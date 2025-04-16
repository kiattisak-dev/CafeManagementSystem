package cafe.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Class for managing order history
public class OrderHistoryManager {
    // Save an order to a file
    public void saveOrder(Order order, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("--- Order ---"); // Write order header
            writer.newLine();
            writer.write(order.toString()); // Write order details
            writer.newLine();
            writer.write("-------------"); // Write separator
            writer.newLine();
        }
    }

    // Read and display order history from a file
    public void readOrders(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("\n📜 Order History:");
            boolean hasOrders = false; // Flag to check if orders exist
            while ((line = reader.readLine()) != null) { // Read each line
                System.out.println(line); // Display the line
                hasOrders = true;
            }
            if (!hasOrders) { // Check if no orders were found
                System.out.println("⚠️ No order history found.");
            }
        } catch (IOException e) {
            System.out.println("⚠️ Failed to read order history: " + e.getMessage()); // Handle file read error
        }
    }
}