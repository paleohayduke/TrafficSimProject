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
public class IntersectNd extends Nd {
    
    private long secondaryRef = 0;
    
    IntersectNd(){
        
    }

    void setSecondary(long ref){
        this.secondaryRef=ref;
    }
    long getSecondary(){
        return secondaryRef;
    }
    
    
}
