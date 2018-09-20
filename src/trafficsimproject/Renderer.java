package trafficsimproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;


import org.xml.sax.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

@SuppressWarnings("serial")

/**
 *
 * @author paleo
 */

//  
//

public class Renderer extends JFrame{
    
    
    int xRes = 500;
    int yRes = 500;
    
    ArrayList<Integer> lons = new ArrayList<Integer>();
    ArrayList<Integer> lats = new ArrayList<Integer>();
    
    public void setMap(ArrayList<Road> roads){
//        int arrayIndex =0;
        for(int i = 0; i<roads.size();i++){
            for(int j = 0 ; j<roads.get(i).nodeList.size();j++){
                double lon = roads.get(i).nodeList.get(j).getLong();
                double lat = roads.get(i).nodeList.get(j).getLat();
                lons.add((int)((roads.get(i).nodeList.get(j).getLong()*100000-minLon*100000)+200));
                lats.add((int)(latRange-(roads.get(i).nodeList.get(j).getLat()*100000-minLat*100000)+200));     
                
                System.out.println( "MATH"+(roads.get(i).nodeList.get(j).getLong()*100000) +" - "+ (minLon*100000)  );
                System.out.println("lon "+lon);
                System.out.println("lat "+lat);
                
                
            }
        }
        System.out.println("maxLon"+minLon);
        System.out.println("minLat"+minLat);
    }
    
    double minLat=0;
    double minLon=0;
    double maxLon=0;
    double maxLat =0;
    int latRange=0;
    int lonRange=0;
    Renderer(double minLat, double maxLat, double minLon, double maxLon){
        this.minLat=minLat;
        this.minLon=minLon;
        this.maxLon=maxLon;
        this.maxLat=maxLat;
        System.out.println("SIZE OF WINDOW: " + (maxLon*100000-minLon*100000) + " " +(maxLat*100000-minLat*100000) );
        latRange = (int)(maxLat*100000-minLat*100000);
        lonRange = (int)(maxLon*100000- minLon*100000);
        
        this.setSize(lonRange,latRange);
        
        this.setTitle("Traffic Sim");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new DrawStuff(), BorderLayout.CENTER);
        this.setVisible(true);
        
        
    }
    
    private class DrawStuff extends JComponent{
        public void paint(Graphics g){
            Graphics2D graph2 = (Graphics2D)g;
            
            //see how it looks
            graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Shape drawLine = new Line2D.Float(20,90,55,250);
            
            graph2.setPaint(Color.BLACK);
            //graph2.draw(drawLine);
            for(int i =0; i<lats.size();i++){
                Shape drawPoint = new Line2D.Float(lons.get(i)/scale,lats.get(i)/scale,lons.get(i)/scale+5,lats.get(i)/scale+5);
                System.out.println("DRAWING POINT: "+lons.get(i)+ " " +lats.get(i));
                graph2.draw(drawPoint);
            }
            System.out.println("SIZE OF WINDOW: " + (maxLon*100000-minLon*100000) + " " +(maxLat*100000-minLat*100000) );

            
        }
        int scale = 2;//scale where drawings are on map...
        public void renderFrame(){
            
        }
        
        public void newFrame(){
            
        }
        
        public void addLine(int x1, int y1, int x2, int y2){
            
        }
        
        public void addPoint(int x, int y){
            
        }
        
        
        
        
    }
    
    
}
