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
public class Road {
    private String name = "";
    ArrayList<Node> nodeList = new ArrayList<Node>(); 
    
    Road(){
        
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public void addNode(Node node){
        nodeList.add(node);
    }
    
    public ArrayList<Node> getNodes(){
        return nodeList;
    }

    
}
