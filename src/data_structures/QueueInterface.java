package data_structures;

/**
 * Generic implementation of a Queue data structure using doubly-linked nodes
 * Supports operations for adding elements at the back and removing from the front
 * @param <E> Type parameter for node ID
 * @param <T> Type parameter for node name
 * @param <P> Type parameter for additional data
 */
public class QueueInterface<E, T, P> {
    /** Reference to the front node of the queue */
    private QueueNode<E, T, P> front;
    /** Reference to the back node of the queue */
    private QueueNode<E, T, P> back;

    /**
     * Adds a new node to the back of the queue
     * Time Complexity: O(1)
     * @param node The node to be added to the queue
     * @return The newly added node (now at the back of the queue)
     */
    public QueueNode<E, T, P> enqueue(QueueNode<E, T, P> node) {
        if (back != null) {
            back.setNext(node);
            node.setPrev(back);
        } else {
            front = node;
        }
        back = node;
        return back;
    }

    /**
     * Removes and returns the node at the front of the queue
     * Time Complexity: O(1)
     * @return The removed node, or null if queue is empty
     */
    public QueueNode<E, T, P> dequeue() {
        if (front == null) return null;
        QueueNode<E, T, P> removed = front;
        front = front.getNext();
        if (front != null) {
            front.setPrev(null);
        } else {
            back = null; // Queue is now empty
        }
        removed.setNext(null);
        return removed;
    }

    /**
     * Returns the node at the front of the queue without removing it
     * Time Complexity: O(1)
     * @return The front node, or null if queue is empty
     */
    public QueueNode<E, T, P> peek() {
        return front;
    }

    /**
     * Gets the reference to the front node
     * Time Complexity: O(1)
     * @return The front node of the queue
     */
    public QueueNode<E, T, P> getFront() {
        return front;
    }

    /**
     * Gets the reference to the back node
     * Time Complexity: O(1)
     * @return The back node of the queue
     */
    public QueueNode<E, T, P> getBack() {
        return back;
    }

    /**
     * Checks if the queue is empty
     * Time Complexity: O(1)
     * @return true if queue has no elements, false otherwise
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Removes all nodes from the queue
     * Time Complexity: O(1)
     */
    public void clear() {
        front = null;
        back = null;
    }

    /**
     * Main method for testing the QueueInterface implementation
     * Demonstrates basic queue operations (enqueue, peek, dequeue)
     */
    public static void main(String[] args) {
        QueueInterface<Integer, String, String> queue = new QueueInterface<>();
        queue.enqueue(new QueueNode<>(1, "Task A", "Data1", null, null));
        queue.enqueue(new QueueNode<>(2, "Task B", "Data2", null, null));
        System.out.println("Front of queue: " + queue.peek().getName());
        queue.dequeue();
        System.out.println("After dequeue, front: " + queue.peek().getName());
    }
}
