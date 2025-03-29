package com.xyz.social_media.models;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likeId;
    private Long userId;
    private Long postId;
    private Long createdAt;

    public Likes(){

    }

    public Likes(Long likeId, Long userId, Long postId, Long createdAt) {
        this.likeId = likeId;
        this.userId = userId;
        this.postId = postId;
        this.createdAt = createdAt;
    }



    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
