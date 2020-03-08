/*
 * AUTHOR: Aaron Sloan
 * FOR: CS 2365
 * COLLABORATOR: Brandon Bort
 * CHANGES MADE: added a cardCount int, with associated getters and setters. Also
 * changed push and pop functions to change the variable
 */
package workoutuno;

// TODO: change comments and variable names
// TODO: change data to reflect card variables

public class Deck { 

    Node head; // head of list
    private int cardCount;
    
    static class Node {
        Card card;
        Node next;
        Node prev;
        
        Node(Card c)
        {
            card = c;
            next = null;
            prev = null;
        }
        
        public Node getPrev() {
            return this.prev;
        }

        public Node getNext() {
            return this.next;
        }
        
        public void setPrev(Node prevN) {
            this.prev = prevN;
        }
        
        public void setNext(Node nextN) {  
            this.next = nextN;
        }
        
        
    
    }
    
    
    public Card pop() 
    { 
        // If linked list is empty 
        if (head == null) 
            return null; 
 
        head.setPrev(null);// prev pointer set to 
        // Store head node 
        Node temp = head; 
  
         // If head needs to be removed 
        head = temp.next;   // Change head 
        //removes one from the cardCount
        setCardCount(-1);
        
        return temp.card;
    } 
    
    public void push(Card card) 
    { 
        // Create a new node with given data 
        Node new_node = new Node(card); 
        // If linked list is empty 
        if (head == null)
        {
            new_node.next = null; 
            new_node.prev = null;
            head = new_node;
        }
        else 
        { 
            // Else traverse till the last node 
            // and insert the new_node there 
            Node last = head; 
            Node temp = null; // going to be used for the prev pointer
            while (last.next != null) { 
                temp = last; 
                last = last.next; 
            } 
  
            // Insert the new_node at last node 
            last.next = new_node;
            last.prev = temp;
        }
        setCardCount(1);
    } 
    //BRBORT- added function specifically for combining multiple decks together
    public void pushDeck(Deck new_deck){
            Node last = head; 
            while (last.next != null) { 
                last = last.next; 
            } 
            
            // Insert the new_node at last node 
            last.next = new_deck.head;
            last = last.next;
            setCardCount(new_deck.getCardCount());
    }
    
    public void setCardCount(int card){
        if(card < 0){
            if(getCardCount()>0) this.cardCount-=1;
        }
        else{
            this.cardCount += card;
        }
    }
    
    public int getCardCount(){
        return this.cardCount;
    }
   
} 