/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

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
    Nd roadConnect1;
    Nd roadConnect2;
    double distConnect1 =0;
    double distConnect2 =0;
    
    Nd(){

    }

    public void setConnect1(Nd connect, double dist){
        roadConnect1=connect;
        distConnect1=dist;
    }
    public void setConnect2(Nd connect, double dist){
        roadConnect2=connect;
        distConnect2=dist;
    }
    public Nd getConnect1(){
        return roadConnect1;
    }
    public Nd getConnect2(){
        return roadConnect2;
    }    
    public double getDistConnect1(){
        return distConnect1;
    }
    public double getDistConnect2(){
        return distConnect2;
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