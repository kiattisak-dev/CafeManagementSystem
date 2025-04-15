package main.java.com.cafe.services;

import main.java.com.cafe.exceptions.PaymentFailedException;
import main.java.com.cafe.model.Cafe;
import main.java.com.cafe.model.Order;

public class PaymentService {
    // ลบ Discountable ออก เพราะ Order คำนวณส่วนลดเรียบร้อยแล้ว

    // เพิ่ม constructor ที่ไม่รับพารามิเตอร์เพื่อให้ Main.java ใช้งานได้
    public PaymentService() {
    }

    public void processPayment(double paidAmount, double totalAmount, Order order, Cafe cafe) throws PaymentFailedException {
        if (paidAmount < totalAmount) {
            throw new PaymentFailedException("Insufficient payment. You need to pay at least " + totalAmount + "฿.");
        }

        double change = paidAmount - totalAmount;
        System.out.println("✅ Payment of " + paidAmount + "฿ accepted.");

        if (change > 0) {
            System.out.println("💰 Change: " + change + "฿");
        }

        // อัปเดต Stock หลังจากจ่ายเงินสำเร็จ
        order.updateStock(cafe, true);
    }
}