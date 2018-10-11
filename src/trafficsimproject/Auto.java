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
/*
//******************************************************
//***  Class Name
//***  Class Author (i.e. Your name)
//******************************************************
//*** Purpose of the class (Why did you write this class?)
//***
//******************************************************
//*** Date
//******************************************************
//*** List of changes with dates. 
//******************************************************
//*** Look at this!
//*** List all the places in the code where you did something interesting,
//*** clever or elegant.  If you did good work in this program and you want
//*** me to consider it in your grade, point it out here.
//*******************************************************
*/
public class Auto {
    

    Directions directions = new Directions();
    
    Nd posNode = new Nd();
    Nd targetNode = new Nd();
    Nd waypointNode =  new Nd();
    Nd lastWaypointNode = new Nd();
    double distNext = 0;
    double velocity=0;
    double maxVelocity=0;
    double acceleration =.000040;
    boolean stop = false;
    static int carID=0;
    boolean accelerationOn=true;
    

    
    
    
//******************************************************
//***  Method Name
//***  Method Author (i.e. Your name)
//******************************************************
//*** Purpose of the Method (Why did you write this Method?)
//*** Method Inputs:
//*** List all the method parameters with their expected value ranges
//*** Return value:
//*** If this is a function list the return data type and the expected range of 
//*** values to be returned.
//******************************************************
//*** Date
//******************************************************

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

    double carSpacing =.00016;
    double stopSpacing=.00004;
    int justGo =0;
    boolean stoppedOnce=false;
    
    
    public void calcPos(double timeIncrement){
        double newVelocity = waypointNode.speedLimit;
        if(accelerationOn){
            if(velocity<newVelocity){
                velocity=velocity+acceleration*timeIncrement;
            }else if(velocity>newVelocity){
                velocity=velocity-acceleration*timeIncrement;
            }
            
        }else{
            velocity=newVelocity;
        }
        
        double x1=posNode.getLong();
        double y1=posNode.getLat();
        double x2=waypointNode.getLong();
        double y2=waypointNode.getLat();
        double d = velocity*timeIncrement;
        double D = posNode.calcDistance(waypointNode);





        if(waypointNode.isStop){
            if(D<stopSpacing){


                if(!waypointNode.stopQ.contains(this.posNode)){
                    waypointNode.stopQ.add(posNode);
                    stoppedOnce=true;
                    if(waypointNode.connections.size()>3){
                        d=0;
                        this.velocity=0;
                        
                    }

//                    System.out.println("add");
                }
                
                if(this.posNode==waypointNode.stopQ.get(0)){

                }else{

                    justGo++;
                    if(justGo>5){
                        justGo=0;
                        d=newVelocity*timeIncrement;

                    }else{
                        d=0;
                        this.velocity=0;
                    }
                }
            }
        }

        for(int i = 0;i< waypointNode.cars.size();i++){
            if(waypointNode.cars.get(i).parentAuto.lastWaypointNode==this.lastWaypointNode
                    &&waypointNode.cars.get(i)!=this.posNode){
                double otherDist=waypointNode.cars.get(i).calcDistance(waypointNode);
                if(otherDist<D){
                    double spacing = D-otherDist;
//                    double spacing = posNode.calcDistance(waypointNode.cars.get(i));
                    if(spacing<carSpacing){
                        justGo++;
                        if(justGo>100){
                            justGo=0;
                            d=newVelocity*timeIncrement;
                            break;

                        }
//                        d=0;
//                        velocity=velocity-acceleration*3;
                        this.velocity=0;
//                        System.out.println("car distance");
                        return;
                    }

                }
            }
            else if(waypointNode.cars.get(i).parentAuto.lastWaypointNode==waypointNode
                    &&waypointNode.cars.get(i).parentAuto.waypointNode!=this.lastWaypointNode){
                if(posNode.calcDistance(waypointNode.cars.get(i))<carSpacing){
                    justGo++;
                        if(justGo>200){
                            justGo=0;
                            
                            d=newVelocity*timeIncrement;
                            break;

                        }
//                    d=0;
//                    velocity=velocity-acceleration*3;
                    this.velocity=0;
                    return;
                }
                
            }
        }

//stopsigns
        
        
        
        while(d>(D)){
            
            d=d-D;
            
            if(!nextWaypoint()){
                break;
            }
            D = posNode.calcDistance(waypointNode);
        }
        if(d<(D)){
//            System.out.println("d<D");
            double x3 = -(x1-x2)*(d/D)+x1;
            double y3 = -(y1-y2)*(d/D)+y1;
//            Nd temp=calcOffset(posNode, x3, y3);
//            posNode.setLong(temp.getLong());
//            posNode.setLat(temp.getLat());
            posNode.setLat(y3);
            posNode.setLong(x3);

        }
        
        

    }
    
    //for start of leg
        private Nd calcOffset(Nd startPoint, Nd endPoint, Nd disPoint){
            double px=startPoint.getLong()-endPoint.getLong();
            double py=startPoint.getLat()-endPoint.getLat();
        
            double nx=(-py);
            double ny= px;
            double norm = (Math.sqrt(nx*nx+ny*ny));
            nx=nx/norm;
            ny=ny/norm;
        
            Nd offNode = new Nd();
            double cx=disPoint.getLong()+stopSpacing*nx;
            double cy=disPoint.getLong()+stopSpacing*ny;
            offNode.setLong(cx);
            offNode.setLat(cy);
            return offNode;
        }
    
    public boolean nextWaypoint(){
        if(waypointNode.isStop){
            stoppedOnce=false;
            waypointNode.stopQ.remove(this.posNode);
        }
        
        lastWaypointNode.removeCar(posNode);
        lastWaypointNode=waypointNode;

        if(!directions.inProgress()){
            waypointNode.stopQ.remove(this.posNode);
            lastWaypointNode.removeCar(this.posNode);
//            System.out.println("!inProgress()");
            waypointNode.removeCar(posNode);
            return false;            
        }

        int choice = directions.next();
        waypointNode=waypointNode.connections.get(choice);            
        waypointNode.addCar(posNode);
        return true;
    }
    

    
//    boolean accelSet=false;
    
    public void step(double timeStep){
        if(stop){
            velocity=0;
            return;
        }
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
        calcPos(timeStep);
        
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
