/*
 * AUTHOR: Brandon Bort
 * FOR: CS 2365
 * To change this template file, choose Tools | Templates
 */
package workoutuno;

import static java.lang.System.exit;

/**
 *
 * @author brand
 */
public class WorkoutUno {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
       UserInterface ui = new UserInterface();
       int[] options = new int[3];
       Object lock = new Object(); //lock object will pause thread until user enters input in GUI
       ui.getOptions(options, lock);//passes the array to populate with choices, and lock that pauses the thread
       synchronized(lock) {
            try {
                // Calling wait() will block this thread until user presses the
                // okayBtn in the UI
                lock.wait();
            } catch (InterruptedException e) {
            // Happens if someone interrupts the lock thread
            }
       }
       //options[0] will return the number of decks the user selected
       //options[1] will return a 0 if the user selcted to shuffle the decks individually
       //options[1] will return a 1 if the user selected to shuffle the decks together
       //options[2] will return a 0 if the user selected to include action cards,
       //or a 1 if they chose not to
       exit(0);
        
        
        
//        Deck deck1 = new Deck();
//        Hand hand1 = new Hand();
//        //draw 7 cards from the deck and store them in array 
//        for (int i = 0; i < hand1.getHandSize(); i++){
//            if(deck1.head != null){
//                hand1.hand[i] = deck1.pop();
//            }
//        }
    
    }
    
    public static void shuffle(){

    }

    
}
