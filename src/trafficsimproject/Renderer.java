/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author paleo
 */
public class Renderer extends JFrame{
    
    int playSpeed = 1;
    DrawStuff drawer = new DrawStuff();
    ArrayList<Road> roads = new ArrayList<>();
    ArrayList<Auto> cars = new ArrayList<>();
    ArrayList<Shape> roadShapes = new ArrayList<Shape>();
    ArrayList<Shape> intersectShapes = new ArrayList<Shape>();
    ArrayList<Shape> carShapes = new ArrayList<Shape>();
//    MouseHandler mouseHandler;
    //send a Nd to this with mouse! check that this is not null first. 
    NodeInfoFrame nodeInfoFrame;
    

    double mouseLong=0;
    double mouseLat=0;
    

    //drawRoad = new Line2D.Float(lons.get(i)/scale,lats.get(i)/scale,lons.get(i+1)/scale,lats.get(i+1)/scale);
    
    //for adjusting map
    double scale = 1;
    
    Renderer(double scale, ArrayList<Road> roads, double minLat, double maxLat, double minLon, double maxLon){
        this.scale=scale;
        this.roads=roads;
        this.minLat=minLat;
        this.minLon=minLon;
        this.maxLon=maxLon;
        this.maxLat=maxLat;// i made these global because of testing. sys out.prints are all debug testing related
        
        drawWindow();
//        latRange = (int)((maxLat-minLat)*scale1);// calculate the size of the window
//        lonRange = (int)((maxLon- minLon)*scale1);
//        System.out.println("SIZE OF WINDOW: " + lonRange + " " +latRange );
//        this.setSize(lonRange,latRange); // regular stuff for jframe 
//        
//        this.setTitle("Traffic Sim");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.add(new DrawStuff(), BorderLayout.CENTER);
//        this.setVisible(true);
        
        
    }
    
    public void setScale(double scale){
        this.scale = scale;
        roadShapes = new ArrayList<>();
        intersectShapes=new ArrayList<>();
        
        setMap();
        drawer.repaint();
        //drawWindow();
        
    }    
    //for transforming the long&lat to pixels
    

    int scale1 = 100000;
    
    
    public void stepCars(ArrayList<Auto> cars){
        this.cars=cars;
        drawer.repaint();
        //drawWindow();
    }
    
    public void stepCarsOLD(ArrayList<Auto> cars){
        carShapes = new ArrayList<Shape>();
        for(int i =0; i<cars.size();i++){
            Double x1=(cars.get(i).posNode.getLong()-minLon)*scale1;
            Double y1=(cars.get(i).posNode.getLat()-minLat)*scale1;
        
            Shape carPos = new Rectangle2D.Double(x1/scale-10,y1/scale-10,20,20);
            carShapes.add(carPos);
        }
//        drawer.repaint();
        drawWindow();
    }
    
    
    public void setMap(){

        for(int i = 0; i<roads.size();i++){

            for(int j = 0 ; j<roads.get(i).nodeList.size()-1;j++){


                Double y1=(roads.get(i).nodeList.get(j).getLat()-minLat)*scale1;
                Double x1=(roads.get(i).nodeList.get(j).getLong()-minLon)*scale1;
                Double y2=(roads.get(i).nodeList.get(j+1).getLat()-minLat)*scale1;
                Double x2=(roads.get(i).nodeList.get(j+1).getLong()-minLon)*scale1;
                
                Shape tempRoad = new Line2D.Double(x1/scale,y1/scale,x2/scale,y2/scale);
//                AlphaComposite alpha = new AlphaComposite();
                roadShapes.add(tempRoad);
                
            }
//            for(int j=0; j<roads.get(i).intersections.size();j++){
//                Double x1 = (roads.get(i).intersections.get(j).getLong()-minLon)*scale1;
//                Double y1 = (roads.get(i).intersections.get(j).getLat()-minLat)*scale1;
//                
//
//                Shape tempCept = new Rectangle2D.Double(x1/scale,y1/scale,5,5);
//                intersectShapes.add(tempCept);
//            }
        }
        
//        System.out.println("minLon"+minLon);
//        System.out.println("maxLon"+maxLon);
//        System.out.println("minLat"+minLat);
//        System.out.println("maxLat"+maxLon);
    }
    
    
    Shape testCar = new Rectangle2D.Double(0,0,0,0);
    public void setAutoPos(Nd node){
        Double x1=(node.getLong()-minLon)*scale1;
        Double y1=(node.getLat()-minLat)*scale1;
        
        testCar = new Rectangle2D.Double(x1/scale-10,y1/scale-10,20,20);
        
        drawWindow();
        
//        System.out.println("STEP");
    }

    
    // I was using these to test some things but a few of the functions
    // rely on them now. need to fix this shit so they are not global. 
    double minLat=0;
    double minLon=0;
    double maxLon=0;
    double maxLat =0;
    int latRange=0;
    int lonRange=0;
    
    
    //Graphics2D graph2;
    
    
    // this is the constructor... i was using it as a spot to 
    // test code in the class.it takes the values for max and min borders of
    // the whole map as parameters.
    
    
    private void updateFrame(){
        
    }
    
