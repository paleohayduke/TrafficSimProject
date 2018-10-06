/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
        JTextField speedField = new JTextField(5);
        
        JTextArea carArea = new JTextArea(7,12);
        JTextArea conArea = new JTextArea(7,12);
        
        JRadioButton stopYes = new JRadioButton("true");
        JRadioButton stopNo = new JRadioButton("false");        
        ButtonGroup stopButtons = new ButtonGroup();
        
        
        
    NodeInfoFrame(){

        this.setSize(500,300); // regular stuff for jframe 
//        this.setDefaultCloseOperation(0);
//        this.setUndecorated(true);

        this.setTitle("Node Info");
//        this.setLocation(0,55);
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
        
        JLabel speedLabel = new JLabel("speed:");
        speedField = new JTextField(10);
        
        JLabel speedEditLabel = new JLabel("edit speed");
        JTextField speedEditField = new JTextField(10);
        speedEditField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                node.speedLimit=Double.parseDouble(speedEditField.getText());
            }
        });
        
        displayPanel.add(longLabel);
        displayPanel.add(longField);
        displayPanel.add(latLabel);
        displayPanel.add(latField);
        displayPanel.add(speedLabel);
        displayPanel.add(speedField);
        displayPanel.add(speedEditLabel);
        displayPanel.add(speedEditField);
        
        this.add(displayPanel, BorderLayout.WEST);

        JPanel conPanel = new JPanel();
        conPanel.add(conLabel);
        conArea.setWrapStyleWord(true);
        conPanel.add(conArea);

        JPanel carPanel = new JPanel();
        carPanel.add(carsLabel);
        carPanel.add(carArea);
        JLabel stopLabel = new JLabel("StopNode? ");
        stopYes = new JRadioButton("true");
        stopNo = new JRadioButton("false");
        stopYes.addActionListener(new RadioButtonListener());
        stopNo.addActionListener(new RadioButtonListener());
        
        stopButtons = new ButtonGroup();
        stopButtons.add(stopYes);
        stopButtons.add(stopNo);        
        JPanel stopPanel = new JPanel();
        stopPanel.add(stopLabel);
        stopPanel.add(stopYes);
        stopPanel.add(stopNo);
        this.add(conPanel,BorderLayout.CENTER);
        this.add(carPanel,BorderLayout.CENTER);
        this.add(stopPanel,BorderLayout.SOUTH);        
        
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
            output=output+"  "+Long.toString(node.connections.get(i).getRef())+"\n";
        }
        conArea.setText(output);
        
        output="";
        for(int i=0;i<node.cars.size();i++){
            output=output+"  "+Long.toString(node.cars.get(i).getRef())+"\n";
      
        }
        carArea.setText(output);
        
        if(node.isStop){
            stopButtons.setSelected(stopYes.getModel(), true);

            
        }else{
            stopButtons.setSelected(stopNo.getModel(), true);
            
        }
        
        speedField.setText(Double.toString(node.speedLimit));
        
    }
    //functions to edit the node
    //make functions to cycle through its connections.
    //maybe open another window that has info on cars connected to a node.
    //display graphs and charts
    
    private class RadioButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==stopYes){
                node.isStop=true;
            }
            if(e.getSource()==stopNo ){
                node.isStop=false;
            }
        }
        
    }
    
}
