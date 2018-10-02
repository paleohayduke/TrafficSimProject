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
    GraphBuilder gb;
//    private ArrayList<Road> roads = new ArrayList<Road>();
    
    //TODO implement this cars
    
    int tickCounter =0;
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
//        StopBuilder sb = new StopBuilder(gb.roads);
//        roads=gb.roads;
        this.gb=gb;
        minLat=reader.minLat;
        minLon=reader.minLon;
        maxLon=reader.maxLon;
        maxLat =reader.maxLat;
        
    }
    
    // to start renderer
    public void startRenderer(double scale){
        this.display = new Renderer(scale, gb.roads,minLat,maxLat,minLon,maxLon);
        display.setMap();
//        display.reSizeWindow();
        
        
    }
    
    public void setScale(double sca){
        display.setScale(sca);
    }
    
    // Need to create framework of methods to handle cars
    public void demo() {
        if(gb.roads.size()<1){
            System.out.println("You must load a map first");
            return;
        }
        
        startRenderer(4);
        // HERE WE GO
        Scanner sc = new Scanner(System.in);

        
        getSimInfo();

        
 
        
        Random rand = new Random();
        int roadNum1 = rand.nextInt(gb.roads.size());
        int nodeNum1 = rand.nextInt(gb.roads.get(roadNum1).nodeList.size());
        int roadNum2 = rand.nextInt(gb.roads.size());
        int nodeNum2 = rand.nextInt(gb.roads.get(roadNum2).nodeList.size());
        Directions route = new Directions(gb.roads.get(roadNum1).nodeList.get(nodeNum1));
        route = route.findRoute(gb.roads, gb.roads.get(roadNum1).nodeList.get(nodeNum1), gb.roads.get(roadNum2).nodeList.get(nodeNum2));   
        route.start = gb.roads.get(roadNum1).nodeList.get(nodeNum1);       


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
        for(int i = 0;i<gb.roads.size();i++){
            System.out.println("road#: "+i);
            System.out.println("intercepts: "+ gb.roads.get(i).intersections.size());
            for(int j = 0;j<gb.roads.get(i).intersections.size();j++){
                System.out.println("intersept ref:\t"+gb.roads.get(i).intersections.get(j).getRef());
            }
            for(int j = 0; j<gb.roads.get(i).nodeList.size();j++){
                System.out.println( "\tnode ref:\t"+gb.roads.get(i).nodeList.get(j).getRef());
                System.out.println( "\tconnections:"+gb.roads.get(i).nodeList.get(j).connections.size());
                
            }
        }
        System.out.println("end"+gb.roads.size());
    }
    
    
    public void setCars(int totalCars){
        Random rand = new Random();
        int routeCounter=0;
        for(int i = 0;i<totalCars;i++){
//            boolean repeat = true;
//
//
//            int roadNum1=0; 
//            int nodeNum1=0; 
//            int roadNum2=0; 
//            int nodeNum2=0;
            Directions route=makeDirection();
            routeCounter++;
            if(routeCounter%100==0){
                System.out.println("...route # "+routeCounter);
            }
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
            int roadNum1 = rand.nextInt(gb.roads.size());
            int nodeNum1 = rand.nextInt(gb.roads.get(roadNum1).nodeList.size());
            int roadNum2 = rand.nextInt(gb.roads.size());
            int nodeNum2 = rand.nextInt(gb.roads.get(roadNum2).nodeList.size());
            route = new Directions(gb.roads.get(roadNum1).nodeList.get(nodeNum1));
            route = route.findRoute(gb.roads, gb.roads.get(roadNum1).nodeList.get(nodeNum1), gb.roads.get(roadNum2).nodeList.get(nodeNum2));
            route.start = gb.roads.get(roadNum1).nodeList.get(nodeNum1);
            
            if(!route.isEmpty()){
                repeat=false;
            }
        }
        
        return route;
        
    }
    
    
    // this is broke... way points are deleted after trip complete.. maybe not necissary. 
    //revisit auto updatepos 
    public Directions makeDirection(Nd start){
        boolean repeat = true;
        Random rand = new Random();
        Directions route = new Directions();
        while(repeat){
            int roadNum2 = rand.nextInt(gb.roads.size());
            int nodeNum2 = rand.nextInt(gb.roads.get(roadNum2).nodeList.size());
            route = new Directions(start);
            route = route.findRoute(gb.roads, start, gb.roads.get(roadNum2).nodeList.get(nodeNum2));
            route.start = start;
            
            if(!route.isEmpty()){
                repeat=false;
            }
        }
        
        return route;
        
    }
    
   // 10/2/2018 -ww 
   // make a function to set this on its own. simulator should keep track of all
   // this shit on its own and not rely on main()
   // should be able to modify these options from GUI 
    double stepSize =0;
    public void step(double velocity, double stepSize){
        if(display.playSpeed!=1){
            if(display.playSpeed==0){
                return;
            }else if(display.playSpeed>1){
               stepSize=stepSize*(display.playSpeed)*(display.playSpeed);
            }
        }
        if(display.nodeSearchPlease){
            Nd searchResult = gb.findNode(display.mouseLong, display.mouseLat);
//            System.out.println("ndPlease");
            display.nodeInfoFrame.node=searchResult;
            display.nodeSearchPlease=false;
            display.nodeInfoFrame.setFields();;
//            display.nodeInfoFrame.repaint();
            
        }else if(display.nodeButtonOn){
            display.nodeInfoFrame.setFields();
        }
        
        this.stepSize=stepSize;
        tickCounter++;
        if(0==tickCounter%3600){
            System.out.println("tick # "+tickCounter);
        }   
        for(int j=0;j<cars.size();j++){
//            if(checkStop(j)){
//                continue;
//            }
            cars.get(j).step(velocity,stepSize);
            if(!cars.get(j).directions.inProgress()){
                cars.get(j).waypointNode.stopQ.remove(cars.get(j).posNode);
                
                cars.get(j).waypointNode.removeCar(cars.get(j).posNode);
                cars.get(j).lastWaypointNode.removeCar(cars.get(j).posNode);
                cars.get(j).setDirections(makeDirection());
//                cars.get(j).setDirections(makeDirection(cars.get(j).waypointNode));
 //               System.out.println("makeDirection()");
            }        
        }
    

        
    }
    
    private boolean checkStop(int carIndex){
         if(cars.get(carIndex).stop==true){
            return true;
        }
        return false;
    }
    
    public void updateRenderer(){
        display.stepCars(cars);
    }

}
