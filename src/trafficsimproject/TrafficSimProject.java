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
        Simulation sim = new Simulation();
        sim.openMap("./src/MediumMap.osm");
        sim.setCars(100);
        sim.startRenderer(5);
//       sim.step();
//        sim.updateRenderer();
        for(int i =0;i<111111100;i++){
            
            try{
                
                TimeUnit.MILLISECONDS.sleep(250);
            }catch(Exception ex){
                System.out.println("TimeUnit.SECONDS.sleep(1)");
            }
 //           String pauseStr = sc.next();
            sim.step();
            System.out.println("Frame "+i);
            sim.updateRenderer();
//            setScale(i);
        }
//        sim.demo();
//        sim.startRenderer(1);
    }
    
    


}
