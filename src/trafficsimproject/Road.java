/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.util.ArrayList;

/**
 *
 * TODO: figure what else we need here, currently just using to test 
 * // parser
 */
public class Road {
    private int id = 0;
    ArrayList<Nd> nodeList = new ArrayList<Nd>(); 
    
    Road(){
        
    }
    public void setID(int id){
        this.id = id;
    }
    public int getID(){
        return id;
    }
    
    public void addNode(Nd node){
        nodeList.add(node);
    }
    
    public ArrayList<Nd> getNodes(){
        return nodeList;
    }

    
}
