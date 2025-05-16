package data_structures;

/**
 * Generic implementation of a Queue Node that stores three types of data
 * Supports doubly-linked structure with next and previous node references
 * @param <E> Type parameter for node ID
 * @param <T> Type parameter for node name
 * @param <P> Type parameter for additional data
 */
public class QueueNode<E,T,P> {
    /** Unique identifier for the node */
    private E id;
    /** Name or description of the node */
    private T name;
    /** Additional data stored in the node */
    private P data;
    /** Reference to the next node in the queue */
    private QueueNode<E,T,P> next;
    /** Reference to the previous node in the queue */
    private QueueNode<E,T,P> prev;

    /**
     * Constructs a new QueueNode with specified parameters
     * Time Complexity: O(1)
     * @param id Unique identifier for the node
     * @param name Name or description of the node
     * @param data Additional data to be stored
     * @param next Reference to the next node
     * @param prev Reference to the previous node
     */
    public QueueNode(E id, T name, P data, QueueNode<E,T,P> next, QueueNode<E,T,P> prev) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    /**
     * Gets the next node in the queue
     * Time Complexity: O(1)
     * @return Reference to the next node
     */
    public QueueNode<E,T,P> getNext() {
        return this.next;
    }

    /**
     * Gets the previous node in the queue
     * Time Complexity: O(1)
     * @return Reference to the previous node
     */
    public QueueNode<E,T,P> getPrev() {
        return this.prev;
    }

    /**
     * Gets the data stored in the node
     * Time Complexity: O(1)
     * @return The stored data
     */
    public P getData() {
        return this.data;
    }

    /**
     * Sets new data for the node
     * Time Complexity: O(1)
     * @param data The new data to store
     */
    public void setData(P data) {
        this.data = data;
    }

    /**
     * Gets the name of the node
     * Time Complexity: O(1)
     * @return The node's name
     */
    public T getName() {
        return this.name;
    }

    /**
     * Sets a new name for the node
     * Time Complexity: O(1)
     * @param name The new name to set
     */
    public void setName(T name) {
        this.name = name;
    }

    /**
     * Gets the node's ID
     * Time Complexity: O(1)
     * @return The node's unique identifier
     */
    public E getId() {
        return this.id;
    }

    /**
     * Sets a new ID for the node
     * Time Complexity: O(1)
     * @param id The new ID to set
     */
    public void setId(E id) {
        this.id = id;
    }

    /**
     * Sets the reference to the next node
     * Time Complexity: O(1)
     * @param node The node to set as next
     */
    public void setNext(QueueNode<E,T,P> node) {
        this.next = node;
    }

    /**
     * Sets the reference to the previous node
     * Time Complexity: O(1)
     * @param node The node to set as previous
     */
    public void setPrev(QueueNode<E,T,P> node) {
        this.prev = node;
    }
}
