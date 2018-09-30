/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author paleo
 */
public class NodeInfoFrame extends JFrame{
    Nd node;
    
    NodeInfoFrame(){

        this.setSize(400,700); // regular stuff for jframe 
        
        this.setTitle("Node Info");
        
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
