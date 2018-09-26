/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.util.ArrayList;

// get rid of these when done
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author paleo
 */
public class Simulation {
    
    private ArrayList<Road> roads = new ArrayList<Road>();
    
    //TODO implement this cars
    private ArrayList<Auto> cars = new ArrayList<Auto>();
    
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
    public void startRenderer(double scale){
        this.display = new Renderer(scale, roads,minLat,maxLat,minLon,maxLon);
        display.setMap();
        
        
    }
    
    public void setScale(double sca){
        display.setScale(sca);
    }
    
    // Need to create framework of methods to handle cars
    public void demo() {
        if(roads.size()<1){
            System.out.println("You must load a map first");
            return;
        }
        
        startRenderer(4);
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

        
        //fix this 
//        Directions route = new Directions(roads.get(5).nodeList.get(4));
////        route = route.findRoute(roads, roads.get(5).nodeList.get(4), roads.get(1).nodeList.get(9));     
//        route = route.findRoute(roads, roads.get(5).nodeList.get(4), roads.get(1).nodeList.get(9));   
//        route.start = roads.get(5).nodeList.get(4); 

//        Directions route = new Directions(roads.get(10).nodeList.get(0));
//        route = route.findRoute(roads, roads.get(10).nodeList.get(0), roads.get(5).nodeList.get(1));   
//        route.start = roads.get(10).nodeList.get(0);       
        

//MEDIUM MAP
//        Directions route = new Directions(roads.get(119).nodeList.get(0));
//        route = route.findRoute(roads, roads.get(119).nodeList.get(0), roads.get(5).nodeList.get(1));   
//        route.start = roads.get(119).nodeList.get(0);       

//MEDIUM MAP
        Directions route = new Directions(roads.get(375).nodeList.get(0));
        route = route.findRoute(roads, roads.get(375).nodeList.get(0), roads.get(5).nodeList.get(1));   
        route.start = roads.get(375).nodeList.get(0);       
        
        
// MEDIUMMAP        
//        Directions route = new Directions(roads.get(440).nodeList.get(0));
//        route = route.findRoute(roads, roads.get(440).nodeList.get(0), roads.get(5).nodeList.get(1));   
//        route.start = roads.get(440).nodeList.get(0);       

        for(int i =0;i<route.directions.size();i++){
            System.out.println(route.directions.get(i));
        }
        
        
        Auto test = new Auto();
        test.setDirections(route);
        
       
        
        for(int i =0;i<route.directions.size();i++){
            
            try{
            TimeUnit.SECONDS.sleep(1);
            }catch(Exception ex){
                System.out.println("TimeUnit.SECONDS.sleep(1)");
            }
 //           String pauseStr = sc.next();
            test.step();
            System.out.println("Frame "+i);
            display.setAutoPos(test.posNode);
//            setScale(i);
        }
        
        while(true){
            
 //           String pauseStr = sc.next();
            test.debug();
            display.setAutoPos(test.posNode);
//            setScale(i);
        }
        
        
        

    }
    
    


}
