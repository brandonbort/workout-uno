/*
 * AUTHOR: Brandon Bort
 * FOR: CS 2365
 * To change this template file, choose Tools | Templates
 */

package workoutuno;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * Author: BRANDON BORT
 */
class UserInterface extends JFrame{
    
    public void getOptions(int[] options, Object lock){

        UserInterface parFrame = new UserInterface();
        parFrame.setLocationRelativeTo(null);//pops the ui in the center of screen
        
        //creating a user interface 
        JPanel child = new JPanel();
        JPanel radios = new JPanel();
        ButtonGroup radioGroup = new ButtonGroup();
        JRadioButton one = new JRadioButton("1");
        JRadioButton two = new JRadioButton("2");
        JRadioButton three = new JRadioButton("3");
        radios.setBorder(BorderFactory.createTitledBorder("Number of Decks"));
        
        //adding the buttons to the radio group insures only one can be selected at a time
        radioGroup.add(one);
        radioGroup.add(two);
        radioGroup.add(three);
        radioGroup.setSelected(one.getModel(), true); //sets button one as default selected to prevent errors
        radios.add(one);
        radios.add(two);
        radios.add(three);
        
        //button group for options for shuffling
        JPanel shuffleOpts = new JPanel(new GridLayout(2, 2));
        ButtonGroup shuffleGroup = new ButtonGroup();
        JRadioButton alone = new JRadioButton("Shuffle Decks individually");
        JRadioButton together = new JRadioButton("Shuffle decks together");
        
        shuffleGroup.add(alone);
        shuffleGroup.add(together);
        //sets shuffling alone as the default option
        shuffleGroup.setSelected(alone.getModel(), true);
        shuffleOpts.add(alone);
        shuffleOpts.add(together);
                
        JPanel finalOpts = new JPanel(new GridLayout(2,2));
        JCheckBox inclAct = new JCheckBox("Include action cards", true);
        JButton okayBtn = new JButton("Okay");
        //sets the Okay button to get the results from the UI

        okayBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
              if(e.getSource() == okayBtn)  {

                if(radioGroup.getSelection() == one.getModel()) options[0] = 1;
                else if(radioGroup.getSelection() == two.getModel()) options[0] = 2;
                else if(radioGroup.getSelection() == three.getModel()) options[0] = 3;
                //sets to 0 if the user selected to shuffle each deck alone, or 1 if together
                if(shuffleGroup.getSelection() == alone.getModel()) options[1] = 0;
                else if(shuffleGroup.getSelection() == together.getModel()) options[1] = 1;
                //set to 0 if the user wants action cards included, or 1 if they don't
                if(inclAct.isSelected()) options[2] = 0;
                else options[2] = 1;
                synchronized(lock){
                    lock.notify();
                }//the main thread will resume upon the options array being populated
                
                parFrame.dispatchEvent(new WindowEvent(parFrame, WindowEvent.WINDOW_CLOSING));
              }
            }
        });
        finalOpts.add(inclAct);
        finalOpts.add(okayBtn);
        
        child.add(radios, BorderLayout.NORTH);
        child.add(shuffleOpts);
        child.add(finalOpts);
            
        parFrame.add(child);
        
        //making the UI visible
        ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "\\utility\\uno-logo.png");
        parFrame.setIconImage(img.getImage());
        parFrame.setVisible(true);
        parFrame.setSize(200,225);
        
        parFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
