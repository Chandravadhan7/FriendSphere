package com.xyz.social_media.models;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;
    @Column(name = "user_id",nullable = false)
    private Long userId;
    @Column(name = "description",nullable = true)
    private String description;
    @Column(name = "content",nullable = false)
    private String content;
    @Column(name = "image_url",nullable = true)
    private String imageUrl;
    @Column(name = "video_url",nullable = true)
    private String videoUrl;
    @Column(name = "created_at",nullable = false)
    private Long createdAt;

    public Post(Long postId, Long userId, String content,String description,Long createdAt,String imageUrl,String videoUrl) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.description = description;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
    }
    public Post(){

    }
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
