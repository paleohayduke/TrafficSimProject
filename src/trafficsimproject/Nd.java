/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.util.ArrayList;

/**
 *
 * These are the nodes, Node was already reserved by another library in use. 
 */
// This is a Node, Node was a reserved word and 
// Nd is used to indicate a Node for a <way> in the original .OSM file.
// its as u see
public class Nd {
    private long ref = 0;
    private double longitude = 0;
    private double latitude = 0;

    
    ArrayList<Nd> connections = new ArrayList<Nd>();
    ArrayList<Double> weights = new ArrayList<Double>();
 
    public void addConnection(Nd node, double weight){
        this.connections.add(node);
        this.weights.add(weight);
    }
    
    Nd(){

    }

    public void setRef(long ref){
        this.ref = ref;
    }
    public long getRef(){
        return ref;
    }
    public void setLong(double longitude){
        this.longitude = longitude;
    }
    public double getLong(){
        return longitude;
    }
    public void setLat(double latitude){
        this.latitude = latitude;
    }
    public double getLat(){
        return latitude;
    }    
}