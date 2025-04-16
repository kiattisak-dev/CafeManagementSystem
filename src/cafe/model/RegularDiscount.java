package cafe.model;

public class RegularDiscount implements Discountable {
    private double discountRate = 0.05; // 5% discount for regular customers

    @Override
    public double calculateDiscount(double price) {
        return price * discountRate;
    }
}
