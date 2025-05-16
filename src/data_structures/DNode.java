package data_structures;

/**
 * A doubly-linked node implementation that extends the base Node class
 * Provides functionality for maintaining both previous and next node references
 * @param <E> The type of the node's ID
 * @param <T> The type of the node's name
 */
public class DNode<E, T> extends Node<E, T> {
    /** Reference to the previous node in the doubly-linked structure */
    DNode<E,T> prev; 
    /** Reference to the next node in the doubly-linked structure */
    DNode<E, T> next;

    /**
     * Constructs a new DNode with specified id, name, and node references
     * @param id The unique identifier for this node
     * @param name The name value associated with this node
     * @param prev Reference to the previous node
     * @param next Reference to the next node
     */
    public DNode(E id, T name, DNode<E, T> prev, DNode<E, T> next) {
        super(id, name, next); // Initializes via Node superclass
        this.prev = prev;
        this.next = next;
    }

    /**
     * Sets the reference to the previous node
     * @param prev The node to set as previous
     */
    public void setPrev(DNode<E, T> prev) {
        this.prev = prev;
    }

    /**
     * Gets the reference to the previous node
     * @return The previous node in the sequence
     */
    public DNode<E, T> getPrev() {
        return this.prev;
    }
    
    /**
     * Gets the reference to the next node
     * Overrides the base class implementation to provide correct typing
     * @return The next node in the sequence
     */
    @Override
    public DNode<E,T> getNext() {
        return this.next;
    }

    /**
     * Sets the reference to the next node
     * @param next The node to set as next
     */
    public void setNext(DNode<E,T> next) {
        this.next = next;
    }
}