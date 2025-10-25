package com.example.DnDProject.Services;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionManager {

    private final ConcurrentHashMap<String, Instant> sessions = new ConcurrentHashMap<>();

    public String createSession(long timeoutSeconds) {
        String sessionId = java.util.UUID.randomUUID().toString();
        Instant endTime = Instant.now().plusSeconds(timeoutSeconds);
        sessions.put(sessionId, endTime);
        return sessionId;
    }

    public boolean isValid(String sessionId) {
        Instant endTime = sessions.get(sessionId);
        if (endTime == null) return false;

        if (Instant.now().isAfter(endTime)) {
            sessions.remove(sessionId);
            return false;
        }
        return true;
    }

    public String prolong(String sessionId, long timeoutSeconds) {
        if (!isValid(sessionId)) return "1";

        Instant newEndTime = Instant.now().plusSeconds(timeoutSeconds);
        sessions.put(sessionId, newEndTime);
        return "0";
    }

    public void removeSession(String sessionId) {
        sessions.remove(sessionId);
    }
}
