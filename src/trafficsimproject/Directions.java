/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author paleo
 */
public class Directions {
    
    Nd start;
    ArrayList<Integer> directions = new ArrayList<Integer>();
    public double score;
    
    Directions(){
        
    }
    
    Directions(Directions dir){
        directions= new ArrayList<Integer>(dir.directions);
    }
    
    Directions(int start){
        directions.add(start);
    }
    Directions(Nd start){
        this.start=start;
    }
    
    public boolean inProgress(){
        if (stepIndex<directions.size()){
            return true;
        }
        else return false;
    }
    
    int stepIndex=0;
    public int next(){
        return directions.get(stepIndex++);
    }
    
    public void add(int dir,double score){
        directions.add(dir);
        this.score = score;
        
    }
    
    public Directions findRoute(ArrayList<Road> roads, Nd start, Nd end){
        Directions route = new Directions();
        ArrayList<Directions> queue = new ArrayList<Directions>();
        
        ArrayList<Long> visitedRef = new ArrayList<Long>();
        boolean found = false;
        

//        Nd next;//in case
        while(!found){
            Nd current=start;
            
            
            for(int i =0;i< current.connections.size();i++){

                Directions tempDirection= new Directions();
                
                
                if(queue.size()>0){
                    tempDirection = new Directions(queue.get(0));
                    queue.remove(0);
                }
                
                //get current node
                current=start;
                for(int j=0;j<tempDirection.directions.size();j++){
                    current=current.connections.get(tempDirection.directions.get(j));
                    if(current.getRef()==end.getRef()){
                        System.out.println("FOUND ROUTE");
                        found=true;
                        return tempDirection;
                        
                    }
                }
                
                // add direction list with score to queue
                for(int j =0;j<current.connections.size();j++){
                    Directions temp2Direction = new Directions(tempDirection);
                    temp2Direction.add(j, current.connections.get(j).calcDistance(end));
                    System.out.println("Adding direction");
                    queue.add(temp2Direction);
                }          
                
                
                
            }
            queue=sortQueue(queue);
            


            
            
            
            
            
            
        }
        
        
        
        
        
        return route;
    }
    

    
    
// this needs to be tested
//https://docs.oracle.com/javase/6/docs/api/java/util/Comparator.html
    //TEST THIS OUT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private ArrayList<Directions> sortQueue(ArrayList<Directions> queue){
        Collections.sort(queue, new Comparator<Directions>(){
            public int compare(Directions d1, Directions d2){
                return Double.valueOf(d1.score).compareTo(d2.score);
            }
        });
        
        return queue;
    }
    
    public int compareTo(Directions d){
        if(this.score>d.score)return 1;
        if(this.score<d.score)return-1;
        else return 0;
    }
    public int compareTo(double score){
        if(this.score>score)return 1;
        if(this.score<score)return-1;
        else return 0;
    }
}
