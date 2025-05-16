package data_structures;

import java.util.Date;

/**
 * Node class for Binary Search Tree implementation
 * Stores task information and references to left and right child nodes
 */
class BSTNode {
    String task;        // Stores the task name
    Date dueDate;       // Stores the due date of the task
    BSTNode left, right;  // References to left and right child nodes

    /**
     * Constructor for creating a new BST node
     * @param task The task name to be stored
     * @param dueDate The due date of the task
     */
    public BSTNode(String task, Date dueDate) {
        this.task = task;
        this.dueDate = dueDate;
    }
}

/**
 * Binary Search Tree implementation for managing tasks by their due dates
 * Tasks are organized such that left subtree contains tasks due earlier
 * and right subtree contains tasks due later
 */
public class BTree {
    private BSTNode root;  // Root node of the binary search tree

    /**
     * Inserts a new task with its due date into the tree
     * @param task The task name to insert
     * @param dueDate The due date of the task
     */
    public void insert(String task, Date dueDate) {
        root = insertRecursive(root, task, dueDate);
    }

    /**
     * Recursive helper method for inserting a task
     * @param node Current node in recursion
     * @param task Task to be inserted
     * @param dueDate Due date of the task
     * @return Updated node after insertion
     */
    private BSTNode insertRecursive(BSTNode node, String task, Date dueDate) {
        if (node == null) return new BSTNode(task, dueDate);
        if (dueDate.before(node.dueDate)) node.left = insertRecursive(node.left, task, dueDate);
        else node.right = insertRecursive(node.right, task, dueDate);
        return node;
    }

    /**
     * Performs an in-order traversal of the tree
     * Visits tasks in ascending order of due dates
     */
    public void inOrderTraversal() {
        inOrder(root);
    }

    /**
     * Recursive helper method for in-order traversal
     * @param node Current node in recursion
     */
    private void inOrder(BSTNode node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.task + " due on " + node.dueDate);
        inOrder(node.right);
    }

    /**
     * Performs a pre-order traversal of the tree
     * Visits root before left and right subtrees
     */
    public void preOrderTraversal() {
        preOrder(root);
    }

    /**
     * Recursive helper method for pre-order traversal
     * @param node Current node in recursion
     */
    private void preOrder(BSTNode node) {
        if (node == null) return;
        System.out.println(node.task + " due on " + node.dueDate);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * Performs a post-order traversal of the tree
     * Visits left and right subtrees before root
     */
    public void postOrderTraversal() {
        postOrder(root);
    }

    /**
     * Recursive helper method for post-order traversal
     * @param node Current node in recursion
     */
    private void postOrder(BSTNode node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.task + " due on " + node.dueDate);
    }

    /**
     * Searches for a task in the tree
     * @param task The task name to search for
     * @return true if task is found, false otherwise
     */
    public boolean search(String task) {
        return searchRecursive(root, task) != null;
    }

    /**
     * Recursive helper method for searching a task
     * @param node Current node in recursion
     * @param task Task name to search for
     * @return The node containing the task if found, null otherwise
     */
    private BSTNode searchRecursive(BSTNode node, String task) {
        if (node == null) return null;
        if (node.task.equals(task)) return node;
        BSTNode found = searchRecursive(node.left, task);
        if (found == null) found = searchRecursive(node.right, task);
        return found;
    }

    /**
     * Removes a task from the tree
     * @param task The task name to remove
     * @return true if task was successfully removed, false if not found
     */
    public boolean remove(String task) {
        if (!search(task)) return false;
        root = removeRecursive(root, task);
        return true;
    }

    /**
     * Recursive helper method for removing a task
     * @param node Current node in recursion
     * @param task Task name to remove
     * @return Updated node after removal
     */
    private BSTNode removeRecursive(BSTNode node, String task) {
        if (node == null) return null;

        // Search for the node to remove
        if (!node.task.equals(task)) {
            BSTNode newLeft = removeRecursive(node.left, task);
            if (newLeft != node.left) {
                node.left = newLeft;
                return node;
            }
            node.right = removeRecursive(node.right, task);
            return node;
        }

        // Case 1: Node to delete has no children
        if (node.left == null && node.right == null) {
            return null;
        }

        // Case 2: Node to delete has only one child
        if (node.left == null) return node.right;
        if (node.right == null) return node.left;

        // Case 3: Node to delete has two children
        // Find the minimum value in the right subtree (successor)
        BSTNode successor = findMin(node.right);
        // Copy successor's data to this node
        node.task = successor.task;
        node.dueDate = successor.dueDate;
        // Remove the successor
        node.right = removeRecursive(node.right, successor.task);
        return node;
    }

    /**
     * Helper method to find the minimum value node in a subtree
     * @param node Root of the subtree
     * @return Node with minimum value in the subtree
     */
    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Main method for testing the BTree implementation
     */
    public static void main(String[] args) {
        BTree tree = new BTree();
        tree.insert("Task X", new Date(2025 - 1900, 5, 10)); // June 10, 2025
        tree.insert("Task Y", new Date(2025 - 1900, 4, 1));  // May 1, 2025
        tree.insert("Task Z", new Date(2025 - 1900, 6, 20)); // July 20, 2025
        System.out.println("In-Order:");
        tree.inOrderTraversal();
        System.out.println("Pre-Order:");
        tree.preOrderTraversal();
        System.out.println("Post-Order:");
        tree.postOrderTraversal();
    }

}