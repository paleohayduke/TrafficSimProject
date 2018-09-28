/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.util.ArrayList;
import java.util.Random;

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
        
        
        getSimInfo();

        
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
        

        //rand direction generator
//MEDIUM MAP
//        Directions route = new Directions(roads.get(375).nodeList.get(0));
//        route = route.findRoute(roads, roads.get(375).nodeList.get(0), roads.get(5).nodeList.get(1));   
//        route.start = roads.get(375).nodeList.get(0);       
        
        
// MEDIUMMAP        
//        Directions route = new Directions(roads.get(440).nodeList.get(0));
//        route = route.findRoute(roads, roads.get(440).nodeList.get(0), roads.get(5).nodeList.get(1));   
//        route.start = roads.get(440).nodeList.get(0);       
        
        Random rand = new Random();
        int roadNum1 = rand.nextInt(roads.size());
        int nodeNum1 = rand.nextInt(roads.get(roadNum1).nodeList.size());
        int roadNum2 = rand.nextInt(roads.size());
        int nodeNum2 = rand.nextInt(roads.get(roadNum2).nodeList.size());
        Directions route = new Directions(roads.get(roadNum1).nodeList.get(nodeNum1));
        route = route.findRoute(roads, roads.get(roadNum1).nodeList.get(nodeNum1), roads.get(roadNum2).nodeList.get(nodeNum2));   
        route.start = roads.get(roadNum1).nodeList.get(nodeNum1);       


        for(int i =0;i<route.directions.size();i++){
            System.out.println(route.directions.get(i));
        }
        
        
        Auto test = new Auto();
        test.setDirections(route);
        
       
        
        for(int i =0;i<route.directions.size()*100;i++){
            
            try{
                
            TimeUnit.MILLISECONDS.sleep(250);
            }catch(Exception ex){
                System.out.println("TimeUnit.SECONDS.sleep(1)");
            }
 //           String pauseStr = sc.next();
            test.step(0.0001, .4);
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
    
    // return this shit as a string
    public void getSimInfo(){
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
    }
    
    
    public void setCars(int totalCars){
        Random rand = new Random();
        for(int i = 0;i<totalCars;i++){
//            boolean repeat = true;
//
//
//            int roadNum1=0; 
//            int nodeNum1=0; 
//            int roadNum2=0; 
//            int nodeNum2=0;
            Directions route=makeDirection(); 
//           
//            while(repeat){
//                roadNum1 = rand.nextInt(roads.size());
//                nodeNum1 = rand.nextInt(roads.get(roadNum1).nodeList.size());
//                roadNum2 = rand.nextInt(roads.size());
//                nodeNum2 = rand.nextInt(roads.get(roadNum2).nodeList.size());
//                route = new Directions(roads.get(roadNum1).nodeList.get(nodeNum1));
//                route = route.findRoute(roads, roads.get(roadNum1).nodeList.get(nodeNum1), roads.get(roadNum2).nodeList.get(nodeNum2));
//    
//                if(!route.isEmpty()){
//                    repeat=false;
//                }
//            }
  
            

            
//            route.start = roads.get(roadNum1).nodeList.get(nodeNum1);
            
            Auto tempCar = new Auto();
            tempCar.setDirections(route);
            cars.add(tempCar);
            
        }
//        System.out.println("Cars Size="+cars.size());

    }
    
    public Directions makeDirection(){
        boolean repeat = true;
        Random rand = new Random();
        Directions route = new Directions();
        while(repeat){
            int roadNum1 = rand.nextInt(roads.size());
            int nodeNum1 = rand.nextInt(roads.get(roadNum1).nodeList.size());
            int roadNum2 = rand.nextInt(roads.size());
            int nodeNum2 = rand.nextInt(roads.get(roadNum2).nodeList.size());
            route = new Directions(roads.get(roadNum1).nodeList.get(nodeNum1));
            route = route.findRoute(roads, roads.get(roadNum1).nodeList.get(nodeNum1), roads.get(roadNum2).nodeList.get(nodeNum2));
            route.start = roads.get(roadNum1).nodeList.get(nodeNum1);
            
            if(!route.isEmpty()){
                repeat=false;
            }
        }
        
        return route;
        
    }
    
    public Directions makeDirection(Nd start){
        boolean repeat = true;
        Random rand = new Random();
        Directions route = new Directions();
        while(repeat){
            int roadNum2 = rand.nextInt(roads.size());
            int nodeNum2 = rand.nextInt(roads.get(roadNum2).nodeList.size());
            route = new Directions(start);
            route = route.findRoute(roads, start, roads.get(roadNum2).nodeList.get(nodeNum2));
            route.start = start;
            
            if(!route.isEmpty()){
                repeat=false;
            }
        }
        
        return route;
        
    }
    
    public void step(){
           
        for(int j=0;j<cars.size();j++){
            cars.get(j).step(0.0001, .4);
            if(!cars.get(j).directions.inProgress()){
                cars.get(j).setDirections(makeDirection(cars.get(j).waypointNode));
                System.out.println("makeDirection()");
            }
        }
    
        
        
    }
    
    
    public void step(double velocity, double stepSize){
           
        for(int j=0;j<cars.size();j++){
            cars.get(j).step(velocity,stepSize);
            if(!cars.get(j).directions.inProgress()){
                cars.get(j).setDirections(makeDirection());
 //               System.out.println("makeDirection()");
            }        
        }
    

        
    }
    
    public void updateRenderer(){
        display.stepCars(cars);
    }

}
