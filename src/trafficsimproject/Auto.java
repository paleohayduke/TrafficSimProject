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
    ArrayList<Integer> directions = new ArrayList<Integer>();
    
    int directionIndex =0;
    
    
    Nd currentNode = new Nd();
    
    Nd nextNode =  new Nd();
    double distNext = 0;
    double velocity=0;
    
    static int carID=0;

    Auto(){

    }
    
    public void setDirections(Nd start, ArrayList<Integer> directions){
        this.directions=directions;
        setPos(start);
        nextNode = start.connections.get(directions.get(directionIndex));
    }

    public void setPos(Nd node){
        currentNode.setLat(node.getLat());
        currentNode.setLong(node.getLong());
    }
    public void setPos(double lon, double lat){
        currentNode.setLat(lat);
        currentNode.setLong(lon);
    }

    public void step(){
        //CHECK IF DONE
        
        currentNode=nextNode;

        nextNode=nextNode.connections.get(directionIndex++);
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
