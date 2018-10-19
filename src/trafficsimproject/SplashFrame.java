/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author paleo
 */
public class SplashFrame extends JFrame{
    
    boolean completed = false;
    String fileName=("map.pro");
    int carTotal = 5000;
    boolean loadCarFile =true;
    boolean parseMap =false;
    boolean saveCars=false;


//        openPro("map.pro");
//        carTotal=5000;
//        loadCarFile=true;
    SplashFrame(){
        
        this.setSize(440,300); // regular stuff for jframe 
//        this.setDefaultCloseOperation(0);
//        this.setUndecorated(true);

        this.setTitle("Traffic Sim Menu");
//        this.setLocation(0,55);
//        this.setLayout(new BorderLayout());
        this.setLayout(new GridLayout());

        JButton loadMapButton = new JButton("Load Map");
        loadMapButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".pro files", "pro");
                chooser.setFileFilter(filter);

                int returnVal = chooser.showOpenDialog(chooser);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getAbsolutePath());
                    fileName=chooser.getSelectedFile().getAbsolutePath();
                }
//                            System.out.println("??? "+completed);
            }
        });
        
        
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                completed=true;
                
//                            System.out.println("??? "+completed);
            }
        });
        
        JButton parseMapButton = new JButton("Parse Map");
        parseMapButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".osm files", "osm","osmPRO");
                chooser.setFileFilter(filter);
                
                int numCars = 0;
                    boolean notVerif = true;
                    while(notVerif){
                        String str=JOptionPane.showInputDialog("Please input number of cars: ");
                        try{
                            numCars=Integer.parseInt(str);
                            notVerif=false;
                        }catch(Exception ex){
                            
                        }
                    }
                int returnVal = chooser.showOpenDialog(chooser);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getAbsolutePath());
                    fileName=chooser.getSelectedFile().getAbsolutePath();
                    
                    // USE THIS SHIT
                    System.out.println("name to make prjectL "+fileName.substring(0,fileName.length()-4));
                    parseMap=true;
                    
                    
                    
                    carTotal=numCars;
                    saveCars=true;
                    completed=true;
                }

                
                
//                            System.out.println("??? "+completed);
            }
        });
        
        
       
        JPanel loadPanel = new JPanel();
        
        loadPanel.add(loadMapButton);
        loadPanel.add(parseMapButton);
        loadPanel.add(startButton);

        
        this.add(loadPanel);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }
    
    String openPro(){
        String proName="";
        
        return proName;
    }
    
    String openMap(){
        String openName="";
        
        return openName;
    }
    
    public boolean isDone(){
        if(completed==true){
            return true;
        }
        return false;
    }
}
