package managers;

import java.util.*;
import data_structures.Graph;

/**
 * Manages task dependencies using a directed graph structure
 * Provides functionality for adding dependencies, detecting cycles,
 * and traversing dependency relationships between tasks
 */
public class GraphManager {
    /** Graph structure to store task dependencies */
    private Graph dependencyGraph;

    /**
     * Constructs a new GraphManager
     * Initializes an empty dependency graph
     */
    public GraphManager() {
        dependencyGraph = new Graph();
    }

    /**
     * Adds a dependency relationship between two tasks
     * Task 'task' depends on the completion of task 'dependsOn'
     * @param task The dependent task
     * @param dependsOn The task that must be completed first
     */
    public void addDependency(String task, String dependsOn) {
        dependencyGraph.addEdge(dependsOn, task);
    }

    /**
     * Prints all dependencies for a given task using depth-first search
     * @param task The task whose dependencies should be displayed
     */
    public void printDependencies(String task) {
        System.out.println("Dependencies for: " + task);
        dependencyGraph.dfs(task);
    }

    /**
     * Detects circular dependencies starting from a specified task
     * Uses depth-first search with a recursion stack to identify cycles
     * @param start The task to start checking from
     */
    public void detectCircularDependencies(String start) {
        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();

        if (hasCycle(start, visited, recursionStack)) {
            System.out.println("Cycle detected starting from task: " + start);
        } else {
            System.out.println("No cycles detected from task: " + start);
        }
    }

    /**
     * Helper method to detect cycles in the dependency graph
     * Uses depth-first search with visited set and recursion stack
     * Time Complexity: O(V + E) where V is number of tasks and E is number of dependencies
     * @param node Current task being checked
     * @param visited Set of all visited tasks
     * @param stack Set of tasks in current recursion stack
     * @return true if a cycle is detected, false otherwise
     */
    private boolean hasCycle(String node, Set<String> visited, Set<String> stack) {
        if (stack.contains(node)) return true;
        if (visited.contains(node)) return false;

        visited.add(node);
        stack.add(node);

        for (String neighbor : dependencyGraph.getAdjList().getOrDefault(node, new ArrayList<>())) {
            if (hasCycle(neighbor, visited, stack)) return true;
        }

        stack.remove(node);
        return false;
    }

    /**
     * Prints the adjacency list representation of the dependency graph
     * Shows all tasks and their immediate dependencies
     */
    public void printAdjacencyList() {
        Map<String, List<String>> adj = dependencyGraph.getAdjList();
        for (String key : adj.keySet()) {
            System.out.println(key + " -> " + adj.get(key));
        }
    }

    /**
     * Gets the adjacency list representation of the dependency graph
     * @return Map containing task dependencies
     */
    public Map<String, List<String>> getAdjList() {
        return dependencyGraph.getAdjList();
    }
}
