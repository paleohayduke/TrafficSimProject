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
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author paleo
 */
public class NodeInfoFrame extends JFrame{
    Nd node= new Nd();
        JTextField refField = new JTextField(10);
        JTextField longField = new JTextField(10);
        JTextField latField = new JTextField(10);
        JTextArea carArea = new JTextArea(7,12);
        JTextArea conArea = new JTextArea(7,12);
    NodeInfoFrame(){

        this.setSize(420,200); // regular stuff for jframe 
        
        this.setTitle("Node Info");
//        this.setLayout(new BorderLayout());
this.setLayout(new GridLayout());

        JLabel refLabel = new JLabel("ref:");
        refField = new JTextField(10);
        JLabel longLabel = new JLabel("long:");
        longField = new JTextField(10);
        JLabel latLabel = new JLabel("lat:");
        latField = new JTextField(10);
        JLabel conLabel = new JLabel("connects:");
        JLabel carsLabel = new JLabel("cars:");
        
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        displayPanel.add(refLabel);
        displayPanel.add(refField);
        
        
        displayPanel.add(longLabel);
        displayPanel.add(longField);
        displayPanel.add(latLabel);
        displayPanel.add(latField);
        this.add(displayPanel, BorderLayout.WEST);

        JPanel conPanel = new JPanel();
        conPanel.add(conLabel);
        conArea.setWrapStyleWord(true);
        conPanel.add(conArea);

        JPanel carPanel = new JPanel();
        carPanel.add(carsLabel);
        carPanel.add(carArea);

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
    
    
//            JTextField refField = new JTextField(6);
//        JTextField longField = new JTextField(6);
//        JTextField latField = new JTextField(6);
    public void setFields(){

        refField.setText( Long.toString(node.getRef()));
        longField.setText(Double.toString(node.getLong()));
        latField.setText(Double.toString(node.getLat()));
        String output = "";
        for(int i=0;i<node.connections.size();i++){
            output=output+Long.toString(node.connections.get(i).getRef())+"\n";
        }
        conArea.setText(output);
        
        output="";
        for(int i=0;i<node.cars.size();i++){
            output=output+Long.toString(node.cars.get(i).getRef())+"\n";
      
        }
        carArea.setText(output);
    }
    //functions to edit the node
    //make functions to cycle through its connections.
    //maybe open another window that has info on cars connected to a node.
    //display graphs and charts
    
}
