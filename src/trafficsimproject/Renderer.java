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

@SuppressWarnings("serial")

/**
 *
 * @author paleo
 */

//  
//

public class Renderer extends JFrame{
    Renderer(){
        this.setSize(500,500);
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
    }
    
    
}
