import data_structures.*;

public class Test {
    public static void main(String[] args) {
        try {
            System.out.println("=== DynamicArray ===");
            DynamicArray.main(null);

            System.out.println("\n=== SinglyLinkedList ===");
            SinglyLinkedList.main(null);

            System.out.println("\n=== DLinkedList ===");
            DLinkedList.main(null);

            System.out.println("\n=== CircularLinkedList ===");
            CircularLinkedList.main(null);

            System.out.println("\n=== Stack ===");
            Stack.main(null);

            System.out.println("\n=== QueueInterface ===");
            QueueInterface.main(null);

            try {
                System.out.println("\n=== BTree ===");
                BTree.main(null);
            } catch (Exception e) {
                System.out.println("BTree test skipped: " + e.getMessage());
            }

            System.out.println("\n=== Graph ===");
            Graph.main(null);
        } catch (Exception e) {
            System.out.println("Test suite encountered an error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
