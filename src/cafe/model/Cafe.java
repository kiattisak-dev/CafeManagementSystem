package cafe.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cafe {
    private List<MenuItem> menu; // List to store menu items

    public Cafe() {
        this.menu = new ArrayList<>(); // Initialize empty menu list
        loadMenuFromFile("src/cafe/menu.txt"); // Load menu from file
    }

    // Load menu items from a file (format: name,price,stock)
    private void loadMenuFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) { // Read each line
                if (line.trim().isEmpty()) { // Skip empty lines
                    continue;
                }

                String[] parts = line.split(","); // Split line into name, price, stock
                if (parts.length == 3) { // Check if format is correct
                    String name = parts[0].trim(); // Get item name
                    double price = Double.parseDouble(parts[1].trim()); // Get price
                    int stock = Integer.parseInt(parts[2].trim()); // Get stock
                    menu.add(new MenuItem(name, price, stock)); // Add item to menu
                } else {
                    System.out.println("⚠️ Invalid menu item format: " + line); // Warn if format is wrong
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Failed to load menu from file: " + e.getMessage()); // Handle file read error
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid number format in menu file: " + e.getMessage()); // Handle invalid number format
        }
    }

    // Add a new menu item to the list
    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    // Find a menu item by name and check if enough stock is available
    public MenuItem getMenuItem(String name, int quantity) throws Exception {
        for (MenuItem item : menu) {
            if (item.getName().equalsIgnoreCase(name)) { // Match item name (case-insensitive)
                if (item.getStock() < quantity) { // Check stock
                    throw new Exception("❌ Not enough stock for " + name + ". Available: " + item.getStock());
                }
                return item; // Return item if found and stock is sufficient
            }
        }
        throw new Exception("❌ Menu item not found: " + name); // Throw error if item not found
    }

    // Update stock for a menu item after an order
    public void updateStock(String name, int quantity) {
        for (MenuItem item : menu) {
            if (item.getName().equalsIgnoreCase(name)) { // Find item by name
                int newStock = Math.max(0, item.getStock() - quantity); // Reduce stock, ensure not below 0
                item.setStock(newStock); // Update stock
                return;
            }
        }
    }

    // Display current stock of all menu items
    public void displayStock() {
        System.out.println("\n📦 Coffee Stock:");
        for (MenuItem item : menu) {
            System.out.println("✅ " + item.getName() + " - " + item.getStock() + " left");
        }
    }

    // Display the menu with item names, prices, and stock
    public void displayMenu() {
        System.out.println("\n🍵 Menu:");
        for (MenuItem item : menu) {
            System.out.println("✅ " + item.getName() + " - " + item.getPrice() + "฿ (" + item.getStock() + " left)");
        }
    }
}