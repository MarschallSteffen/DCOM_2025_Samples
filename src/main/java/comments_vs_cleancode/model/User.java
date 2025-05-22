package comments_vs_cleancode.model;

import java.time.Instant;

public class User {
    private final String userId;
    private final Instant subscriptionExpirationTime;

    public User(String userId, Instant subscriptionExpirationTime) {
        this.userId = userId;
        this.subscriptionExpirationTime = subscriptionExpirationTime;
    }

    public String getUserId() {
        return userId;
    }

    public Instant getSubscriptionExpirationTime() {
        return subscriptionExpirationTime;
    }
}
