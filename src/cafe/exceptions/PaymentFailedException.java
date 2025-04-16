package cafe.exceptions;

// Custom exception for payment failure
public class PaymentFailedException extends Exception {

    // Constructor with error message
    public PaymentFailedException(String message) {
        super(message);
    }
}