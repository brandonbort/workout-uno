/*
 * AUTHOR: Brandon Bort
 * FOR: CS 2365
 * To change this template file, choose Tools | Templates
 */
package workoutuno;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import static java.lang.System.exit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brand
 */
public class WorkoutUno {
        
    static int exerciseTotal, totalSkipped, maxSquat, maxPushup, maxSitup, maxLunge, maxBurpees = 0;
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
        Deck deck2 = new Deck();
        Deck deck3 = new Deck();
        deck1 = addToDeck(deck1, options[2]);
       
        //when the deck options is 2 or 3, add one or two decks
        if(options[0] > 1) {
           deck2 = addToDeck(deck2, options[2]);
           
           if(options[0] == 3){
              deck3 = addToDeck(deck3, options[2]);
           }
       } 
        
        //based on if the user selected to shuffle decks together, takes number
        //of decks and adds an equal number into one giant deck
        
        if(options[1] == 1){
            for(int i = 1; i < options[0]; i++){
                deck1.pushDeck(addToDeck(new Deck(), options[2]));
            }
        }
        String outputString = new String();
        Hand hand = new Hand();
        //drawHand works, just need shuffle function to work
        drawHand(hand, deck1);
        System.out.print("Hand : ");
        for(int i = 0; i < hand.getHandSize(); i++){
            if(i != hand.getHandSize()-1) System.out.print(hand.getHand()[i] + ", ");
            else System.out.print(hand.getHand()[i] + "\n");
        }
        int y=0,skipNumber=0,breakTime=0;   //color workout& skip counter& 0 break time
        int z[]={0,0,0,0,0};                //set all workouts to 0
        
        for(int i = 0; i < hand.getHandSize(); i++){
                    switch (hand.getHand()[i].getColor()) {
                    case Green:
                        y=0;
                        break;
                    case Blue:
                        y=1;
                        break;
                    case Red:
                        y=2;
                        break;
                    case Yellow:
                        y=3;
                        break;
                    case Black:
                        y=4;
                        break;
                    }
                    switch (hand.getHand()[i].getType()) {
                    case Zero:
                        breakTime+=1;
                        z[y]+=0;            //add zero?
                        break;
                    case One:
                        z[y]+=1;
                        break;
                    case Two:
                        z[y]+=2;
                        break;
                    case Three:
                        z[y]+=3;
                        break;
                    case Four:
                        z[y]+=4;
                        break;
                    case Five:
                        z[y]+=5;
                        break;
                    case Six:
                        z[y]+=6;
                        break;
                    case Seven:
                        z[y]+=7;
                        break;
                    case Eight:
                        z[y]+=8;
                        break;
                    case Nine:
                        z[y]+=9;
                        break;
                    case Skip:
                        skipNumber=z[y];        //count how many there r to skip
                        z[y]=0;                 //nark all of this workout
                        break;
                    case Draw2:
                        z[y]*=2;                //buff all of this workout
                        break;
                    case Wild:
                        z[y]+=10;               //add burpees to this hoe
                        break;
                    case Wild4:
                    {
                     z[y]+=10;
                     z[0]*=4;                   //multiply all workouts by 4
                     z[1]*=4;
                     z[2]*=4;
                     z[3]*=4;
                    }
                        break;
                    case Reverse:
                        z[y]=0;                 //ill tweak this up to push car to stack
                        break;       
                }
        }
        
        System.out.println("\nLunges:        " +z[0]);
        System.out.println("Push-Ups:      " +z[1]);
        System.out.println("Sit-Ups:       " +z[2]);
        System.out.println("Squats:        " +z[3]);
        System.out.println("Burpees:       " +z[4]);
        System.out.println("Break Time:    " +breakTime);
        
        
         //comment this out bec its annoying for now
        System.out.println("\n Number of cards remaining in deck: " +deck1.getCardCount());
        shuffle(deck1);

        while(deck1.head != null){
           
            drawHand(hand, deck1);
            hand.sortHand();

            outputString += stringifyHand(hand) + "<br/>Cards remaining in deck: " +
                            deck1.getCardCount() + "<br/>";
        }
        try {
            outputToHtml(outputString);
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find output file.");
        }

        exit(0);
            
    }
    
    public static String stringifyHand(Hand hand){
        String handString = new String();
        for(int i = 0; i < hand.getHandSize(); i++){
            if(i < hand.getHandSize()-1 && hand.getHand()[i+1] != null) 
                handString += hand.getHand()[i] + ", ";
            else if(i == hand.getHandSize()-1 || hand.getHand()[i+1] == null) {
                handString += hand.getHand()[i] + "\n";
                break;
            }
        }
        return handString;
    }
    
    public static void shuffle(Deck deck){
        Card[] map = new Card[deck.getCardCount()];
        int cardCount = deck.getCardCount();
        for(int i = 0; i < cardCount; i++){
            map[i] = deck.pop(); //adds all cards from the deck to temporary array
        }
        
        for(int i = 0; i < cardCount; i++){
            int randomX = (int)(Math.random() * cardCount);
            Card temp = map[randomX];
            map[randomX] = map[i];
            map[i] = temp;
        }
     
        for(int i = 0; i < cardCount; i++){
            deck.push(map[i]);
        }
    }
    //BRBORT- this will accept a hand and output it to our chosen html file
    public static void outputToHtml(String output) throws FileNotFoundException{
        PrintStream outputStream = new PrintStream("output.html");
        outputStream.println(output);
        outputStream.close();
    }
    //BRBORT- created drawHand function
    //This function will pop the card off the top of the passed deck and add it 
    //to the hand until the hand has a full 7 cards
    //-functionality written by airishimamura
    public static void drawHand(Hand hand, Deck deck){
        int incrementor;
        //clears the previous hand since the same hand will be used everytime
        for(int i = 0; i < hand.getHandSize(); i++){
            hand.setHandIndex(i, null);
        }
        if(deck.getCardCount() < 7)incrementor = deck.getCardCount();
        else incrementor = hand.getHandSize();
        for (int i = 0; i < incrementor; i++){
            if(deck.head.card != null){
                hand.setHandIndex(i, deck.pop());
            }
        }
    }
    //BRBORT- Created addToDeck() function
    //Accepts a deck and the user's choice whether to include action cards or not
    //and populates the deck with cards based on that choice
    //if inclAct == 0, it will include action cards
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
            //theres four of each wild card and plus4 so iterate 4 times
            for(int i = 0; i < 4; i++){
                deck.push(wildCard);
                deck.push(wildPlusFour);
            }
        }
        return deck;
    }
}
