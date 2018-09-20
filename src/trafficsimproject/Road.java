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

// creates a Road data type for us. 
public class Road {
    private int id = 0;
    ArrayList<Nd> nodeList = new ArrayList<Nd>(); 
    ArrayList<IntersectNd> intersections = new ArrayList<IntersectNd>();
    
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
    public void addIntersection(IntersectNd nd){
        intersections.add(nd);
    }
    public void setIntersections(ArrayList<IntersectNd> list){
        intersections=list;
    }
    public ArrayList<IntersectNd> getIntersections(){
        return intersections;
    }
}
