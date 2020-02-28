
package workoutuno;
  
public class Deck { 
  
    Node head; // head of list 
    static class Node {
        Card card;
        Node next;
        
        Node(Card c)
        {
            card = c;
            next = null;
        }
    }
    
    // Linked list Card. 
    // This inner class is made static 
    // so that main() can access it 
//    static class Card { 
//  
//        int data; 
//        Card next; 
//  
//        // Constructor 
//        Card(int d) 
//        { 
//            //color;
//            //type;
//            data = d;
//            next = null; 
//        } 
//    } 
    // TODO: change comments and variable names
    
    
    public Card pop() 
    { 
        // If linked list is empty 
        if (head == null) 
            return null; 
  
        // Store head node 
        Node temp = head; 
  
        // If head needs to be removed 
        head = temp.next;   // Change head 
        
        return temp.card;
    } 
   
} 