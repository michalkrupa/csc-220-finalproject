package data_structures;

import tasks.Task;
/**
 * Implementation of a Stack data structure using linked Tasks
 * Follows Last-In-First-Out (LIFO) principle for task management
 */
public class Stack<T> {
    /** Reference to the top task in the stack */
    private T head;
    /** Current number of tasks in the stack */
    private int size;

    /**
     * Pushes a new task onto the top of the stack
     * Time Complexity: O(1)
     * @param node Task to be added to the stack
     */
    public void push(T node) {
        T oldHead = this.head;
        this.head = node;
        // Update next reference if T is Task
        if (node instanceof Task) {
            ((Task)node).setNext((Task)oldHead);
        }
        this.incrementSize();
    }

    /**
     * Removes and returns the task from the top of the stack
     * Time Complexity: O(1)
     * @return The task at the top of the stack, or null if stack is empty
     */
    public T pop() {
        T node = this.getHead();
        if (node == null) return null;
        if (node instanceof Task) {
            Task taskNode = (Task)node;
            this.head = (T)taskNode.getNext();
            taskNode.setNext(null);
        } else {
            this.head = null;
        }
        this.decrementSize();
        return node;
    }

    /**
     * Returns the task at the top of the stack without removing it
     * Time Complexity: O(1)
     * @return The task at the top of the stack
     */
    public T peek() {
        return this.getHead();
    }

    /**
     * Checks if the stack is empty
     * Time Complexity: O(1)
     * @return true if stack has no elements, false otherwise
     */
    public boolean isEmpty() {
        return (this.getSize() == 0);
    }

    /**
     * Gets the current number of tasks in the stack
     * Time Complexity: O(1)
     * @return The size of the stack
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Increments the size counter of the stack
     * Time Complexity: O(1)
     */
    public void incrementSize() {
        this.size++;
    }

    /**
     * Decrements the size counter of the stack
     * Time Complexity: O(1)
     */
    public void decrementSize() {
        this.size--;
    }

    /**
     * Gets the task at the top of the stack
     * Time Complexity: O(1)
     * @return The head task of the stack
     */
    public T getHead() {
        return this.head;
    }

    /**
     * Sets the head task of the stack
     * Time Complexity: O(1)
     * @param head The task to set as the head
     */
    public void setHead(T head) {
        this.head = head;
    }

    public static void main(String[] args) {
        Stack<Task> undoStack = new Stack<>();
        undoStack.push(new Task(1, "Edit A", 1));
        undoStack.push(new Task(2, "Edit B", 2));
        System.out.println("Top of stack: " + ((Task)undoStack.peek()).getName());
        undoStack.pop();
        System.out.println("Top after pop: " + ((Task)undoStack.peek()).getName());
    }
}
