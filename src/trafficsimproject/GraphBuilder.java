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
public class GraphBuilder {
    
    ArrayList<Road> roads = new ArrayList<Road>();
    
    

    GraphBuilder(ArrayList<Road> roads){
        this.roads=roads;
        //go through each road
        for(int i = 0;i<roads.size();i++){ 
            //REMEMBER TO UNCOMMENT THIS
            roads.get(i).buildLocalIntercets();
            
            
//            System.out.println("Road#"+i+" size: "+roads.get(i).nodeList.size()+" intercept size: "+ roads.get(i).intersections.size());            

            //Go through each node
            for(int j = 0; j<roads.get(i).getNodes().size();j++){
//                System.out.println("\tnode ref:"+roads.get(i).getNodes().get(j).getRef());
                
            // check each node again all intersections. 
                for(int k=0;k<roads.get(i).getIntersections().size();k++){

                   if(roads.get(i).nodeList.get(j).getRef()==roads.get(i).getIntersections().get(k).getRef()){
                       //MUST FIND AND LINK ALL instance of this intersect across all roads
                       Nd tempNode = findNode(roads.get(i).nodeList.get(j).getRef());
                       roads.get(i).nodeList.get(j).addConnection(tempNode, 0);
//                       System.out.println("IM WORKING******");
                       
                   }
                }
                
            }
        }
    }
    
    private Nd findNode(long ref){
        Nd temp = new Nd();
        for(int i = 0; i<roads.size();i++){
            for(int j =0; j<roads.get(i).nodeList.size();j++){
                if(roads.get(i).nodeList.get(j).getRef()==ref){
                    temp = roads.get(i).nodeList.get(j);
                }
            }
        }
        
        return temp;
    }
}
