package cafe;

import java.io.IOException;
import java.util.Scanner;

import cafe.exceptions.*;
import cafe.model.*;
import cafe.services.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cafe cafe = new Cafe(); // Initialize the cafe system

        boolean running = true;

        System.out.println("<<< Welcome to Cafe Management System >>>");

        while (running) {
            // Show main menu options
            System.out.println("\n Main Menu:");
            System.out.println("1. Order Coffee");
            System.out.println("2. View Stock");
            System.out.println("3. View Order History");
            System.out.println("4. Exit");
            System.out.print(" Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    placeOrder(scanner, cafe); // Place a new order
                    break;
                case "2":
                    cafe.displayStock(); // Show available stock
                    break;
                case "3":
                    // Read and display order history
                    OrderHistoryManager historyManager = new OrderHistoryManager();
                    try {
                        historyManager.readOrders("orders.txt");
                    } catch (IOException e) {
                        System.out.println(" Failed to read order history: " + e.getMessage());
                    }
                    break;
                case "4":
                    // Exit the program
                    System.out.println(">> Thank you! Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println(" Invalid option! Please select again.");
            }
        }

        scanner.close(); // Close scanner when done
    }

    // Method to handle placing an order
    private static void placeOrder(Scanner scanner, Cafe cafe) {

        System.out.print("\n Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print(" Are you a member? (yes/no): ");
        String isMember = scanner.nextLine();

        // Create customer (member or regular)
        Customer customer;
        if (isMember.equalsIgnoreCase("yes")) {
            System.out.print(" Enter member ID: ");
            String memberId = scanner.nextLine();
            customer = new MemberCustomer(name, memberId);
        } else {
            customer = new Customer(name);
        }

        Order order = new Order();
        order.setCustomer(customer); // Set customer info to the order

        boolean ordering = true;

        while (ordering) {
            System.out.println("\n Menu:");
            cafe.displayMenu(); // Show menu

            System.out.print(" Enter item name to order (or 'done' to finish): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("done")) {
                ordering = false;
                break;
            }

            try {
                System.out.print(" Enter quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine());

                // Get menu item and add to the order
                MenuItem item = cafe.getMenuItem(input, quantity);
                order.addItem(item, quantity);
                System.out.println("- Added " + quantity + "x " + input + " to order.");
            } catch (NumberFormatException e) {
                System.out.println(" Invalid quantity! Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("⚠️ " + e.getMessage());
            }
        }

        // Calculate and show the total bill
        order.calculateTotal();
        if (order.getTotalAmount() > 0) {
            System.out.println("\n Order Summary:");
            order.printReceipt();

            double paymentAmount = 0;
            boolean validPayment = false;

            // Loop until valid payment is entered
            while (!validPayment) {
                try {
                    System.out.print("\n Enter payment amount: ");
                    paymentAmount = Double.parseDouble(scanner.nextLine());
                    validPayment = true;
                } catch (NumberFormatException e) {
                    System.out.println(" Invalid payment amount! Please enter a valid number.");
                }
            }

            PaymentService paymentService = new PaymentService();
            try {
                // Try to process the payment
                paymentService.processPayment(paymentAmount, order.getTotalAmount(), order, cafe);
                System.out.println(" Payment successful! Thank you for your order.");

                // Save order to history
                OrderHistoryManager historyManager = new OrderHistoryManager();
                try {
                    historyManager.saveOrder(order, "orders.txt");
                    System.out.println(" Order saved to history.");
                } catch (IOException ex) {
                    System.out.println(" Failed to save order history: " + ex.getMessage());
                }

            } catch (PaymentFailedException e) {
                System.out.println("❌ " + e.getMessage());
            }
        } else {
            // No items were ordered
            System.out.println(" No items in the order. Returning to main menu...");
        }
    }
}