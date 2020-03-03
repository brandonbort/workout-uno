/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workoutuno;

/**
 *
 * @author airishimamura
 */
public class Hand {
    private final int HandSize;  //there is no setters because HandSize is constant value
    final Card[] hand;
    
    public Hand(){
        this.HandSize = 7;
        this.hand = new Card[7];
    }
    //getter
    public int getHandSize (){
        return HandSize;
    }   
    
    public Card[] getHand(){
        return hand;
    }
}
