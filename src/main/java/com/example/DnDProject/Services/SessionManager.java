package com.example.DnDProject.Services;

import com.example.DnDProject.Entities.Login.UserSession;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionManager {

    private final ConcurrentHashMap<String, UserSession> sessions = new ConcurrentHashMap<>();

    public String createSession(int userId, long timeoutSeconds) {
        String sessionId = java.util.UUID.randomUUID().toString();
        UserSession userSession = new UserSession(userId, Instant.now().plusSeconds(timeoutSeconds));
        sessions.put(sessionId, userSession);
        return sessionId;
    }

    public void removeSession(String sessionId) {
        sessions.remove(sessionId);
    }

    public int getUserId(String sessionId) {
        UserSession session = sessions.get(sessionId);
        if (session == null || session.isExpired()) {
            sessions.remove(sessionId);
            return -1;
        }
        return session.getUserId();
    }

    public String prolong(String sessionId, long timeoutSeconds) {
        UserSession session = sessions.get(sessionId);
        if (session == null || session.isExpired()) {
            sessions.remove(sessionId);
            return "1";
        }
        session.prolong(timeoutSeconds);
        return "0";
    }

    public boolean isValid(String sessionId) {
        UserSession session = sessions.get(sessionId);
        if (session == null || session.isExpired()) {
            sessions.remove(sessionId);
            return false;
        }
        return true;
    }




}