    boolean nodeButtonOn=false;
    public void drawWindow(){
        latRange = (int)((maxLat-minLat)*scale1);// calculate the size of the window
        lonRange = (int)((maxLon- minLon)*scale1);
        System.out.println("SIZE OF WINDOW: " + lonRange/scale + " " +latRange/scale );
        this.setSize(lonRange,latRange); // regular stuff for jframe 
 
        this.setTitle("Traffic Sim");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(drawer, BorderLayout.CENTER);
        
        
        JPanel toolPanel = new JPanel();
        
        JButton pauseButton = new JButton("||");
        pauseButton.setPreferredSize(new Dimension(65, 20));
        pauseButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
//                nodeInfoFrame=new NodeInfoFrame();
                playSpeed=0;
            }
        });
        
        JButton playButton = new JButton(">");
        playButton.setPreferredSize(new Dimension(65, 20));
        playButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                playSpeed=1;
//                nodeInfoFrame=new NodeInfoFrame();
            }
        });
        JButton fastPlayButton = new JButton(">>>");
        fastPlayButton.setPreferredSize(new Dimension(65, 20));
        fastPlayButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                playSpeed++;
//                nodeInfoFrame=new NodeInfoFrame();
            }
        });
        
        JButton nodeToolButton = new JButton("Node");
        nodeToolButton.setPreferredSize(new Dimension(65, 20));
        nodeToolButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                nodeInfoFrame=new NodeInfoFrame();
                if(nodeButtonOn){
                    nodeButtonOn=false;
                }else nodeButtonOn=true;
            }
        });
        
        JButton carToolButton = new JButton("Car");
        carToolButton.setPreferredSize(new Dimension(65, 20));
        carToolButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new CarInfoFrame();
            }
        });

        JButton optionsButton = new JButton("Options");
        optionsButton.setPreferredSize(new Dimension(80, 20));
        optionsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new OptionsFrame();
            }
        });
        
//        this.add(carToolButton, BorderLayout.NORTH);  
        toolPanel.add(pauseButton);
        toolPanel.add(playButton);
        toolPanel.add(fastPlayButton);
