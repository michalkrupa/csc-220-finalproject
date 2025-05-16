# Task Management System with Advanced Features

## Overview
This Java-based Task Management System allows users to create, manage, and analyze tasks using various custom and built-in data structures. The project simulates a multi-user environment where each user maintains their own task list, with support for prioritization, undo operations, and task dependencies.

---

## Features

### 1. Task Storage and Retrieval
- **Categories** stored using `DynamicArray`.
- **Task history** tracked via `SinglyLinkedList`.
- **Navigation** of tasks via `DLinkedList`.
- **Recurring tasks** managed using `CircularLinkedList`.

### 2. Task Operations
- **Undo/Redo** supported using a custom `Stack`.
- **Scheduling** uses `QueueInterface` (FIFO queue).
- **Priority execution** managed via Java's `PriorityQueue`.

### 3. Task Analytics
- **Binary Search Tree (BST)** implemented with `BTree` for sorting tasks by due date.
- **PriorityQueue** used to find the most urgent tasks.

### 4. User Management
- **Unique users** enforced with `HashSet`.
- **User-task mapping** managed via `HashMap`.

### 5. Task Dependencies
- **Directed Graph** via `Graph` implementation for managing dependencies.
- **Cycle detection** implemented via DFS in `GraphManager`.

### 6. Visualization & Reports
- **Tree traversal** outputs due date tasks.
- **Adjacency lists** show dependencies.
- **Console interface** allows interaction.

---

## Class Summary
| Class           | Purpose                                                |
|----------------|--------------------------------------------------------|
| `Main`         | Entry point, demonstrates system capabilities           |
| `ConsoleUI`    | CLI interface for user interaction                     |
| `Task`         | Core task entity with properties and operations        |
| `User`         | Represents a user with a task list                     |
| `TaskManager`  | Handles all task operations and data structures        |
| `GraphManager` | Manages task dependencies and cycle detection          |
| `Node`         | Generic node for linked data structures                |
| `QueueNode`    | Specialized node for queue implementations            |
| `Stack`        | Custom stack implementation for undo/redo              |
| `BTree`        | Binary search tree for due date organization          |
| `Graph`        | Directed graph implementation for dependencies        |
| `DynamicArray` | Resizable array implementation                        |
| `LinkedList`   | Base linked list implementation                       |
| `QueueInterface`| FIFO queue implementation                            |

---

## How to Run
1. Compile all `.java` files
2. For interactive command-line program:
   - Run `Console.java`
   - Use the menu to manage tasks, users, and dependencies
3. For test demonstration:
   - Run `Main.java` to see a demonstration of the system's capabilities
   - This will show sample task creation, dependency management, and various data structure operations
4. For running tests:
   - Execute `Test.java` to run the test suite
   - This will verify the functionality of all data structures and operations
   - Tests cover task creation, user management, dependencies, and data structure operations

---

## Custom Data Structures Used
- `DynamicArray`
- `SinglyLinkedList`
- `DLinkedList`
- `CircularLinkedList`
- `Stack`
- `QueueInterface`
- `BTree`
- `Graph`

---

## Built-In Java Structures
- `PriorityQueue`
- `HashMap`
- `HashSet`

---

## Sample Use Case
1. Register a user.
2. Add a few tasks with due dates and priorities.
3. Schedule tasks in order.
4. Add dependencies.
5. Detect cycles.
6. Undo a task.
7. Print priority queue, schedule, history, and due-date tree.

---

## Resume Bullet (Optional)
> Developed a Java-based Task Management System incorporating advanced data structures (e.g., graphs, trees, hash maps) to manage complex task operations like prioritization, dependencies, and analytics.

---

## Note
This implementation satisfies **all required custom structure implementations**, uses **Java built-ins where permitted**, and provides a **console interface** for full interactivity.

---

## To Do Before Submission
- [ ] Capture screenshots of sample runs
- [ ] Record a 5-minute presentation video
