package managers;

import data_structures.*;
import tasks.*;
import user.User;

import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 * Manages tasks and users in the task management system.
 * Implements various data structures to handle different aspects of task management:
 * - User management (HashSet, HashMap)
 * - Task categories (DynamicArray)
 * - Task undo/redo functionality (Stack)
 * - Task scheduling (QueueInterface)
 * - Priority-based task management (PriorityQueue)
 * - Task history tracking (SinglyLinkedList)
 * - Recurring tasks (CircularLinkedList)
 * - Due date organization (BTree)
 */
public class TaskManager {
    /** Set of unique user IDs to prevent duplicates */
    private HashSet<Integer> userIds;
    /** Maps user IDs to User objects for quick access */
    private HashMap<Integer, User> userMap;
    /** Dynamic array to store task categories */
    private DynamicArray<String> categories;
    /** Stack to support undo operations for tasks */
    private Stack<Task> undoStack;
    /** Stack to support redo operations for tasks */
    private Stack<Task> redoStack;

    /** Queue for scheduling tasks in order */
    private QueueInterface<Integer, String, String> scheduleQueue;
    /** Priority queue for managing tasks based on priority */
    private PriorityQueue<Task> priorityQueue;
    /** Linked list to maintain task history */
    private SinglyLinkedList<Integer, String> taskHistory;
    /** Circular linked list for managing recurring tasks */
    private CircularLinkedList<Integer, String> recurringTasks;
    /** Binary tree for organizing tasks by due date */
    private BTree taskTree;

