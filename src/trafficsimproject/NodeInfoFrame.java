/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author paleo
 */
public class NodeInfoFrame extends JFrame{
    Nd node;
    
    NodeInfoFrame(){

        this.setSize(400,700); // regular stuff for jframe 
        
        this.setTitle("Node Info");
//        this.setLayout(new BorderLayout());
this.setLayout(new GridLayout());

        JLabel refLabel = new JLabel("ref:");
        JLabel longLabel = new JLabel("long:");
        JLabel latLabel = new JLabel("lat:");
        JLabel conLabel = new JLabel("connects:");
        JLabel carsLabel = new JLabel("cars:");
        
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        displayPanel.add(refLabel);
        displayPanel.add(longLabel);
        displayPanel.add(latLabel);
        this.add(displayPanel, BorderLayout.WEST);

        JPanel conPanel = new JPanel();
        conPanel.add(conLabel);

        JPanel carPanel = new JPanel();
        carPanel.add(carsLabel);

        this.add(conPanel,BorderLayout.CENTER);
        this.add(carPanel,BorderLayout.CENTER);
        
        
//        this.add(refLabel);
//        JButton nodeToolButton = new JButton("delete");
//        nodeToolButton.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
////                new NodeInfoFrame();
//            }
//        });
//        this.add(nodeToolButton, BorderLayout.NORTH);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }
    
    // use this to give window the Node 
    // once you got the node fill all data about it
    // in this window.
    public void setNode(Nd node){
        this.node=node;
    }
    
    //functions to edit the node
    //make functions to cycle through its connections.
    //maybe open another window that has info on cars connected to a node.
    //display graphs and charts
    
}
