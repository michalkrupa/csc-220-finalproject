package data_structures;

import java.util.*;

/**
 * Implementation of a directed graph using adjacency list representation
 * Supports basic graph operations including edge addition and graph traversal
 * Used for managing task dependencies in the task management system
 */
public class Graph {
    /** Adjacency list representation of the graph using a Map */
    private Map<String, List<String>> adjList;

    /**
     * Constructs a new empty Graph
     * Initializes the adjacency list
     */
    public Graph() {
        adjList = new HashMap<>();
    }

    /**
     * Adds a directed edge from one vertex to another
     * Time Complexity: O(1) amortized
     * @param from Source vertex
     * @param to Destination vertex
     */
    public void addEdge(String from, String to) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }

    /**
     * Performs Breadth-First Search traversal starting from a given vertex
     * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
     * @param start Starting vertex for BFS traversal
     */
    public void bfs(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (visited.contains(current)) continue;
            System.out.println(current);
            visited.add(current);
            for (String neighbor : adjList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) queue.add(neighbor);
            }
        }
    }

    /**
     * Initiates Depth-First Search traversal starting from a given vertex
     * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
     * @param start Starting vertex for DFS traversal
     */
    public void dfs(String start) {
        Set<String> visited = new HashSet<>();
        dfsHelper(start, visited);
    }

    /**
     * Helper method for DFS traversal
     * Recursively visits all vertices reachable from the current node
     * @param node Current vertex being visited
     * @param visited Set of vertices already visited
     */
    private void dfsHelper(String node, Set<String> visited) {
        if (visited.contains(node)) return;
        System.out.println(node);
        visited.add(node);
        for (String neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
            dfsHelper(neighbor, visited);
        }
    }

    /**
     * Gets the adjacency list representation of the graph
     * @return Map containing the graph's adjacency list
     */
    public Map<String, List<String>> getAdjList() {
        return adjList;
    }

    /**
     * Main method for testing the Graph implementation
     * Demonstrates basic graph operations (edge addition, BFS, DFS)
     */
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("Task A", "Task B");
        graph.addEdge("Task A", "Task C");
        graph.addEdge("Task B", "Task D");
        System.out.println("BFS from Task A:");
        graph.bfs("Task A");
        System.out.println("DFS from Task A:");
        graph.dfs("Task A");
    }
}
