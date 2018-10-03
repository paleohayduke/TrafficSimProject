/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author paleo
 */

// just a Nd with info about who this node intersects with 
public class IntersectNd extends Nd implements Serializable {
    
    private long secondaryRef = 0;
    ArrayList<Nd> connections = new ArrayList<Nd>();
    ArrayList<Double> weights = new ArrayList<Double>();
    
    
    IntersectNd(){
        
    }

    
    public void addEdge(Nd node){
        
    }
    
    

    void setSecondary(long ref){
        this.secondaryRef=ref;
    }
    long getSecondary(){
        return secondaryRef;
    }
    
    
}