//        toolPanel.add(nodeToolButton);
//        toolPanel.add(carToolButton);
//        toolPanel.add(optionsButton);
        toolPanel.setPreferredSize(new Dimension(300,24));
        toolPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,1));
        this.add(toolPanel, BorderLayout.NORTH);
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        
        //ugh, add these buttons to a frame and then put on north of thie frame
        
        this.setVisible(true);
        

    }
    

    
    // this draws stuff
    // 
        boolean nodeSearchPlease=false;
    private class MouseHandler implements MouseListener, MouseMotionListener{
        


        public void mouseClicked(MouseEvent e) {
//            System.out.println("clicked at, X="+e.getX()+" Y="+e.getY());
            mouseLong=(e.getX()*scale)/scale1+minLon;
            mouseLat=(e.getY()*scale)/scale1+minLat;

            
            if(nodeButtonOn){
                nodeSearchPlease = true;
            }
            
        }

  
        public void mousePressed(MouseEvent e) {
     
        }


        public void mouseReleased(MouseEvent e) {
         
        }


        public void mouseEntered(MouseEvent e) {
       
        }


        public void mouseExited(MouseEvent e) {
   
        }


        public void mouseDragged(MouseEvent e) {

        }

        public void mouseMoved(MouseEvent e) {
            
        }
        
    }
    
    private class DrawStuff extends JComponent{
        public void paint(Graphics g){
            Graphics2D graph2 = (Graphics2D)g;
            

//            graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                    RenderingHints.VALUE_ANTIALIAS_ON);

            
            graph2.setPaint(Color.BLACK);

//            BufferedImage test = new BufferedImage(graph2.);
            for(int i =0; i<roadShapes.size();i++){
                
                graph2.draw(roadShapes.get(i));

                
            }
            
            graph2.setPaint(Color.GREEN);
            
//            for(int i =0; i<carShapes.size();i++){
//                
//                graph2.draw(carShapes.get(i));
//
//                
//            }

//            carShapes = new ArrayList<Shape>();
            for(int i =0; i<cars.size();i++){
                Double x1=(cars.get(i).posNode.getLong()-minLon)*scale1;
                Double y1=(cars.get(i).posNode.getLat()-minLat)*scale1;
        
                Shape carPos = new Rectangle2D.Double(x1/scale-7,y1/scale-7,14,14);
 //               carShapes.add(carPos);
                graph2.draw(carPos);
            }


            
//            for(int i=0; i<intersectShapes.size();i++){
//                graph2.setPaint(Color.RED);
//                graph2.draw(intersectShapes.get(i));
// //               System.out.println("*********************************************************DRAWING INTERCEPT");
// //               System.out.println("INTERSECT X: "+intersectShapes.get(i).getBounds().x);
// //               System.out.println("INTERSECT Y: "+intersectShapes.get(i).getBounds().y);
//            }
            
            //TEST CAR
//            graph2.setPaint(Color.GREEN);
//            graph2.draw(testCar);
            
//            System.out.println("SIZE OF WINDOW: " + (maxLon*100000-minLon*100000) + " " +(maxLat*100000-minLat*100000) );
            graph2.dispose();
            g.dispose();
            
        }

        public void paintComponent(Graphics g){
            Graphics2D graph2 = (Graphics2D)g;
            

//            graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                    RenderingHints.VALUE_ANTIALIAS_ON);

            
//            graph2.setPaint(Color.BLACK);
//
////            BufferedImage test = new BufferedImage(graph2.);
//            for(int i =0; i<roadShapes.size();i++){
//                
//                graph2.draw(roadShapes.get(i));
//
//                
//            }
            
            graph2.setPaint(Color.GREEN);
            
//            for(int i =0; i<carShapes.size();i++){
//                
//                graph2.draw(carShapes.get(i));
//
//                
//            }

//            carShapes = new ArrayList<Shape>();
            for(int i =0; i<cars.size();i++){
                Double x1=(cars.get(i).posNode.getLong()-minLon)*scale1;
                Double y1=(cars.get(i).posNode.getLat()-minLat)*scale1;
        
                Shape carPos = new Rectangle2D.Double((x1/scale-10),(y1/scale-10),20/scale,20/scale);
 //               carShapes.add(carPos);
                graph2.draw(carPos);
            }


            
//            for(int i=0; i<intersectShapes.size();i++){
//                graph2.setPaint(Color.RED);
//                graph2.draw(intersectShapes.get(i));
// //               System.out.println("*********************************************************DRAWING INTERCEPT");
// //               System.out.println("INTERSECT X: "+intersectShapes.get(i).getBounds().x);
// //               System.out.println("INTERSECT Y: "+intersectShapes.get(i).getBounds().y);
//            }
            
            //TEST CAR
//            graph2.setPaint(Color.GREEN);
//            graph2.draw(testCar);
            
//            System.out.println("SIZE OF WINDOW: " + (maxLon*100000-minLon*100000) + " " +(maxLat*100000-minLat*100000) );
            graph2.dispose();
            g.dispose();
            
        }

        
        
        
        
    }
    
    
}