    /**
     * Initializes a new TaskManager with empty data structures
     * Sets up all necessary collections for managing tasks and users
     */
    public TaskManager() {
        userIds = new HashSet<>();
        userMap = new HashMap<>();
        categories = new DynamicArray<>(5);
        undoStack = new Stack<Task>();
        redoStack = new Stack<Task>();
        scheduleQueue = new QueueInterface<>();
        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Task::getPriority).reversed());
        taskHistory = new SinglyLinkedList<>();
        recurringTasks = new CircularLinkedList<>();
        taskTree = new BTree();
    }

    /**
     * Registers a new user in the system
     * @param id Unique identifier for the user
     * @param name Name of the user
     * @return true if registration successful, false if ID already exists
     */
    public boolean registerUser(int id, String name) {
        if (userIds.contains(id)) return false;
        userIds.add(id);
        userMap.put(id, new User(id, name));
        return true;
    }

    /**
     * Adds a task to a specific user and updates all relevant data structures
     * @param userId ID of the user to whom the task will be assigned
     * @param task Task object containing task details
     * @return true if task was successfully added, false if user not found
     */
    public boolean addTaskToUser(int userId, Task task) {
        User user = userMap.get(userId);
        if (user == null) return false;
        user.addTask(task);
        undoStack.push(task);
        redoStack = new Stack<Task>(); // Clear redo stack on new action
        taskHistory.addFirst(new Node<>(task.getId(), task.getName(), null));
        priorityQueue.add(task);
        scheduleQueue.enqueue(new QueueNode<>(task.getId(), task.getName(), "Scheduled", null, null));
        taskTree.insert(task.getName(), task.getDueDate());
        return true;
    }

    /**
     * Undoes the last task addition by removing it from the user's task list
     * Stores the undone task in the redo stack for potential redo operation
     */
    public void undoLastTask() {
        Task last = undoStack.pop();
        if (last != null) {
            User user = userMap.get(last.getId());
            if (user != null) {
                user.removeTask(last.getId());
                redoStack.push(last); // Save task for potential redo
            }
        }
    }

    /**
     * Redoes the last undone task by adding it back to the user's task list
     * Moves the task back to the undo stack for potential undo operation
     */
    public void redoLastTask() {
        Task last = redoStack.pop();
        if (last != null) {
            User user = userMap.get(last.getId());
            if (user != null) {
                user.addTask(last);
                undoStack.push(last); // Move task back to undo stack
                taskHistory.addFirst(new Node<>(last.getId(), last.getName(), null));
                priorityQueue.add(last);
                scheduleQueue.enqueue(new QueueNode<>(last.getId(), last.getName(), "Scheduled", null, null));
                taskTree.insert(last.getName(), last.getDueDate());
            }
        }
    }

    /**
     * Displays all tasks assigned to a specific user
     * @param userId ID of the user whose tasks should be displayed
     */
    public void displayUserTasks(int userId) {
        User user = userMap.get(userId);
        if (user != null) {
            System.out.println("Tasks for user: " + user.getName());
            user.printTasks();
        }
    }

    /**
     * Prints all scheduled tasks in queue order
     * Tasks are displayed in the order they were scheduled
     */
    public void printScheduledTasks() {
        QueueNode<Integer, String, String> current = scheduleQueue.getFront();
        while (current != null) {
            System.out.println("Scheduled: " + current.getName());
            current = current.getNext();
        }
    }

    /**
     * Prints tasks in order of priority
     * Creates a temporary queue to preserve the original priority queue
     */
    public void printPriorityTasks() {
        PriorityQueue<Task> temp = new PriorityQueue<>(priorityQueue);
        while (!temp.isEmpty()) {
            System.out.println(temp.poll().getDetails());
        }
    }

    /**
     * Prints the complete task history
     * Shows tasks in the order they were added to the system
     */
    public void printTaskHistory() {
        taskHistory.printList();
    }

    /**
     * Prints all tasks ordered by due date
     * Uses in-order traversal of the binary tree
     */
    public void printDueDateTree() {
        taskTree.inOrderTraversal();
    }

    /**
     * Edits an existing task's details
     * @param userId ID of the user who owns the task
     * @param taskId ID of the task to edit
     * @param newName New name for the task (if null, keeps existing name)
     * @param newPriority New priority for the task (-1 to keep existing priority)
     * @param newDueDate New due date for the task (if null, keeps existing date)
     * @return true if task was successfully edited, false if task or user not found
     */
    public boolean editTask(int userId, int taskId, String newName, int newPriority, Date newDueDate) {
        User user = userMap.get(userId);
        if (user == null) return false;
        
        Task task = user.getTaskById(taskId);
        if (task == null) return false;

        // Store original task in undo stack before modifications
        Task originalTask = new Task(task.getId(), task.getName(), task.getPriority());
        originalTask.setDueDate(task.getDueDate());
        undoStack.push(originalTask);
        redoStack = new Stack<Task>(); // Clear redo stack on new action

        // Update task details
        if (newName != null) {
            taskTree.remove(task.getName()); // Remove from tree with old name
            task.setName(newName);
            taskTree.insert(task.getName(), task.getDueDate()); // Add back with new name
        }
        
        if (newPriority != -1) {
            priorityQueue.remove(task);
            task.setPriority(newPriority);
            priorityQueue.add(task);
        }
        
        if (newDueDate != null) {
            taskTree.remove(task.getName());
            task.setDueDate(newDueDate);
            taskTree.insert(task.getName(), task.getDueDate());
        }

        // Update task history
        taskHistory.addFirst(new Node<>(task.getId(), task.getName(), null));
        
        return true;
    }

    /**
     * Gets the total number of tasks in the system
     * @return Total number of tasks
     */
    public int getTotalTasks() {
        int total = 0;
        for (User user : userMap.values()) {
            total += user.getTaskList().size();
        }
        return total;
    }

    /**
     * Gets the distribution of tasks by priority
     * @return Map of priority levels to number of tasks
     */
    public Map<Integer, Integer> getTasksByPriority() {
        Map<Integer, Integer> distribution = new HashMap<>();
        for (User user : userMap.values()) {
            for (Task task : user.getTaskList()) {
                int priority = task.getPriority();
                distribution.put(priority, distribution.getOrDefault(priority, 0) + 1);
            }
        }
        return distribution;
    }

    /**
     * Gets all tasks due within a specified date range
     * @param startDate Start of the date range
     * @param endDate End of the date range
     * @return List of tasks within the date range
     */
    public List<Task> getTasksInDateRange(Date startDate, Date endDate) {
        List<Task> tasksInRange = new ArrayList<>();
        for (User user : userMap.values()) {
            for (Task task : user.getTaskList()) {
                Date dueDate = task.getDueDate();
                if (dueDate != null && !dueDate.before(startDate) && !dueDate.after(endDate)) {
                    tasksInRange.add(task);
                }
            }
        }
        return tasksInRange;
    }

    /**
     * Gets all users in the system
     * @return Map of user IDs to User objects
     */
    public Map<Integer, User> getAllUsers() {
        return new HashMap<>(userMap);
    }
}
