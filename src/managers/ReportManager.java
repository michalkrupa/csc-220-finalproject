package managers;

import tasks.Task;
import user.User;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Manages report generation for the task management system
 * Provides functionality to generate various reports about tasks, users, and system status
 */
public class ReportManager {
    private TaskManager taskManager;
    private GraphManager graphManager;

    /**
     * Constructs a new ReportManager
     * @param taskManager Reference to the task manager for accessing task data
     * @param graphManager Reference to the graph manager for accessing dependency data
     */
    public ReportManager(TaskManager taskManager, GraphManager graphManager) {
        this.taskManager = taskManager;
        this.graphManager = graphManager;
    }

    /**
     * Generates a summary report of all tasks in the system
     * @return String containing the formatted report
     */
    public String generateTaskSummaryReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Task Summary Report ===\n");
        report.append("Generated: ").append(new Date()).append("\n\n");
        
        // Add task statistics
        int totalTasks = taskManager.getTotalTasks();
        report.append("Total Tasks: ").append(totalTasks).append("\n");
        report.append("Tasks by Priority:\n");
        Map<Integer, Integer> priorityDistribution = taskManager.getTasksByPriority();
        for (Map.Entry<Integer, Integer> entry : priorityDistribution.entrySet()) {
            report.append("  Priority ").append(entry.getKey())
                  .append(": ").append(entry.getValue()).append(" tasks\n");
        }
        
        return report.toString();
    }

    /**
     * Generates a report of tasks due within a specified date range
     * @param startDate Start of the date range
     * @param endDate End of the date range
     * @return String containing the formatted report
     */
    public String generateDateRangeReport(Date startDate, Date endDate) {
        StringBuilder report = new StringBuilder();
        report.append("=== Task Date Range Report ===\n");
        report.append("Period: ").append(startDate).append(" to ").append(endDate).append("\n\n");
        
        List<Task> tasksInRange = taskManager.getTasksInDateRange(startDate, endDate);
        for (Task task : tasksInRange) {
            report.append("Task: ").append(task.getName())
                  .append(" (Due: ").append(task.getDueDate()).append(")\n");
        }
        
        return report.toString();
    }

    /**
     * Generates a dependency analysis report
     * @return String containing the formatted report
     */
    public String generateDependencyReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Task Dependency Analysis ===\n");
        report.append("Generated: ").append(new Date()).append("\n\n");
        
        // Get dependency information from graph manager
        Map<String, List<String>> dependencies = graphManager.getAdjList();
        for (Map.Entry<String, List<String>> entry : dependencies.entrySet()) {
            report.append("Task: ").append(entry.getKey()).append("\n");
            report.append("Dependencies: ").append(entry.getValue()).append("\n\n");
        }
        
        return report.toString();
    }

    /**
     * Generates a user workload report
     * @return String containing the formatted report
     */
    public String generateUserWorkloadReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== User Workload Report ===\n");
        report.append("Generated: ").append(new Date()).append("\n\n");
        
        Map<Integer, User> users = taskManager.getAllUsers();
        for (User user : users.values()) {
            report.append("User: ").append(user.getName())
                  .append(" (ID: ").append(user.getId()).append(")\n");
            report.append("Total Tasks: ").append(user.getTaskList().size()).append("\n\n");
        }
        
        return report.toString();
    }

    private String saveReportToFile(String report, String defaultFileName) {
        try {
            String fileName = defaultFileName;
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(report);
            writer.close();
            return "Report saved to: " + fileName;
        } catch (IOException e) {
            return "Error saving report: " + e.getMessage();
        }
    }

    public String generateTaskSummaryReport(boolean saveToFile) {
        String report = generateTaskSummaryReport();
        if (saveToFile) {
            return report + "\n" + saveReportToFile(report, "task_summary_report.txt");
        }
        return report;
    }

    public String generateDateRangeReport(Date startDate, Date endDate, boolean saveToFile) {
        String report = generateDateRangeReport(startDate, endDate);
        if (saveToFile) {
            return report + "\n" + saveReportToFile(report, "date_range_report.txt");
        }
        return report;
    }

    public String generateDependencyReport(boolean saveToFile) {
        String report = generateDependencyReport();
        if (saveToFile) {
            return report + "\n" + saveReportToFile(report, "dependency_report.txt");
        }
        return report;
    }

    public String generateUserWorkloadReport(boolean saveToFile) {
        String report = generateUserWorkloadReport();
        if (saveToFile) {
            return report + "\n" + saveReportToFile(report, "workload_report.txt");
        }
        return report;
    }
}