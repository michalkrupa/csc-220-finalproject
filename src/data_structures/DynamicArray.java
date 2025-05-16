package data_structures;

/**
 * Generic implementation of a dynamic array that automatically resizes
 * Provides amortized O(1) append operations with dynamic capacity management
 * @param <T> Type parameter for the elements stored in the array
 */
public class DynamicArray<T> {
    /** Internal array to store elements */
    private T[] array;
    /** Current number of elements in the array */
    private int size;
    /** Current capacity of the internal array */
    private int capacity;

    /**
     * Constructs a new DynamicArray with specified initial capacity
     * @param capacity Initial size of the internal array
     */
    public DynamicArray(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    /**
     * Adds an element to the end of the array
     * Doubles capacity if array is full
     * Time Complexity: Amortized O(1)
     * @param element Element to be added
     */
    public void add(T element) {
        if (size == capacity) resize(capacity * 2);
        array[size++] = element;
    }

    /**
     * Removes element at specified index
     * Shifts remaining elements left and reduces capacity if necessary
     * Time Complexity: O(n) where n is number of elements after index
     * @param index Position of element to remove
     */
    public void remove(int index) {
        if (index < 0 || index >= size) return;
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null;
        if (size > 0 && size == capacity / 4) resize(capacity / 2);
    }

    /**
     * Retrieves element at specified index
     * Time Complexity: O(1)
     * @param index Position of desired element
     * @return Element at index, or null if index is invalid
     */
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        return array[index];
    }

    /**
     * Gets the current number of elements in the array
     * Time Complexity: O(1)
     * @return Number of elements currently stored
     */
    public int size() {
        return size;
    }

    /**
     * Resizes the internal array to the new capacity
     * Copies existing elements to the new array
     * Time Complexity: O(n) where n is number of elements
     * @param newCapacity New size for internal array
     */
    private void resize(int newCapacity) {
        capacity = newCapacity;
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    /**
     * Main method for testing the DynamicArray implementation
     * Demonstrates basic array operations (add, get, remove)
     */
    public static void main(String[] args) {
        DynamicArray<String> categories = new DynamicArray<>(2);
        categories.add("Work");
        categories.add("Urgent");
        categories.add("Personal");
        System.out.println("Category at index 1: " + categories.get(1));
        categories.remove(0);
        System.out.println("Category at index 0 after removal: " + categories.get(0));
    }

}
