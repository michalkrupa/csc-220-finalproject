package data_structures;

/**
 * Generic implementation of a Doubly Linked List data structure
 * Maintains references to both head and tail nodes for efficient operations
 * @param <E> Type parameter for node ID
 * @param <T> Type parameter for node data
 */
public class DLinkedList<E,T> {
    /** Reference to the first node in the list */
    public DNode<E,T> head;
    /** Reference to the last node in the list */
    public DNode<E,T> tail;
    /** Current number of nodes in the list */
    private int size = 0;

    /**
     * Displays all entries in the list from head to tail
     * Time Complexity: O(n) where n is the number of nodes
     */
    public void displayEntries() {
        DNode<E,T> ptr = this.getFirst();
        while (ptr != null) {
            System.out.println(ptr.getData());
            ptr = ptr.getNext();
        }
        //print empty line for style
        System.out.println("");
    }

    /**
     * Adds a new node at the beginning of the list
     * Time Complexity: O(1)
     * @param node Node to be added at the start
     * @return Reference to the head of the list
     */
    public DNode<E,T> addFirst(DNode<E,T> node) {
        if(this.head != null) {
            this.head.prev = node;
        }
        this.head = node;
        this.size++;
        return this.head;
    }
    
    /**
     * Adds a new node at the end of the list
     * Time Complexity: O(n) where n is the number of nodes
     * @param node Node to be added at the end
     * @return Reference to the head of the list
     */
    public DNode<E,T> addLast(DNode<E,T> node) {
        if(this.head == null) {
            this.head = node;
        } else {
            // traverse to last node of DLL
            DNode<E,T> curr = this.head;
            while(curr.next != null) {
                curr = curr.next;
            }
            curr.next = node;
            node.prev = curr;
        }
        this.tail = node;
        this.size++;
        return this.head;
    }

    /**
     * Inserts a node at the specified index
     * Time Complexity: O(n) where n is the number of nodes
     * @param node Node to be inserted
     * @param index Position where node should be inserted
     * @return Reference to the head of the list
     */
    public DNode<E, T> insert(DNode<E, T> node, int index) {
        if(index == 0) return addFirst(node);

        // traverse to the node before index
        DNode<E, T> curr = this.head;
        for (int i = 0; i < index - 1 && curr != null; i++) {
            curr = curr.next;
        }
        // configure new node
        node.prev = curr;
        node.next = curr.next;

        if(node.next != null) {
            node.next.prev = node;
        }

        if (node.next == null) {
            this.tail = node;
        }

        return this.head;
    }

    /**
     * Removes and returns the first node in the list
     * Time Complexity: O(1)
     * @return The removed node, or null if list is empty
     */
    public DNode<E, T> removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        DNode<E, T> removed = this.head;
        this.head = this.head.getNext();
        if (this.head != null) {
            this.head.setPrev(null);
        } else {
            this.tail = null; // if list became empty
        }
        this.size--;
        return removed;
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
     * Removes and returns the last node in the list
     * Time Complexity: O(1)
     * @return The removed node, or null if list is empty
     */
    public DNode<E, T> removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        DNode<E, T> removed = this.tail;
        this.tail = this.tail.getPrev();
        if (this.tail != null) {
            this.tail.setNext(null);
        } else {
            this.head = null; // if list became empty
        }
        this.size--;
        return removed;
    }

    /**
     * Removes a node with the specified ID
     * Time Complexity: O(n) where n is the number of nodes
     * @param id ID of the node to remove
     * @return The removed node, or null if not found
     */
    public DNode<E, T> removeById(E id) {
        if (this.isEmpty()) return null;

        DNode<E, T> curr = this.head;
        while (curr != null) {
            if (curr.getId().equals(id)) {
                if (curr == head) return removeFirst();
                if (curr == tail) return removeLast();

                curr.getPrev().setNext(curr.getNext());
                curr.getNext().setPrev(curr.getPrev());
                curr.setNext(null);
                curr.setPrev(null);
                this.size--;
                return curr;
            }
            curr = curr.getNext();
        }
        return null; // not found
    }


    /**
     * Removes node at specified index
     * Time Complexity: O(n) where n is the number of nodes
     * @param index Position of node to remove
     * @return Reference to the head of the list
     */
    public DNode<E, T> remove(int index) {
        if (index == 0) {
            return this.removeFirst();
        }

        DNode<E, T> curr = this.head;
        for (int i = 0; i < index - 1 && curr != null; i++) {
            curr = curr.next;
        }

        DNode<E, T> toDelete = curr;

        if (curr.prev != null) {
            curr.prev.next = curr.next;
        }

        if (curr.next != null) {
            toDelete.next = toDelete.prev = null;
        }

        return this.head;
    }

    /**
     * Removes all nodes from the list
     * Time Complexity: O(1)
     */
    public void clear() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Gets the first node in the list
     * Time Complexity: O(1)
     * @return The head node, or null if list is empty
     */
    public DNode<E,T> getFirst() {
        //if the list is empty, return null
        if (this.isEmpty()) return null;
        //otherwise, return current head
        return this.head;
    }

    /**
     * Gets the last node in the list
     * Time Complexity: O(1)
     * @return The tail node, or null if list is empty
     */
    public DNode<E, T> getLast() {
        if (this.isEmpty()) return null;
        return this.tail;
    }

    /**
     * Main method for testing the DLinkedList implementation
     * Demonstrates basic list operations (add, insert, remove)
     */
    public static void main(String[] args) {
        DLinkedList<Integer, String> dlist = new DLinkedList<>();
        DNode<Integer, String> n1 = new DNode<>(1, "First", null, null);
        DNode<Integer, String> n2 = new DNode<>(2, "Second", null, null);
        DNode<Integer, String> n3 = new DNode<>(3, "Third", null, null);
        dlist.addFirst(n1);
        dlist.addLast(n2);
        dlist.insert(n3, 1);
        dlist.displayEntries();
        dlist.removeById(2);
        System.out.println("After removing ID 2:");
        dlist.displayEntries();
    }
}