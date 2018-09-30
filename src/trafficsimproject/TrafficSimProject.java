/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author paleo
 */

// Just being used to test stuff right now. 
public class TrafficSimProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        demo();
        //test();
    }
    
    static void demo(){
        Simulation sim = new Simulation();
//        sim.openMap("./src/KilleenMap.osm"); // pick the map
//        sim.startRenderer(8);// 8 is the scale
        sim.openMap("./src/MediumMap.osm");
        sim.startRenderer(4);
        
//        sim.openMap("./src/Austin.osm");
//        sim.startRenderer(1);
        sim.setCars(500); // 200 is the amount of cars 

        for(int i =0;i<111111100;i++){ //play for a long time
            sim.step(0.00016, .05);            
            try{
                
                TimeUnit.MILLISECONDS.sleep(20); //
            }catch(Exception ex){
                System.out.println("TimeUnit.SECONDS.sleep(1)");
            }
 //           String pauseStr = sc.next();

 //           System.out.println("Frame "+i);
            sim.updateRenderer();
//            setScale(i);
        }
//        sim.demo();
//        sim.startRenderer(1);
    }
    
    static void test(){
        Simulation simTest = new Simulation();
        simTest.openMap("./src/HomeMap.osm");
        simTest.startRenderer(1);
        
    }


}
