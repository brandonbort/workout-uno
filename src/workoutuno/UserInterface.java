/*
 * AUTHOR: Brandon Bort
 * FOR: CS 2365
 * To change this template file, choose Tools | Templates
 */

package workoutuno;

import javax.swing.*;
/**
 *
 * Author: BRANDON BORT
 */
public class UserInterface {

    public static int[] getOptions(){
        int options[] = new int[3];
        JFrame parFrame = new JFrame();
        //creating a user interface 
//        ButtonGroup numOfDecks = new ButtonGroup();
        JFrame numOfDecks = new JFrame();
        JRadioButton one = new JRadioButton("1");
        JRadioButton two = new JRadioButton("2");
        JRadioButton three = new JRadioButton("3");
        //adding creating radio buttons to the button groups
        numOfDecks.add(one);
        numOfDecks.add(two);
        numOfDecks.add(three);
        parFrame.add(numOfDecks);
        
        //button group for options for shuffling
        JFrame shuffleOpts = new JFrame();
        JRadioButton alone = new JRadioButton("Shuffle Decks individually");
        JRadioButton together = new JRadioButton("Shuffle decks together");
        shuffleOpts.add(alone);
        shuffleOpts.add(together);
        parFrame.add(shuffleOpts);
       
        JCheckBox inclAct = new JCheckBox("Include action cards", true);
        parFrame.add(inclAct);
        
        
        
        
        return options;
    }
    
}
