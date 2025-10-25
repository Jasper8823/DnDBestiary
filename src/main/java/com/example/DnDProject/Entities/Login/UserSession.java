package com.example.DnDProject.Entities.Login;

import java.time.Instant;

public class UserSession {
    private final int userId;
    private Instant endTime;

    public UserSession(int userId, Instant endTime) {
        this.userId = userId;
        this.endTime = endTime;
    }

    public int getUserId() {
        return userId;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void prolong(long timeoutSeconds) {
        this.endTime = Instant.now().plusSeconds(timeoutSeconds);
    }

    public boolean isExpired() {
        return Instant.now().isAfter(endTime);
    }
}
