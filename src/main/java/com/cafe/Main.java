package main.java.com.cafe;

import main.java.com.cafe.model.*;
import main.java.com.cafe.services.*;
import main.java.com.cafe.exceptions.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cafe cafe = new Cafe();

        // เพิ่มเมนูพร้อม Stock
        cafe.addMenuItem(new MenuItem("Espresso", 50, 10));
        cafe.addMenuItem(new MenuItem("Latte", 70, 8));
        cafe.addMenuItem(new MenuItem("Cappuccino", 80, 5));

        boolean running = true;

        System.out.println("<<< Welcome to Cafe Management System >>>");

        while (running) {
            System.out.println("\n📌 Main Menu:");
            System.out.println("1️⃣ Order Coffee");
            System.out.println("2️⃣ View Stock");
            System.out.println("3️⃣ View Order History");
            System.out.println("4️⃣ Exit");
            System.out.print("👉 Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    placeOrder(scanner, cafe);
                    break;
                case "2":
                    cafe.displayStock();
                    break;
                case "3":
                    OrderHistoryManager historyManager = new OrderHistoryManager();
                    try {
                        historyManager.readOrders("orders.txt");
                    } catch (IOException e) {
                        System.out.println("⚠️ Failed to read order history: " + e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("👋 Thank you! Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("⚠️ Invalid option! Please select again.");
            }
        }

        scanner.close();
    }

    private static void placeOrder(Scanner scanner, Cafe cafe) {
        // 📛 รับข้อมูลลูกค้า
        System.out.print("\n📛 Enter customer name: ");
        String name = scanner.nextLine();
    
        System.out.print("💳 Are you a member? (yes/no): ");
        String isMember = scanner.nextLine();
    
        Customer customer;
        if (isMember.equalsIgnoreCase("yes")) {
            System.out.print("🔖 Enter member ID: ");
            String memberId = scanner.nextLine();
            customer = new MemberCustomer(name, memberId);
        } else {
            customer = new Customer(name);
        }
    
        Order order = new Order();
        order.setCustomer(customer); // เชื่อมลูกค้ากับออเดอร์
    
        boolean ordering = true;
    
        while (ordering) {
            System.out.println("\n📜 Menu:");
            cafe.displayMenu();
    
            System.out.print("🔹 Enter item name to order (or 'done' to finish): ");
            String input = scanner.nextLine();
    
            if (input.equalsIgnoreCase("done")) {
                ordering = false;
                break;
            }
    
            try {
                System.out.print("🔢 Enter quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine());
    
                MenuItem item = cafe.getMenuItem(input, quantity);
                order.addItem(item, quantity);
                System.out.println("✅ Added " + quantity + "x " + input + " to order.");
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid quantity! Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("⚠️ " + e.getMessage());
            }
        }
    
        order.calculateTotal();
        if (order.getTotalAmount() > 0) {
            System.out.println("\n🧾 Order Summary:");
            order.printReceipt();
    
            double paymentAmount = 0;
            boolean validPayment = false;
            while (!validPayment) {
                try {
                    System.out.print("\n💳 Enter payment amount: ");
                    paymentAmount = Double.parseDouble(scanner.nextLine());
                    validPayment = true;
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Invalid payment amount! Please enter a valid number.");
                }
            }
    
            PaymentService paymentService = new PaymentService();
            try {
                paymentService.processPayment(paymentAmount, order.getTotalAmount(), order, cafe);
                System.out.println("✅ Payment successful! Thank you for your order.");
    
                OrderHistoryManager historyManager = new OrderHistoryManager();
                try {
                    historyManager.saveOrder(order, "orders.txt");
                    System.out.println("📁 Order saved to history.");
                } catch (IOException ex) {
                    System.out.println("⚠️ Failed to save order history: " + ex.getMessage());
                }
    
            } catch (PaymentFailedException e) {
                System.out.println("❌ " + e.getMessage());
            }
        } else {
            System.out.println("❌ No items in the order. Returning to main menu...");
        }
    }
}