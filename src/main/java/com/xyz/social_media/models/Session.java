package com.xyz.social_media.models;

import javax.persistence.*;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "session_id", nullable = false, unique = true)
    private String sessionId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "expires_at", nullable = false)
    private Long expiresAt;

    @Column(name = "status")
    private String status;

    public Session(String sessionId, Long userId, Long expiresAt, String status) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.expiresAt = expiresAt;
        this.status = status;
    }

    public Session() {}

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
