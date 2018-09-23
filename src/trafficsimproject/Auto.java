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
    Nd nextNode =  new Nd();
    double distNext = 0;
    double velocity=0;


    Auto(){
        
    }
    
    public void setDirections(Nd start, ArrayList<Integer> directions){
        this.directions=directions;
        setPos(start.getLong(),start.getLat());
        nextNode = start.connections.get(directions.get(directionIndex));
    }
    
    public void setPos(double lon, double lat){
        pos[0]=lon;
        pos[1]=lat;
    }

    public void step(){
        
    }
    
    public double calcDistance(Nd node1, Nd node2){
        double long1=node1.getLong();
        double long2=node2.getLong(); 
        double lat1=node1.getLat(); 
        double lat2=node2.getLat();
        double distance = Math.sqrt(Math.pow((long1-long2), 2)+Math.pow(lat1-lat2,2));
        
        return distance;
    }
    
}
