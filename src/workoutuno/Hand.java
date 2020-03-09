
//Airi Shimamura
/*FOR: CS 2365S
 * COLLABORATOR: Brandon Bort
 * CONTRIBUTION: constructor, sorting methods for color and type
 * Description: Hand class created as an array instantiation of Cards with unique
 * functions pertaining to necessary code execution
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

    /**
     *Set up the maximum size of the hand size
     */
    public Hand(){
        this.HandSize = 7;
        this.hand = new Card[7];
    }
    //getter

    /**
     *To get values of hand size
     * @return: the size of the hand
     */
    public int getHandSize (){
        return HandSize;
    }   
    
    /**
     *To get the values of the card data 
     * @return: the card data in hand 
     */
    public Card[] getHand(){
        return hand;
    }
    
    /**
     *To set up the hand index 
     * @param index:index of array for hand 
     * @param card: card data(color & face value)
     */
    public void setHandIndex(int index, Card card){
        this.hand[index] = card;
    }
//    public void setHand( newHand){
//        this.hand = newHand;
//    }
    //will sort this.hand first by color, and then by face value

    /**
     *To change the order of hand cards
     */
    public void sortHand(){
        this.sortType(0, new Hand(), 0);
        this.sortColor(0, new Hand(), 0);
    }
    //recursive function that will sort by color

    /**
     *To change the color order if cards 
     * @param color: card color 
     * @param newHand:  new hand object 
     * @param handIndex: index of array for hand
     */
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

    /**
     *TO change order of cards by face value 
     * @param type: face value
     * @param newHand: new hand object 
     * @param index: index of the hand array 
     */
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
