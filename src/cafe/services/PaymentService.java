package cafe.services;

import cafe.exceptions.PaymentFailedException;
import cafe.model.Cafe;
import cafe.model.Order;

// Class for handling payment processing
public class PaymentService {

    // Constructor: Initialize PaymentService
    public PaymentService() {
    }

    // Process payment and update stock if successful
    public void processPayment(double paidAmount, double totalAmount, Order order, Cafe cafe) throws PaymentFailedException {
        if (paidAmount < totalAmount) { // Check if payment is enough
            throw new PaymentFailedException("Insufficient payment. You need to pay at least " + totalAmount + "฿.");
        }

        double change = paidAmount - totalAmount; // Calculate change
        System.out.println("✅ Payment of " + paidAmount + "฿ accepted.");

        if (change > 0) { // Display change if any
            System.out.println("💰 Change: " + change + "฿");
        }

        order.updateStock(cafe, true); // Update cafe stock after payment
    }
}