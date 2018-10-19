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
                ".pro files", "pro","osm");
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
        
       
        
        this.add(loadMapButton);
        this.add(startButton);
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
