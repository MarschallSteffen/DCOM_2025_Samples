package comments_vs_cleancode.model;

import java.time.Instant;

public class Fee {
    private final String id;
    private final double value;
    private final Instant paymentDeadline;

    public Fee(String id, double value, Instant paymentDeadline) {
        this.id = id;
        this.value = value;
        this.paymentDeadline = paymentDeadline;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return value;
    }

    public Instant getPaymentDeadline() {
        return paymentDeadline;
    }
}
