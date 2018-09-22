/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import org.xml.sax.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

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
        
        
        // use this to read the .osm file, reader has a couple methods, refer
        // to its class file
        
        MapReader reader = new MapReader("./src/MediumMap.osm");
 //       MapReader reader = new MapReader("./src/KilleenMap.osm");
        
 
 // TEST 
//        System.out.println(reader.roads.get(0).getNodes().get(0).getRef());
//        System.out.println(reader.roads.get(0).getNodes().get(0).getLong());
//        System.out.println(reader.roads.get(0).getNodes().get(0).getLat()); 
//        System.out.println(reader.roads.get(0).getIntersections().get(0).getSecondary()); 
        
        
        
        

        

        Renderer3 display = new Renderer3(reader.getRoads(),reader.minLat,reader.maxLat,reader.minLon,reader.maxLon);
        display.setMap();
        

    }

}
