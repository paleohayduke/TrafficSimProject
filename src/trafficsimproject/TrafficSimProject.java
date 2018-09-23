/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.util.Scanner;

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
        
        MapReader reader = new MapReader("./src/HomeMap.osm");
 //       MapReader reader = new MapReader("./src/KilleenMap.osm");

        

        
        
        
        
        GraphBuilder gb = new GraphBuilder(reader.getRoads());
        
        Renderer3 display = new Renderer3(gb.getRoads(),reader.minLat,reader.maxLat,reader.minLon,reader.maxLon);
        display.setMap();
        // HERE WE GO
        Scanner sc = new Scanner(System.in);
        String pauseStr = sc.next();
        display.setAutoPos(gb.roads.get(5).nodeList.get(2));
        pauseStr = sc.next();
        display.setAutoPos(gb.roads.get(5).nodeList.get(3));
        pauseStr = sc.next();
        
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

}
