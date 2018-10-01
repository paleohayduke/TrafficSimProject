/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author paleo
 */
public class Auto {
    

    Directions directions = new Directions();
    
    Nd posNode = new Nd();
    Nd waypointNode =  new Nd();
    Nd lastWaypointNode = new Nd();
    double distNext = 0;
    double velocity=0;
    double maxVelocity=0;
    double acceleration =.00001;
    boolean stop = false;
    static int carID=0;
    
    

    Auto(){
        posNode.setRef(carID++);
        setNodeParent();
    }
    Auto(Directions dir){
        posNode.setRef(carID++);
        setDirections(dir);
        setNodeParent();
    }
    
    private void setNodeParent(){
        posNode.parentAuto=this;
    }
    
    public void setDirections(Directions directions){
        this.directions=directions;
        setPos(directions.start);
        
        //HANDLE ITERATION INSIDE THE DIRECTION CLASS!!!!!
        try{
        waypointNode = directions.start.connections.get(this.directions.next());
        waypointNode.addCar(this.posNode);
        }
        catch(Exception e){
            
        }
    }

    public void setPos(Nd node){
        posNode.setLat(node.getLat());
        posNode.setLong(node.getLong());
    }
    public void setPos(double lon, double lat){
        posNode.setLat(lat);
        posNode.setLong(lon);
    }

    public Nd getPos(){
        return posNode;
    }
    
    public void ping(){
        System.out.println("****CAR**** #"+ posNode.getRef());
        System.out.println("this.LON "+posNode.getLong());
        System.out.println("this.LAT "+posNode.getLat());
        System.out.println("distNext "+posNode.calcDistance(waypointNode));
        
//        System.out.println("NEXT.ref "+posNode.getRef());
//        System.out.println("NEXT.connects: "+posNode.connections.size());
        
//        System.out.println("next.connects REF: "+nextNode.connections.get(1).getRef());
        
        
    }

    double carSpacing =.0001151;
    double stopSpacing=0;
    int justGo =0;
    boolean stoppedOnce=false;
    public void calcPos(double velocity, double timeIncrement){
        
        double x1=posNode.getLong();
        double y1=posNode.getLat();
        double x2=waypointNode.getLong();
        double y2=waypointNode.getLat();
        double d = velocity*timeIncrement;
        double D = posNode.calcDistance(waypointNode);
//        System.out.println("D="+D+" d="+d);

//EDITED OUT 9/28/2018 7:00AM 
// I think this fixed the jumping around... 
//        if(D==0){
//            nextWaypoint();
//        }


//break the following down into multiple functions
// and add option toggle. 
// this handles distance between cars
        for(int i = 0;i< waypointNode.cars.size();i++){
            if(waypointNode.cars.get(i).parentAuto.lastWaypointNode==this.lastWaypointNode
                    &&waypointNode.cars.get(i)!=this.posNode){
                double otherDist=waypointNode.cars.get(i).calcDistance(waypointNode);
                if(otherDist<D){
                    double spacing = D-otherDist;
//                    double spacing = posNode.calcDistance(waypointNode.cars.get(i));
                    if(spacing<carSpacing){
                        justGo++;
                        if(justGo>20){
                            justGo=0;
                            break;

                        }
                        d=0;
//                        this.velocity=0;
//                        System.out.println("car distance");
                        return;
                    }
                }
            }
            else if(waypointNode.cars.get(i).parentAuto.lastWaypointNode==waypointNode
                    &&waypointNode.cars.get(i).parentAuto.waypointNode!=this.lastWaypointNode){
                if(posNode.calcDistance(waypointNode.cars.get(i))<carSpacing){
//                    justGo++;
                        if(justGo>100){
                            justGo=0;
//                            this.velocity=this.maxVelocity;
                            break;

                        }
                    d=0;
//                    this.velocity=0;
                    return;
                }
                
            }
        }

//stopsigns
        if(waypointNode.isStop){
            if(D<.00017){
                
//                if(!stoppedOnce){
//                    this.velocity=0;
//                    stoppedOnce=true;
//                }
                if(!waypointNode.stopQ.contains(this.posNode)){
                    waypointNode.stopQ.add(posNode);
//                    System.out.println("add");
                }
                
                if(this.posNode==waypointNode.stopQ.get(0)){
                    
//                    stop=false;
//                    System.out.println("remove");
//                    waypointNode.stopQ.remove(this.posNode);
                }else{
//                    System.out.println("ID="+this.posNode.getRef());
//                    System.out.println("get(0)="+waypointNode.stopQ.get(0).getRef());
//                    System.out.println("stop");
                    justGo++;
                    if(justGo>5){
                        justGo=0;
                        

                    }else{
                        d=0;
//                        this.velocity=0;
                    }
                }
            }
        }
        
        
        if(d>=D){
            
            d=d-D;
            
            nextWaypoint();
        }
        if(d<D){
//            System.out.println("d<D");
            double x3 = -(x1-x2)*(d/D)+x1;
            double y3 = -(y1-y2)*(d/D)+y1;
            posNode.setLat(y3);
            posNode.setLong(x3);

        }
        
        

    }
    
