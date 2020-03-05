
//Airi Shimamura
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
    private final Card[] hand;
    
    //constructor
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
    //getter
    public int getHandSize(){
        return HandSize;
    }   
    
    public void setHandIndex(int index, Card card){
        this.hand[index] = card;
    }
//    public void setHand( newHand){
//        this.hand = newHand;
//    }
    //will sort this.hand first by color, and then by face value
    public void sortHand(){
        this.sortType(0, new Hand(), 0);
        this.sortColor(0, new Hand(), 0);
    }
    //recursive function that will sort by color
    public void sortColor(int color, Hand newHand, int handIndex){
            Card[] tempHand = getHand();
            int i = 0;
            while(i < newHand.getHandSize()){
                if(tempHand[i] != null && tempHand[i].getColor() == Card.Color.getColor(color)){
                    newHand.setHandIndex(handIndex, tempHand[i]);
                    handIndex++;
                }
                i++;
            }
            //sorts by the next color if there is another one
            if(color < 4) sortColor(color+1, newHand, handIndex);
            else{
                for(int x = 0; x < newHand.getHandSize(); x++){
                    this.setHandIndex(x, newHand.getHand()[x]);
                }
            }
    }
    //recursive for setting by type
    public void sortType(int type, Hand newHand, int index){
            Card[] tempHand = getHand();
            int i = 0;
            while(i < newHand.getHandSize()){
                if(tempHand[i] != null && (tempHand[i].getType() == Card.Type.getType(type))){
                    newHand.setHandIndex(index, tempHand[i]);
                    index++;
                }
                i++;
            }

            if(type < 14) sortType(type+1, newHand, index);
            else{
                for(int x = 0; x < newHand.getHandSize(); x++){
                    this.setHandIndex(x, newHand.getHand()[x]);
                }
            }
    }        
}

