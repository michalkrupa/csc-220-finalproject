package tasks;

import java.util.Date;

/**
 * Represents a task in the task management system.
 * Each task has a unique identifier, name, priority level, and optional due date.
 * Tasks can be linked together using the next reference, allowing for list-based operations.
 */
public class Task {
    /** Unique identifier for the task */
    private int id;
    /** Name of the task */
    private String name;
    /** Priority level of the task */
    private int priority;
    /** Due date for the task completion (optional) */
    private Date dueDate;
    /** Reference to the next task in a linked structure */
    private Task next;

    /**
     * Constructs a new Task with specified parameters
     * @param id Unique identifier for the task
     * @param name Name of the task
     * @param priority Priority level of the task
     */
    public Task(int id, String name, int priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
    }

    /**
     * Gets the next task in the linked structure
     * @return Reference to the next task
     */
    public Task getNext() { return this.next; }

    /**
     * Sets the next task in the linked structure
     * @param next Reference to the task to be set as next
     */
    public void setNext(Task next) { this.next = next; }

    /**
     * Gets a formatted string containing all task details
     * @return String containing task ID, name, priority, and due date (if set)
     */
    public String getDetails() {
        return (
            "::ID::" + this.getId() +
            "::Name::" + this.getName() +
            "::Priority::" + this.getPriority() +
            (this.dueDate != null ? "::DueDate::" + this.dueDate : "")
        );
    }

    /**
     * Gets the priority level of the task
     * @return Integer representing task priority
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Gets the name of the task
     * @return String containing task name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the task ID
     * @return Integer representing task ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the task ID
     * @param id New ID to assign to the task
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the task name
     * @param id New name to assign to the task
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the task priority
     * @param priority New priority to assign to the task
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * Processes the task and optionally outputs debug information
     * @param debug If true, prints task details during processing
     */
    public void process(boolean debug) {
        if (debug) System.out.println(
            "Executing::" + this.getDetails()
        );
    }

    /**
     * Gets the due date of the task
     * @return Date object representing task due date, may be null
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date for the task
     * @param dueDate Date object representing new due date
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