    public void nextWaypoint(){
        //CHECK IF DONE
        if(waypointNode.isStop){
            stoppedOnce=false;
            waypointNode.stopQ.remove(this.posNode);
        }
        
        lastWaypointNode.removeCar(posNode);
        lastWaypointNode=waypointNode;
        //EDITS 9/30/2019 3:00AM
//        waypointNode.removeCar(posNode);


        if(!directions.inProgress()){
            lastWaypointNode.cars.remove(this.posNode);
//            System.out.println("!inProgress()");
            waypointNode.removeCar(posNode);
            return;
            
        }

        int choice = directions.next();
        //DISABLED 9/28/2018 7:09AM dont think we need this at current
        // frame rates. 
        //posNode.setLat(waypointNode.getLat());
        //posNode.setLong(waypointNode.getLong());
//        System.out.println("choice="+choice);
        waypointNode=waypointNode.connections.get(choice);
        waypointNode.addCar(posNode);
    }
    
    public void stepOLD(double velocity, double timeStep){
        //CHECK IF DONE
        // this is temporary
        // write function call in Node to get coords from a node specifically
        
        
//        System.out.println("distance to waypoint: "+ posNode.calcDistance(waypointNode));
        calcPos(velocity, timeStep);
        
        //ping();
        
        
        
    }
    
//    boolean accelSet=false;
    
    public void step(double maxVelocity, double timeStep){
        //CHECK IF DONE
        // this is temporary
        // write function call in Node to get coords from a node specifically
        
//        this.maxVelocity=maxVelocity;
//        if(velocity<maxVelocity){
//            velocity+=acceleration;
//            if(velocity>maxVelocity){
//                velocity=maxVelocity;
//            }
//        }
//        if(!accelSet){
//            acceleration=.00002;
//            accelSet=true;
//        }
//        
//        System.out.println("distance to waypoint: "+ posNode.calcDistance(waypointNode));
        calcPos(maxVelocity, timeStep);
        
        //ping();
        
        
        
    }
        

    
    public double calcDistance(Nd node1, Nd node2){

        double long1=node1.getLong();
        double long2=node2.getLong(); 
        double lat1=node1.getLat(); 
        double lat2=node2.getLat();
        double distance = Math.sqrt(Math.pow((long1-long2), 2)+Math.pow(lat1-lat2,2));
        
        return distance;
    }
    
    public Nd getCurrentNd(){

        return posNode;

    }
    
    
    
    
    public void debug(){
        Scanner sc = new Scanner(System.in);
//        boolean running = true;
//        while(running){
        System.out.println("***DEBUG***");
        System.out.println(" ref: " +waypointNode.getRef());
        for(int i =0;i<waypointNode.connections.size();i++){
            System.out.println("  Connection #" + i+" "+waypointNode.connections.get(i).getRef());
           //FIND ROAD CONNECTIONS() 
        }
        String choice = sc.nextLine();
        if(choice.charAt(0)=='q'){
            System.out.println("ending debug");
//            running=false;
        }else{
            try{
            waypointNode=waypointNode.connections.get(Integer.parseInt(choice));
            posNode.setLat(waypointNode.getLat());
            posNode.setLong(waypointNode.getLong());
            }catch(Exception ex){
                
            }
        }
//        }
        return;
    }
    
    

    
    
    
}
