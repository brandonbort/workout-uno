/*
 * AUTHOR: Brandon Bort
 * FOR: CS 2365
 * To change this template file, choose Tools | Templates
 */

package workoutuno;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
/**
 *
 * Author: BRANDON BORT
 */
public class UserInterface {

    public static int[] getOptions(){
        int options[] = new int[3];
        JFrame parFrame = new JFrame();
//        FlowLayout layout = new FlowLayout();
        
        //creating a user interface 
        JPanel child = new JPanel();
        JPanel radios = new JPanel();
        JRadioButton one = new JRadioButton("1");
        JRadioButton two = new JRadioButton("2");
        JRadioButton three = new JRadioButton("3");
        radios.setBorder(BorderFactory.createTitledBorder("Number of Decks"));
        
        //adding creating radio buttons to the button groups
        radios.add(one);
        radios.add(two);
        radios.add(three);
        
        //button group for options for shuffling
        JPanel shuffleOpts = new JPanel(new GridLayout(2, 2));
        JRadioButton alone = new JRadioButton("Shuffle Decks individually");
        JRadioButton together = new JRadioButton("Shuffle decks together");
        shuffleOpts.add(alone);
        shuffleOpts.add(together);
//        shuffleOpts.setLayout(layout);
        
        JPanel finalOpts = new JPanel(new GridLayout(2,2));
        JCheckBox inclAct = new JCheckBox("Include action cards", true);
        JButton okayBtn = new JButton("Okay");
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

        
        return options;
    }
    
}
