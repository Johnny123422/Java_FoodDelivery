import java.time.LocalDateTime;

public class Payment {
    private final String paymentId;
    private final double amount;
    private final String method; 
    private final LocalDateTime timestamp;

    public Payment(String paymentId, double amount, String method, LocalDateTime timestamp) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.method = method;
        this.timestamp = timestamp;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}