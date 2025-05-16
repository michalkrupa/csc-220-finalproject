import java.util.*;
import managers.*;
import tasks.*;

public class ConsoleUI {
    private Scanner scanner;
    private TaskManager taskManager;
    private GraphManager graphManager;
    private ReportManager reportManager;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        taskManager = new TaskManager();
        graphManager = new GraphManager();
        reportManager = new ReportManager(taskManager, graphManager);
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": registerUser(); break;
                case "2": addTask(); break;
                case "3": undoTask(); break;
                case "4": redoTask(); break;
                case "5": viewTasks(); break;
                case "6": scheduleOverview(); break;
                case "7": viewPriorities(); break;
                case "8": viewHistory(); break;
                case "9": viewDueDateTree(); break;
                case "10": addDependency(); break;
                case "11": checkCycles(); break;
                case "12": printGraph(); break;
                case "13": editTask(); break;
                case "14": generateTaskSummary(); break;
                case "15": generateDateRangeReport(); break;
                case "16": generateDependencyReport(); break;
                case "17": generateWorkloadReport(); break;
                case "0": running = false; break;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Task Management System Menu ===");
        System.out.println("1. Register User");
        System.out.println("2. Add Task to User");
        System.out.println("3. Undo Last Task");
        System.out.println("4. Redo Last Task");
        System.out.println("5. View User Tasks");
        System.out.println("6. View Task Schedule");
        System.out.println("7. View Task Priorities");
        System.out.println("8. View Task History");
        System.out.println("9. View Task Due Dates (Tree)");
        System.out.println("10. Add Task Dependency");
        System.out.println("11. Check for Circular Dependencies");
        System.out.println("12. Print Dependency Graph");
        System.out.println("13. Edit Task");
        System.out.println("14. Generate Task Summary Report");
        System.out.println("15. Generate Date Range Report");
        System.out.println("16. Generate Dependency Report");
        System.out.println("17. Generate User Workload Report");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Helper method to safely read an integer from user input
     * @param prompt Message to display to user
     * @param min Minimum allowed value (inclusive)
     * @param max Maximum allowed value (inclusive)
     * @return Valid integer within specified range
     */
    private int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty. Please try again.");
                    continue;
                }
                int value = Integer.parseInt(input);
                if (value < min || value > max) {
                    System.out.printf("Please enter a number between %d and %d.\n", min, max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid integer.");
            }
        }
    }

    /**
     * Helper method to safely read a date from user input
     * @param prompt Message to display to user
     * @param allowEmpty Whether empty input is allowed
     * @return Date object or null if empty input is allowed and provided
     */
    private Date readDate(String prompt, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine();
                if (input.isEmpty() && allowEmpty) {
                    return null;
                }
                String[] parts = input.split("-");
                if (parts.length != 3) {
                    System.out.println("Invalid date format. Please use yyyy-mm-dd.");
                    continue;
                }
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);
                
                if (month < 1 || month > 12) {
                    System.out.println("Month must be between 1 and 12.");
                    continue;
                }
                if (day < 1 || day > 31) {
                    System.out.println("Day must be between 1 and 31.");
                    continue;
                }
                return new Date(year - 1900, month - 1, day);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format in date. Please use yyyy-mm-dd.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            }
        }
    }

    /**
     * Helper method to safely read a non-empty string from user input
     * @param prompt Message to display to user
     * @param allowEmpty Whether empty input is allowed
     * @return User input string or null if empty input is allowed and provided
     */
    private String readString(String prompt, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input.isEmpty() && !allowEmpty) {
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }
            return input.isEmpty() && allowEmpty ? null : input;
        }
    }

    // Now update the existing methods to use these helpers:

    private void registerUser() {
        int id = readInt("Enter user ID: ", 1, Integer.MAX_VALUE);
        String name = readString("Enter user name: ", false);
        if (taskManager.registerUser(id, name)) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("User ID already exists.");
        }
    }

    private void addTask() {
        int userId = readInt("Enter user ID: ", 1, Integer.MAX_VALUE);
        int taskId = readInt("Enter task ID: ", 1, Integer.MAX_VALUE);
        String name = readString("Enter task name: ", false);
        int priority = readInt("Enter task priority (1-10): ", 1, 10);
        Date dueDate = readDate("Enter task due date (yyyy-mm-dd): ", false);
        
        Task task = new Task(taskId, name, priority);
        task.setDueDate(dueDate);
        if (taskManager.addTaskToUser(userId, task)) {
            System.out.println("Task added.");
        } else {
            System.out.println("User not found.");
        }
    }

    private void editTask() {
        int userId = readInt("Enter user ID: ", 1, Integer.MAX_VALUE);
        int taskId = readInt("Enter task ID: ", 1, Integer.MAX_VALUE);
        String newName = readString("Enter new task name (press Enter to keep current): ", true);
        int newPriority = readInt("Enter new priority (1-10, or 0 to keep current): ", 0, 10);
        Date newDueDate = readDate("Enter new due date (yyyy-mm-dd, press Enter to keep current): ", true);
        
        if (taskManager.editTask(userId, taskId, newName, newPriority == 0 ? -1 : newPriority, newDueDate)) {
            System.out.println("Task updated successfully.");
        } else {
            System.out.println("Failed to update task. User or task not found.");
        }
    }

    private void viewTasks() {
        int userId = readInt("Enter user ID: ", 1, Integer.MAX_VALUE);
        taskManager.displayUserTasks(userId);
    }

    private void redoTask() {
        taskManager.redoLastTask();
        System.out.println("Redo complete.");
    }

    private void undoTask() {
        taskManager.undoLastTask();
        System.out.println("Undo complete.");
    }

    private void scheduleOverview() {
        System.out.println("Scheduled Tasks:");
        taskManager.printScheduledTasks();
    }

    private void viewPriorities() {
        System.out.println("Priority Queue Tasks:");
        taskManager.printPriorityTasks();
    }

    private void viewHistory() {
        System.out.println("Task History:");
        taskManager.printTaskHistory();
    }

    private void viewDueDateTree() {
        System.out.println("Task Tree (Due Dates):");
        taskManager.printDueDateTree();
    }

    private void addDependency() {
        System.out.print("Enter dependent task name: ");
        String task = scanner.nextLine();
        System.out.print("Enter prerequisite task name: ");
        String dependsOn = scanner.nextLine();
        graphManager.addDependency(task, dependsOn);
        System.out.println("Dependency added.");
    }

    private void checkCycles() {
        System.out.print("Enter task name to check: ");
        String task = scanner.nextLine();
        graphManager.detectCircularDependencies(task);
    }

    private void printGraph() {
        System.out.println("Task Dependency Graph:");
        graphManager.printAdjacencyList();
    }

    private void generateTaskSummary() {
        System.out.println("Would you like to save this report to a file? (y/n)");
        String choice = scanner.nextLine().trim().toLowerCase();
        boolean saveToFile = choice.equals("y");
        System.out.println(reportManager.generateTaskSummaryReport(saveToFile));
    }

    private void generateDateRangeReport() {
        Date startDate = readDate("Enter start date (yyyy-mm-dd): ", false);
        Date endDate = readDate("Enter end date (yyyy-mm-dd): ", false);
        System.out.println("Would you like to save this report to a file? (y/n)");
        String choice = scanner.nextLine().trim().toLowerCase();
        boolean saveToFile = choice.equals("y");
        System.out.println(reportManager.generateDateRangeReport(startDate, endDate, saveToFile));
    }

    private void generateDependencyReport() {
        System.out.println("Would you like to save this report to a file? (y/n)");
        String choice = scanner.nextLine().trim().toLowerCase();
        boolean saveToFile = choice.equals("y");
        System.out.println(reportManager.generateDependencyReport(saveToFile));
    }

    private void generateWorkloadReport() {
        System.out.println("Would you like to save this report to a file? (y/n)");
        String choice = scanner.nextLine().trim().toLowerCase();
        boolean saveToFile = choice.equals("y");
        System.out.println(reportManager.generateUserWorkloadReport(saveToFile));
    }
}