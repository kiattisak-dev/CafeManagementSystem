package cafe.model;

// Class for calculating member discounts
public class MemberDiscount implements Discountable {
    private double discountRate = 0.10; // Discount rate set to 10%

    // Calculate discount based on price
    @Override
    public double calculateDiscount(double price) {
        return price * discountRate; // Apply 10% discount
    }
}