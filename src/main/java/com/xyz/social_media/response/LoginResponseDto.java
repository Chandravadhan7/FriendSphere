package com.xyz.social_media.response;

public class LoginResponseDto {


    private String sessionId;

    private Long expiryAt;

    private Long userId;
    public LoginResponseDto(String sessionId, Long expiryAt, Long userId) {
        this.sessionId = sessionId;
        this.expiryAt = expiryAt;
        this.userId = userId;
    }
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getExpiryAt() {
        return expiryAt;
    }

    public void setExpiryAt(Long expiryAt) {
        this.expiryAt = expiryAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
