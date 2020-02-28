/*
 * AUTHOR: Brandon Bort
 * FOR: CS 2365
 * To change this template file, choose Tools | Templates
 */
package workoutuno;

/**
 *
 * @author brand
 */
public class WorkoutUno {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        
    Deck deck1 = new Deck();
    Hand hand1 = new Hand();
    //draw 7 cards from the deck and store them in array 
    for (int i = 0; i < hand1.getHandSize(); i++){
        if(deck1.head != null){
            hand1.hand[i] = deck1.pop();
        }
    }
    
    }
    
}
