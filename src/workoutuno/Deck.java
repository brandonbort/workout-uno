/*
 * AUTHOR: Aaron Sloan
 * FOR: CS 2365
 *
 */
package workoutuno;

// TODO: change comments and variable names
// TODO: change data to reflect card variables

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
    
    public void push(Card card) 
    { 
        // Create a new node with given data 
        Node new_node = new Node(card); 
        // If linked list is empty 
        if (head == null)
        {
            new_node.next = null; 
            head = new_node;
        }
        else 
        { 
            // Else traverse till the last node 
            // and insert the new_node there 
            Node last = head; 
            while (last.next != null) { 
                last = last.next; 
            } 
  
            // Insert the new_node at last node 
            last.next = new_node; 
        } 
    } 
   
} 