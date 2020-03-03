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
        
        Deck deck1 = new Deck();
        deck1 = addToDeck(deck1, options[2]);
        //based on if the user selected to shuffle decks together, takes number
        //of decks and adds an equal number into one giant deck
        if(options[1] == 1){
            for(int i = 1; i < options[0]; i++){
                deck1.pushNode(addToDeck(new Deck(), options[2]).head);
            }
        }
        while(deck1.head != null){
            System.out.println(deck1.pop());
        }
        Hand hand = new Hand();
        //drawHand works, just need shuffle function to work
        drawHand(hand, deck1);
//        System.out.print("Hand : ");
//        for(int i = 0; i < hand.getHandSize(); i++){
//            if(i != hand.getHandSize()-1) System.out.print(hand.hand[i] + ", ");
//            else System.out.print(hand.hand[i]);
//        }
            
    }
    
    public static void shuffle(Deck deck){
        //This should be a hint to get you started Brittney - BRBORT
//        Deck.Node temp = deck.head;
//        int randomX = (int) (Math.random() * 10 + 1);
//
//        //simply go until the randomX
//        while(randomX --> 0 && temp.next != null)
//            temp = temp.next;
//
//        //remove the Nth node from the list
//
//        temp.getPrevious().setNext(temp.getNext());
//
//        if(temp.getNext() != null)
//            temp.getNext().setPrevious(temp.getPrevious());
//
//        //set it to point to the head
//        temp.setNext(head);
//        temp.setPrevious(null);
//
//        //now set the Head to the Nth node we found
//        head = temp;
    }
    
    public static void outputToHtml(Hand hand){
        
    }
    //BRBORT- created drawHand function
    //This function will pop the card off the top of the passed deck and add it 
    //to the hand until the hand has a full 7 cards
    //-functionality written by airishimamura
    public static void drawHand(Hand hand, Deck deck){
        for (int i = 0; i < hand.getHandSize(); i++){
            if(deck.head != null){
                hand.hand[i] = deck.pop();
            }
        }
    }
    //BRBORT- Created addToDeck() function
    //Accepts a deck and the user's choice whether to include action cards or not
    //and populates the deck with cards based on that choice
    public static Deck addToDeck(Deck deck, int inclAct){
        int maxSize;
       
        if(inclAct == 0) maxSize = 12;
        else maxSize = 9;
        //adds all types of all colors if action cards are selected to be included
        for(int colors = 0; colors < 4; colors++){
            for(int i = 0; i <= maxSize; i++){
                //theres only one zero of each color, so this accounts for that
                Card newCard = new Card(Card.Color.getColor(colors), Card.Type.getType(i));
                //will push twice if not a Zero
                if(newCard.getType() != Card.Type.Zero) deck.push(newCard);
                deck.push(newCard);
            }
        }
        //if user selected ot include action cards, adds the 4 of each wilds and plus fours
        if(inclAct == 0){
            Card wildCard = new Card(Card.Color.Black, Card.Type.Wild);
            Card wildPlusFour = new Card(Card.Color.Black, Card.Type.Wild4);
            for(int i = 0; i < 4; i++){
                deck.push(wildCard);
                deck.push(wildPlusFour);
            }
        }
        return deck;
    }
}
