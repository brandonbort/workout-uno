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

    static int exerciseTotal, totalLunge,totalPushup,totalSitup,totalSquat,totalBurpees,totalSkipped, maxSquat, maxPushup, maxSitup, maxLunge, maxBurpees = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        UserInterface ui = new UserInterface();
        int[] options = new int[3];
        Object lock = new Object(); //lock object will pause thread until user enters input in GUI
        ui.getOptions(options, lock);//passes the array to populate with choices, and lock that pauses the thread
        synchronized (lock) {
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
        if (options[0] > 1) {
            deck2 = addToDeck(deck2, options[2]);

            if (options[0] == 3) {
                deck3 = addToDeck(deck3, options[2]);
            }
        }

        //based on if the user selected to shuffle decks together, takes number
        //of decks and adds an equal number into one giant deck
        if (options[1] == 1) {
            for (int i = 1; i < options[0]; i++) {
                deck1.pushDeck(addToDeck(new Deck(), options[2]));
            }
        }
        String outputString = new String();
        Hand hand = new Hand();
        //drawHand works, just need shuffle function to work
        shuffle(deck1);

        while (deck1.head != null) {

            drawHand(hand, deck1);
            hand.sortHand();

            outputString += "<br/>Sorted Hand: " + stringifyHand(hand) + "<br/>Cards remaining in deck: "
                    + deck1.getCardCount() + getWorkout(hand, deck1) + "<br/>";
            if (deck1.head == null && deck2.head != null) {
                outputString += "<font size=\"+1\"></br>Deck1 Workout totals: </font><br/>" + 
                                "Lunges: " + maxLunge + "<br/>"+
                                "Pushups: " + maxPushup + "<br/>"+
                                "Situps: " + maxSitup + "<br/>" +
                                "Squats: " + maxSquat + "<br/>" +
                                "Burpees: " + maxBurpees + "<br/>"+
                                "<br/><font size=\"+2\">Deck 2!</font><br/>";
                while (deck2.head != null) {
                    deck1.push(deck2.pop());
                }
                shuffle(deck1);
            } else if (deck1.head == null && deck3.head != null) {
                outputString += "<font size=\"+1\"><br/>Deck 2 Workout totals: </font><br/>" + 
                                "Lunges: " + maxLunge + "<br/>"+
                                "Pushups: " + maxPushup + "<br/>"+
                                "Situps: " + maxSitup + "<br/>" +
                                "Squats: " + maxSquat + "<br/>" +
                                "Burpees: " + maxBurpees + "<br/>"+
                                "<br/><font size=\"+2\">Deck 3!</font><br/>";
                while (deck3.head != null) {
                    deck1.push(deck3.pop());
                }
                shuffle(deck1);
            }
        }
        try {
            outputToHtml(outputString);
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find output file.");
        }

        exit(0);

    }

    public static String stringifyHand(Hand hand) {
        String handString = new String();
        String colorCode = new String();
        for (int i = 0; i < hand.getHandSize(); i++) {
            if (hand.getHand()[i] != null) {
                if (hand.getHand()[i].getColor() == Card.Color.Green) {
                    colorCode = "<font color = \"green\">";
                } else if (hand.getHand()[i].getColor() == Card.Color.Red) {
                    colorCode = "<font color = \"red\">";
                } else if (hand.getHand()[i].getColor() == Card.Color.Blue) {
                    colorCode = "<font color = \"blue\">";
                } else if (hand.getHand()[i].getColor() == Card.Color.Yellow) {
                    colorCode = "<font color = \"yellow\">";
                } else if (hand.getHand()[i].getColor() == Card.Color.Black) {
                    colorCode = "<font color = \"black\">";
                }
            }
            if (i < hand.getHandSize() - 1 && hand.getHand()[i + 1] != null) {
                handString += colorCode + hand.getHand()[i].getType() + "</font>, ";
            } else if (i == hand.getHandSize() - 1 || hand.getHand()[i + 1] == null) {
                handString += colorCode + hand.getHand()[i].getType() + "</font>";
                break;
            }
        }
        return handString;
    }

    public static void shuffle(Deck deck) {
        Card[] map = new Card[deck.getCardCount()];
        int cardCount = deck.getCardCount();
        for (int i = 0; i < cardCount; i++) {
            map[i] = deck.pop(); //adds all cards from the deck to temporary array
        }

        for (int i = 0; i < cardCount; i++) {
            int randomX = (int) (Math.random() * cardCount);
            Card temp = map[randomX];
            map[randomX] = map[i];
            map[i] = temp;
        }

        for (int i = 0; i < cardCount; i++) {
            deck.push(map[i]);
        }
    }

    //BRBORT- this will accept a hand and output it to our chosen html file
    public static void outputToHtml(String output) throws FileNotFoundException {
        PrintStream outputStream = new PrintStream("output.html");
        outputStream.println(output);
        outputStream.close();
    }

    //BRBORT- created drawHand function
    //This function will pop the card off the top of the passed deck and add it 
    //to the hand until the hand has a full 7 cards
    //-functionality written by airishimamura
    public static void drawHand(Hand hand, Deck deck) {
        int incrementor;
        //clears the previous hand since the same hand will be used everytime
        for (int i = 0; i < hand.getHandSize(); i++) {
            hand.setHandIndex(i, null);
        }
        if (deck.getCardCount() < 7) {
            incrementor = deck.getCardCount();
        } else {
            incrementor = hand.getHandSize();
        }
        for (int i = 0; i < incrementor; i++) {
            if (deck.head.card != null) {
                hand.setHandIndex(i, deck.pop());
            }
        }
    }

    //BRBORT- Created addToDeck() function
    //Accepts a deck and the user's choice whether to include action cards or not
    //and populates the deck with cards based on that choice
    //if inclAct == 0, it will include action cards
    public static Deck addToDeck(Deck deck, int inclAct) {
        int maxSize;

        if (inclAct == 0) {
            maxSize = 12;
        } else {
            maxSize = 9;
        }
        //adds all types of all colors if action cards are selected to be included
        for (int colors = 0; colors < 4; colors++) {
            for (int i = 0; i <= maxSize; i++) {
                //theres only one zero of each color, so this accounts for that
                Card newCard = new Card(Card.Color.getColor(colors), Card.Type.getType(i));
                //will push twice if not a Zero
                if (newCard.getType() != Card.Type.Zero) {
                    deck.push(newCard);
                }
                deck.push(newCard);
            }
        }
        //if user selected ot include action cards, adds the 4 of each wilds and plus fours
        if (inclAct == 0) {
            Card wildCard = new Card(Card.Color.Black, Card.Type.Wild);
            Card wildPlusFour = new Card(Card.Color.Black, Card.Type.Wild4);
            //theres four of each wild card and plus4 so iterate 4 times
            for (int i = 0; i < 4; i++) {
                deck.push(wildCard);
                deck.push(wildPlusFour);
            }
        }
        return deck;
    }

    public static String getWorkout(Hand hand, Deck deck) {
        int color = 0, skipNumber = 0, breakTime = 0;   //color workout& skip counter& 0 break time
        int type[] = {0, 0, 0, 0, 0};                //set all workouts to 0
        String result = new String();
        for (int i = 0; i < hand.getHandSize(); i++) {
            if (hand.getHand()[i] != null) {
                switch (hand.getHand()[i].getColor()) {
                    case Green:
                        color = 0;
                        break;
                    case Blue:
                        color = 1;
                        break;
                    case Red:
                        color = 2;
                        break;
                    case Yellow:
                        color = 3;
                        break;
                    case Black:
                        color = 4;
                        break;
                }
                switch (hand.getHand()[i].getType()) {
                    case Reverse:
                        type[color] = 0;                 //ill tweak this up to push car to stack
                        break;
                    case Zero:
                        breakTime += 1;             //Time for breaks
                        type[color] += 0;            //add zero?
                        break;
                    case One:
                        type[color] += 1;
                        break;
                    case Two:
                        type[color] += 2;
                        break;
                    case Three:
                        type[color] += 3;
                        break;
                    case Four:
                        type[color] += 4;
                        break;
                    case Five:
                        type[color] += 5;
                        break;
                    case Six:
                        type[color] += 6;
                        break;
                    case Seven:
                        type[color] += 7;
                        break;
                    case Eight:
                        type[color] += 8;
                        break;
                    case Nine:
                        type[color] += 9;
                        break;
                    case Skip:
                        skipNumber += type[color];        //count how many there r to skip
                        type[color] = 0;                 //nark all of this workout
                        break;
                    case Draw2:
                        type[color] *= 2;                //buff all of this workout
                        break;
                    case Wild:
                        type[color] += 10;               //add burpees to this hoe
                        break;
                    case Wild4: {
                        type[color] += 10;
                        type[0] *= 4;                   //multiply all workouts by 4
                        type[1] *= 4;
                        type[2] *= 4;
                        type[3] *= 4;
                    }
                    break;
                    
                }
            }
        }
        result += "<br />Lunges:        " + type[0]
                + "<br />Push-Ups:      " + type[1]
                + "<br />Sit-Ups:       " + type[2]
                + "<br />Squats:        " + type[3]
                + "<br />Burpees:       " + type[4]
                + "<br />Break Time:    " + breakTime;
        exerciseTotal += type[0] + type[1] + type[2] + type[3] + type[4];
        totalSkipped += skipNumber;
        
        totalLunge = type[0];
        if (maxLunge <type[0])
            maxLunge = type[0];
        totalPushup = type[1];
        if (maxPushup <type[1])
            maxPushup = type[1];
        maxSitup = type[2];
        maxSquat = type[3];
        maxBurpees = type[4];

        return result;
    }
}
