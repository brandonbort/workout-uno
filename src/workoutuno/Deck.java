/*
 * AUTHOR: Aaron Sloan
 * FOR: CS 2365
 * COLLABORATOR: Brandon Bort
 * CHANGES MADE: added a cardCount int, with associated getters and setters. Also
 * changed push and pop functions to change the variable to card. Added pushDeck 
 * function specifically for combining multiple decks together.
 *
 * 
 * 
 * TEST CASES for Class - Deck:
 *  Test case1: a class to create a deck
 *      Input: None
 *      Expected output: creates a deck of cards in a doubly-linked list
 *      Actual Output: created a deck of cards in a doubly-linked list
 *
 * TEST CASES for Class - Node:
 *  Test case1: a class to create a node with previous and next pointers
 *      Input: none
 *      Expected output: a node containing data of a card and previous & next pointers
 *      Actual Output: a node containing data of a card and previous & next pointers
 *
 * TEST CASES for Method - getPrev:
 *  Test case1: returns the previous node in the deck
 *      Input: None
 *      Expected output: returns the previous node 
 *      Actual Output: returned the previous node
 *
 * TEST CASES for Method - getNext:
 *  Test case1: returns the next node in the deck
 *      Input: None
 *      Expected output: returns the next node 
 *      Actual Output: returned the next node
 *
 * TEST CASES for Method - setPrev:
 *  Test case1: sets the previous node in the deck
 *      Input: node to become the previous node
 *      Expected output: setting new node to be previous node 
 *      Actual Output: setted new node to be previous node
 *
 * TEST CASES for Method - setNext:
 *  Test case1: sets the next node in the deck
 *      Input: node to become the next node
 *      Expected output: setting new node to be the next node 
 *      Actual Output: setted new node to be the next node
 *
 * TEST CASES for Method - pop:
 *  Test case1: removes a card from top deck and returns it
 *      Input: None
 *      Expected output: returning the head node of the doubly-linked list (top card of deck)
 *      Actual Output: returned the head node of the doubly-linked list (top card of deck)
 *
 * TEST CASES for Method - push:
 *  Test case1: adds a card to the bottom of the deck and returns it
 *      Input: None
 *      Expected output: adds card to end of doubly-linked list (bottom of deck)
 *      Actual Output: added card to end of doubly-linked list (bottom of deck)
 *
 * TEST CASES for Method - pushDeck:
 *  Test case1: combines multiple decks together
 *      Input: a new deck to be added to the current deck
 *      Expected output: returns a deck with the current and new deck combined
 *      Actual Output: returned a deck with the current and new deck combined
 *
 * TEST CASES for Method - setCardCount:
 *  Test case1: sets card count for amount of cards left in the deck, currently
 *      Input: the amount of cards in the deck currently
 *      Expected output: updates card count for the deck
 *      Actual Output: the updated card count for the deck
 *
 * TEST CASES for Method - getCardCount:
 *  Test case1: retrieves the current card count
 *      Input: None
 *      Expected output: returns the current card count in deck
 *      Actual Output: returned the current card count in deck
 */
package workoutuno;

/**
 *
 * @author sloan
 */

/**
 * Deck is used to create decks with the uno cards
 * for the user to use in their workout routine.
 */
public class Deck { 

    Node head; // head of list
    private int cardCount;
    
    /**
     * Node class is used to create a card
     * where the type, color, and data is in the card
     * variable, and is inserted into a new node, then 
     * a doubly-linked list
     */
    static class Node {
        Card card; // uno card
        Node next; // next pointer
        Node prev; // previous pointer
        // constructor for each node 
        // in doubly-linked list
        Node(Card c)
        {
            card = c; // data of card: type & color
            next = null; 
            prev = null; 
        }
        
        /**
         * Retrieves the previous card in
         * the deck 
         * @return previous card in deck
         */
        public Node getPrev() {
            return this.prev;
        }
        
        /**
         * Retrieves the next card in
         * the deck 
         * @return next card in deck
         */
        public Node getNext() {
            return this.next;
        }
        
        /**
         * Sets the the previous card in deck
         * to be a new card
         * @param prevN is the new previous
         *              node being set
         */
        public void setPrev(Node prevN) {
            this.prev = prevN;
        }
        
        /**
         * Sets the the next card in deck
         * to be a new card
         * @param nextN is the new next 
         *              node being set
         */
        public void setNext(Node nextN) {  
            this.next = nextN;
        }
    }
    
    /**
     * Removes a card from the top of the deck (beginning of linked list) 
     * and returns card to where is was called from
     * @return card at beginning of linked list
     */
    public Card pop() 
    { 
        // If linked list is empty 
        if (head == null) 
            return null; 
 
        // prev pointer set to 
        head.setPrev(null);
        // Store head node 
        Node temp = head; 
        // Since head needs to be removed 
        head = temp.next;   // Change head 
        //removes one from the cardCount
        setCardCount(-1);
        
        return temp.card; // return top card on deck
    } 
    
    /**
     * Adds a new card to the deck (linked list)
     * setting its previous and next nodes to the 
     * respective cards
     * @param card  is a card that is being added
     *              to the current deck at the end
     *              of the deck
     */
    public void push(Card card) 
    { 
        // create new node with data of card
        Node new_node = new Node(card); 
        // If linked list is empty 
        if (head == null)
        {
            // set previous and next pointers to null
            new_node.next = null; 
            new_node.prev = null;
            head = new_node; // head points to only card in deck
        }
        // else loop through the deck till the last node
        // and insert the new node (card) at the end
        else 
        { 
            // last set to head node
            Node last = head; 
            Node temp = null; // going to be used for the prev pointer
            // while the next node is not null
            // step through the linked list to 
            // the end of the linked list
            while (last.next != null) { 
                // temp points last
                temp = last; 
                // last steps over to next node
                last = last.next; 
            } 
            // new node (card) inserted after last node
            last.next = new_node;
            // set previous pointer of new node to point to temp
            last.prev = temp;
        }
        setCardCount(1); // adds 1 to card count
    } 
    
    /**
     * Combines multiple decks together 
     * if required
     * @param new_deck is the new deck
     *                 being added at the
     *                 end of the current deck
     */
    public void pushDeck(Deck new_deck){
            Node last = head; 
            // while the next node is not null
            // step through the linked list to 
            // the end of the linked list
            while (last.next != null) { 
                // last steps over to next node
                last = last.next; 
            } 
            // Insert the new_deck at last node 
            last.next = new_deck.head;
            last = last.next; // last points to head of new deck
            // adds amount of cards in new deck to the card count
            setCardCount(new_deck.getCardCount()); 
    }
    
    /**
     * Sets the count for how many 
     * cards are left in the deck
     * @param card Contains an integer
     *             variable for how many
     *             cards are to be added 
     *             to the deck
     */
    public void setCardCount(int card){
        // if amount of cards to be added is less than zero
        if(card < 0){
            // if card count is greater than zero
            // then subtract one from card count
            if(getCardCount()>0) this.cardCount-=1;
        }
        // else add the amount of card,to be added, to count 
        else{
            this.cardCount += card;
        }
    }
    
    /**
     * Gets the total amount of cards 
     * left in the deck currently
     * @return amount of cards left in deck
     */
    public int getCardCount(){
        return this.cardCount;
    } 
} 