package main.java.com.cafe.services;

import main.java.com.cafe.model.Order;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OrderHistoryManager {
    public void saveOrder(Order order, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        writer.write(order.toString()); // ต้อง override toString() ใน Order ด้วย
        writer.newLine();
        writer.close();
    }

    public void readOrders(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        System.out.println("\n📜 Order History:");
        boolean hasOrders = false;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            hasOrders = true;
        }
        if (!hasOrders) {
            System.out.println("⚠️ No order history found.");
        }
        reader.close();
    }
}