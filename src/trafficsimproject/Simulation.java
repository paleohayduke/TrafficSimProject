/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author paleo
 */
public class Simulation {
    
    private ArrayList<Road> roads = new ArrayList<Road>();
    private Renderer display;
    
    // TODO: localize the scope of these variables
    private double minLat=0;
    private double minLon=0;
    private double maxLon=0;
    private double maxLat =0;
    
    //you'll have to use openMap() yourself
    Simulation(){
        
    }
    
    //constructor to start the object up while loading a file
    Simulation(String fileName){
        openMap(fileName);
    }
    
    //opens map
    public void openMap(String fileName){
        MapReader reader = new MapReader(fileName);
        GraphBuilder gb = new GraphBuilder(reader.getRoads());
        roads=gb.roads;
        minLat=reader.minLat;
        minLon=reader.minLon;
        maxLon=reader.maxLon;
        maxLat =reader.maxLat;
        
    }
    
    // to start renderer
    public void startRenderer(){
        this.display = new Renderer(roads,minLat,maxLat,minLon,maxLon);
        display.setScale(1);
        display.setMap();
        
        
    }
    
    
    // Need to create framework of methods to handle cars
    public void demo() {
        if(roads.size()<1){
            System.out.println("You must load a map first");
            return;
        }
        
        startRenderer();

        // HERE WE GO
        Scanner sc = new Scanner(System.in);
//        String pauseStr = sc.next();
        
        
 //       display.setAutoPos(gb.roads.get(5).nodeList.get(2));
 //       pauseStr = sc.next();
        
 //       display.setAutoPos(gb.roads.get(5).nodeList.get(3));
 //       pauseStr = sc.next();
        
        
        for(int i = 0;i<roads.size();i++){
            System.out.println("road#: "+i);
            System.out.println("intercepts: "+ roads.get(i).intersections.size());
            for(int j = 0;j<roads.get(i).intersections.size();j++){
                System.out.println("intersept ref:\t"+roads.get(i).intersections.get(j).getRef());
            }
            for(int j = 0; j<roads.get(i).nodeList.size();j++){
                System.out.println( "\tnode ref:\t"+roads.get(i).nodeList.get(j).getRef());
                System.out.println( "\tconnections:"+roads.get(i).nodeList.get(j).connections.size());
                
            }
        }
        System.out.println("end"+roads.size());
        
        Directions testDir = new Directions(roads.get(0).nodeList.get(1));
        Directions route = testDir.findRoute(roads, roads.get(0).nodeList.get(1), roads.get(1).nodeList.get(9));        

       // Directions testDir = new Directions(gb.roads.get(424).nodeList.get(0));
       // Directions route = testDir.findRoute(gb.roads, gb.roads.get(424).nodeList.get(0), gb.roads.get(42).nodeList.get(9));        
        

        
        route.start = roads.get(0).nodeList.get(1);
        

        for(int i =0;i<route.directions.size();i++){
            System.out.println(route.directions.get(i));
        }
        
        Auto test = new Auto();
        test.setDirections(route);
       

        String pauseStr = sc.next();
              test.step();
         System.out.println("did i get here");
         System.out.println("what is test.currendNode "+test.currentNode.getRef());
         
         display.setAutoPos(test.currentNode);

        pauseStr = sc.next();
              test.step();
        display.setAutoPos(test.currentNode);        

        pauseStr = sc.next();
              test.step();
        display.setAutoPos(test.currentNode);        

        pauseStr = sc.next();
              test.step();
        display.setAutoPos(test.currentNode);        
        
                pauseStr = sc.next();
              test.step();
        display.setAutoPos(test.currentNode);        

        pauseStr = sc.next();
              test.step();
        display.setAutoPos(test.currentNode);        

        pauseStr = sc.next();
              test.step();
        display.setAutoPos(test.currentNode);   
        
                        pauseStr = sc.next();
              test.step();
        display.setAutoPos(test.currentNode);        

        pauseStr = sc.next();
              test.step();
        display.setAutoPos(test.currentNode);        

        pauseStr = sc.next();
              test.step();
        display.setAutoPos(test.currentNode);
                pauseStr = sc.next();
              test.step();
        display.setAutoPos(test.currentNode);        

        pauseStr = sc.next();
              test.step();
        display.setAutoPos(test.currentNode);   
        
                        pauseStr = sc.next();
              test.step();
        display.setAutoPos(test.currentNode);        

        pauseStr = sc.next();
              test.step();
        display.setAutoPos(test.currentNode);        

        pauseStr = sc.next();
              test.step();
        display.setAutoPos(test.currentNode);
        
//       display.setAutoPos(gb.roads.get(5).nodeList.get(2));
//       pauseStr = sc.next();
//        
//       display.setAutoPos(gb.roads.get(5).nodeList.get(3));
//       pauseStr = sc.next();
       


    }
    
    


}
