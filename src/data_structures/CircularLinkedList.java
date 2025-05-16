package data_structures;

/**
 * Generic implementation of a Circular Linked List data structure
 * Maintains a tail reference with the last node pointing back to the first node
 * @param <E> Type parameter for node ID
 * @param <T> Type parameter for node data
 */
public class CircularLinkedList<E, T> {
    /** Reference to the last node in the circular list */
    private Node<E, T> tail;
    /** Current number of nodes in the list */
    private int size;

    /**
     * Adds a new node to the circular list
     * If list is empty, creates a self-referential node
     * Otherwise, adds node after tail and updates tail
     * Time Complexity: O(1)
     * @param node Node to be added to the list
     */
    public void add(Node<E, T> node) {
        if (tail == null) {
            tail = node;
            tail.setNext(tail);
        } else {
            node.setNext(tail.getNext());
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    /**
     * Removes the node after the tail node
     * Handles special cases for empty list and single node
     * Time Complexity: O(1)
     */
    public void removeNext() {
        if (tail == null || tail.getNext() == tail) {
            tail = null;
        } else {
            tail.setNext(tail.getNext().getNext());
        }
        size--;
    }

    /**
     * Displays all nodes in the circular list
     * Traverses the list once, starting from the node after tail
     * Time Complexity: O(n) where n is the number of nodes
     */
    public void display() {
        if (tail == null) return;
        Node<E, T> current = tail.getNext();
        do {
            System.out.println(current.getData());
            current = current.getNext();
        } while (current != tail.getNext());
    }

    /**
     * Main method for testing the CircularLinkedList implementation
     * Demonstrates basic list operations (add, remove, display)
     */
    public static void main(String[] args) {
        CircularLinkedList<Integer, String> clist = new CircularLinkedList<>();
        clist.add(new Node<>(1, "Recurring A", null));
        clist.add(new Node<>(2, "Recurring B", null));
        clist.add(new Node<>(3, "Recurring C", null));
        clist.display();
        clist.removeNext();
        System.out.println("After removing one:");
        clist.display();
    }
}
