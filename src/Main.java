
import java.util.Date;
import managers.*;
import tasks.*;
import data_structures.*;

public class Main {
    public static void main(String[] args) {
        TaskManager tm = new TaskManager();
        GraphManager gm = new GraphManager();

        // Register users
        tm.registerUser(1, "Alice");
        tm.registerUser(2, "Bob");

        // Create tasks with dates
        Task t1 = new Task(101, "Design Module", 8);
        t1.setDueDate(new Date(2025 - 1900, 4, 15));
        Task t2 = new Task(102, "Implement Module", 6);
        t2.setDueDate(new Date(2025 - 1900, 4, 20));
        Task t3 = new Task(103, "Test Module", 9);
        t3.setDueDate(new Date(2025 - 1900, 4, 25));

        // Add tasks to Alice
        tm.addTaskToUser(1, t1);
        tm.addTaskToUser(1, t2);
        tm.addTaskToUser(1, t3);

        // Add recurring task
        CircularLinkedList<Integer, String> recurring = new CircularLinkedList<>();
        recurring.add(new Node<>(201, "Daily Standup", null));
        recurring.add(new Node<>(202, "Weekly Report", null));
        recurring.display();

        // Add task dependencies
        gm.addDependency("Test Module", "Implement Module");
        gm.addDependency("Implement Module", "Design Module");

        // Detect cycle (should be none)
        gm.detectCircularDependencies("Test Module");

        // View task lists
        tm.displayUserTasks(1);

        // View priority queue
        tm.printPriorityTasks();

        // Undo most recent task (Test Module)
        tm.undoLastTask();
        tm.displayUserTasks(1);

        // Show schedule queue
        tm.printScheduledTasks();

        // View task history
        tm.printTaskHistory();

        // View task due dates using BST
        tm.printDueDateTree();

        // Print dependency graph
        gm.printAdjacencyList();
    }
}