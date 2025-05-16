package data_structures;

/**
 * Generic implementation of a Singly Linked List data structure
 * @param <E> Type parameter for node ID
 * @param <T> Type parameter for node name/data
 */
public class SinglyLinkedList<E, T> {
    /** Reference to the first node in the list */
    private Node<E, T> head;
    /** Current number of nodes in the list */
    private int size = 0;

    /**
     * Clears the list by removing all nodes
     * Time Complexity: O(1)
     */
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Gets the first node in the list
     * Time Complexity: O(1)
     * @return The head node of the list
     */
    public Node<E, T> getFirst() {
        return this.head;
    }

    /**
     * Gets the last node in the list
     * Time Complexity: O(n) where n is the number of nodes
     * @return The last node in the list, or null if list is empty
     */
    public Node<E, T> getLast() {
        Node<E, T> curr = head;
        if (curr == null) return null;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        return curr;
    }

    /**
     * Sets a new head node for the list
     * Time Complexity: O(1)
     * @param node The node to set as head
     * @return The new head node
     */
    public Node<E, T> setHead(Node<E, T> node) {
        this.head = node;
        return this.head;
    }

    /**
     * Removes and returns the first node in the list
     * Time Complexity: O(1)
     * @return The removed head node, or null if list is empty
     */
    public Node<E, T> removeFirst() {
        if (isEmpty()) return null;
        Node<E, T> oldHead = this.head;
        this.head = head.getNext();
        oldHead.setNext(null);
        decrementSize();
        return oldHead;
    }

    /**
     * Adds a new node at the beginning of the list
     * Time Complexity: O(1)
     * @param node The node to add at the start
     */
    public void addFirst(Node<E, T> node) {
        node.setNext(this.head);
        this.head = node;
        incrementSize();
    }

    /**
     * Adds a new node at the end of the list
     * Time Complexity: O(n) where n is the number of nodes
     * @param node The node to add at the end
     */
    public void addLast(Node<E, T> node) {
        if (isEmpty()) {
            addFirst(node);
            return;
        }
        Node<E, T> last = getLast();
        last.setNext(node);
        incrementSize();
    }

    /**
     * Removes a node with the specified ID
     * Time Complexity: O(n) where n is the number of nodes
     * @param id The ID of the node to remove
     * @return The removed node, or null if not found
     */
    public Node<E, T> removeById(E id) {
        if (isEmpty()) return null;

        if (head.getId().equals(id)) {
            return removeFirst();
        }

        Node<E, T> curr = head;
        while (curr.getNext() != null && !curr.getNext().getId().equals(id)) {
            curr = curr.getNext();
        }

        if (curr.getNext() != null) {
            Node<E, T> toRemove = curr.getNext();
            curr.setNext(toRemove.getNext());
            toRemove.setNext(null);
            decrementSize();
            return toRemove;
        }

        return null;
    }

    /**
     * Prints all nodes in the list
     * Time Complexity: O(n) where n is the number of nodes
     */
    public void printList() {
        Node<E, T> curr = head;
        while (curr != null) {
            System.out.println(curr.getData());
            curr = curr.getNext();
        }
    }

    /**
     * Gets the current size of the list
     * Time Complexity: O(1)
     * @return Number of nodes in the list
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Checks if the list is empty
     * Time Complexity: O(1)
     * @return true if list has no nodes, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Increments the size counter of the list
     * Time Complexity: O(1)
     */
    private void incrementSize() {
        this.size++;
    }

    /**
     * Decrements the size counter of the list
     * Time Complexity: O(1)
     */
    private void decrementSize() {
        this.size--;
    }

    /**
     * Main method for testing the SinglyLinkedList implementation
     * Demonstrates basic list operations (add, remove, print)
     */
    public static void main(String[] args) {
        SinglyLinkedList<Integer, String> list = new SinglyLinkedList<>();
        list.addFirst(new Node<>(1, "Task A", null));
        list.addLast(new Node<>(2, "Task B", null));
        list.addLast(new Node<>(3, "Task C", null));
        list.printList();
        list.removeById(2);
        System.out.println("After removing ID 2:");
        list.printList();
    }
}
