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
    double distNext = 0;
    double velocity=0;
    
    static int carID=0;

    Auto(){
        posNode.setRef(carID++);
    }
    Auto(Directions dir){
        posNode.setRef(carID++);
        setDirections(dir);
    }
    
    public void setDirections(Directions directions){
        this.directions=directions;
        setPos(directions.start);
        
        //HANDLE ITERATION INSIDE THE DIRECTION CLASS!!!!!
        waypointNode = directions.start;
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
        System.out.println("******PING*****");
        System.out.println("this.LON "+posNode.getLong());
        System.out.println("this.LAT "+posNode.getLat());
//        System.out.println("NEXT.ref "+posNode.getRef());
//        System.out.println("NEXT.connects: "+posNode.connections.size());
        
//        System.out.println("next.connects REF: "+nextNode.connections.get(1).getRef());
        
        
    }
    
    public void step(){
        //CHECK IF DONE
        
        if(!directions.inProgress()){
            return;
        }
        // this is temporary
        // write function call in Node to get coords from a node specifically
        
        

        int choice = directions.next();
        posNode.setLat(waypointNode.connections.get(choice).getLat());
        posNode.setLong(waypointNode.connections.get(choice).getLong());
        
        ping();
        
        
        waypointNode=waypointNode.connections.get(choice);
        
    }
    
        public void stepOLD(){
        //CHECK IF DONE
        
        if(!directions.inProgress()){
            return;
        }
        // this is temporary
        // write function call in Node to get coords from a node specifically
        
        

        int choice = directions.next();
        posNode.setLat(waypointNode.connections.get(choice).getLat());
        posNode.setLong(waypointNode.connections.get(choice).getLong());
        
        ping();
        
        
        waypointNode=waypointNode.connections.get(choice);
        
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
