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
    public void setMap(ArrayList<Road> roads){
        
    }
    
    
    Renderer(double minLat, double maxLat, double minLon, double maxLon){
        
        int latRange = (int)(maxLat*100000-minLat*100000);
        int lonRange = (int)(maxLon*100000- minLon*100000);
        
        this.setSize(lonRange,latRange);
        System.out.println("SIZE OF WINDOW: " + (maxLon*100000-minLon*100000) + " " +(maxLat*100000-minLat*100000) );
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
            graph2.draw(drawLine);
            
        }
        
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
