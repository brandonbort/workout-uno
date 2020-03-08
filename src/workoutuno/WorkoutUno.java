/*
 * AUTHOR: Brandon Bort
 * FOR: CS 2365
 * To change this template file, choose Tools | Templates
 */
package workoutuno;

import static java.lang.System.exit;
import java.util.Arrays;

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
        
        
        /* //comment this out bec its annoying for now
        System.out.println("\n Number of cards remaining in deck: " +deck1.getCardCount());
        while(deck1.head != null){
            System.out.println(deck1.pop());
        }
        */
        //exerciseTotal, totalSkipped, maxSquat, maxPushup, maxSitup, maxLunge, maxBurpees
        exerciseTotal+=z[0]+z[1]+z[2]+z[3]+z[4];
        totalSkipped+=skipNumber;
        if (maxLunge<z[0])
            maxLunge=z[0];
        if (maxPushup<z[1])
            maxPushup=z[1];
        if (maxSitup<z[2])
            maxSitup=z[2];
        if (maxSquat<z[3])
            maxSquat=z[3];
        if (maxBurpees<z[4])
            maxBurpees=z[4];
        
        System.out.println("\nTotal Reps:    " +exerciseTotal);
        System.out.println("Total Skip:    " +totalSkipped);
        System.out.println("Max Lunge:     " +maxLunge);
        System.out.println("Max Push-Up:   " +maxPushup);
        System.out.println("Max Sit-Up:    " +maxSitup);
        System.out.println("Max Squat:     " +maxSquat);
        System.out.println("Max Burpee:    " +maxBurpees);
        
        exit(0);
            
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
    //BRBORT- this will accept a hand and output it to our chosen html file
    public static void outputToHtml(Hand hand){
        
    }
    //BRBORT- created drawHand function
    //This function will pop the card off the top of the passed deck and add it 
    //to the hand until the hand has a full 7 cards
    //-functionality written by airishimamura
    public static void drawHand(Hand hand, Deck deck){
        for (int i = 0; i < hand.getHandSize(); i++){
            if(deck.head != null){
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
