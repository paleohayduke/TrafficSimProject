/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

/**
 *
 * @author paleo
 */
public class Node {
    private int ref = 0;
    private int longitude = 0;
    private int latitude = 0;
    
    Node(int ref, int longi, int lati){
        setRef(ref);
        setLong(longi);
        setLat(lati);
    }
    
    public void setRef(int ref){
        this.ref = ref;
    }
    public int getRef(){
        return ref;
    }
    public void setLong(int longitude){
        this.longitude = longitude;
    }
    public int getLong(){
        return longitude;
    }
    public void setLat(int latitude){
        this.latitude = latitude;
    }
    public int getLat(){
        return latitude;
    }    
}
