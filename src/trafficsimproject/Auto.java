/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.util.ArrayList;

/**
 * 
 * @author paleo
 */
public class Auto {
    
    double[] pos = {0,0};//lon, lat
    Directions directions = new Directions();
    
    int directionIndex =0;
    
    
    Nd currentNode = new Nd();
    
    Nd nextNode =  new Nd();
    double distNext = 0;
    double velocity=0;
    
    static int carID=0;

    Auto(){
        currentNode.setRef(carID++);
    }
    Auto(Directions dir){
        currentNode.setRef(carID++);
        setDirections(dir);
    }
    
    public void setDirections(Directions directions){
        this.directions=directions;
        setPos(directions.start);
        
        //HANDLE ITERATION INSIDE THE DIRECTION CLASS!!!!!
        nextNode = directions.start;
    }

    public void setPos(Nd node){
        currentNode.setLat(node.getLat());
        currentNode.setLong(node.getLong());
    }
    public void setPos(double lon, double lat){
        currentNode.setLat(lat);
        currentNode.setLong(lon);
    }

    public Nd getPos(){
        return currentNode;
    }
    
    public void step(){
        //CHECK IF DONE
        
        if(!directions.inProgress()){
            return;
        }
        // this is temporary
        // write function call in Node to get coords from a node specifically
        currentNode.setLat(nextNode.getLat());
        currentNode.setLong(nextNode.getLong());
        System.out.println("STEP");
        System.out.println("LAT "+currentNode.getLat());
        System.out.println("LON "+currentNode.getLong());
        
        
        nextNode=nextNode.connections.get(directions.next());
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

        return currentNode;

    }
    
}
