package main.java.com.cafe.model;

public class MemberDiscount implements Discountable {
    private double discountRate = 0.10; // 10% discount for members

    @Override
    public double calculateDiscount(double price) {
        return price * discountRate;
    }
}
