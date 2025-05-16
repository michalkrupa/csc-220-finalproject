package user;
import tasks.Task; 
import java.util.ArrayList;

/**
 * Represents a user in the task management system.
 * Each user has a unique ID, name, and maintains their own list of tasks.
 */
public class User {
    /** Unique identifier for the user */
    private int id;
    /** Name of the user */
    private String name;
    /** List containing all tasks assigned to this user */
    private ArrayList<Task> taskList;

    /**
     * Constructs a new User with specified ID and name.
     * Initializes an empty task list for the user.
     * @param id Unique identifier for the user
     * @param name Name of the user
     */
    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.taskList = new ArrayList<>();
    }

    /**
     * Gets the user's ID.
     * @return The unique identifier of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     * @param id The new ID to assign to the user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the user's name.
     * @return The name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     * @param name The new name to assign to the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of tasks assigned to this user.
     * @return ArrayList containing all tasks assigned to the user
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a new task to the user's task list.
     * @param task The task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the user's task list by its ID.
     * @param taskId The ID of the task to remove
     * @return true if the task was found and removed, false otherwise
     */
    public boolean removeTask(int taskId) {
        return taskList.removeIf(task -> task.getId() == taskId);
    }

    /**
     * Retrieves a task by its ID from the user's task list.
     * @param taskId The ID of the task to find
     * @return The task with the specified ID, or null if not found
     */
    public Task getTaskById(int taskId) {
        for (Task task : taskList) {
            if (task.getId() == taskId) return task;
        }
        return null;
    }

    /**
     * Prints the details of all tasks in the user's task list.
     * Uses the Task.getDetails() method to format each task's information.
     */
    public void printTasks() {
        for (Task task : taskList) {
            System.out.println(task.getDetails());
        }
    }
}
