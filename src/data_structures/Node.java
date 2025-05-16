package data_structures;

public class Node<E,T> {
    private E id;
    private T name;
    private Node<E,T> next;

    // constructor that takes three arguments
    // id, name of generic type, and next
    // node in linkedlist as Node type
    public Node (E id, T name, Node<E, T> next) {
        this.id = id;
        this.name = name;
        this.next = next;
    }

    // return a string representation
    // of the node data type.
    // since the types are generic,
    // we check for the type of the property
    // before building the value string
    public String getData() {
        String data = "";
        if (this.id.getClass() == Integer.class) {
            data += ("ID= " + this.id + " :: ");
        }
        if (this.name.getClass() == String.class) {
            data += ("Name= " + this.name + " :: ");
        }
        return data;
    }

    // return the node
    // referenced in the next property
    public Node<E,T> getNext() {
        return this.next;
    }

    // set the next node
    // referenced by the next property
    public void setNext(Node<E,T> next) {
        this.next = next;
    }

    //return the id for the Node
    public E getId() {
        return this.id;
    }

    //set the id for the Node
    public void setId(E id) {
        this.id = id;
    }

    //get the name property for the node
    public T getName() {
        return this.name;
    }

    //set the node's name property
    public void setName(T name) {
        this.name = name;
    }

}