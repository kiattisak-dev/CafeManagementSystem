package cafe.model;

// Class for member customers with discounts
public class MemberCustomer extends Customer {
    private String memberId; // Member ID for identification

    // Constructor: Initialize member customer with name and ID
    public MemberCustomer(String name, String memberId) {
        super(name); // Call parent constructor
        this.memberId = memberId;
    }

    // Override: Provide 10% discount for members
    @Override
    public double getDiscountRate() {
        return 0.1;
    }

    // Get the member's ID
    public String getMemberId() {
        return memberId;
    }
}