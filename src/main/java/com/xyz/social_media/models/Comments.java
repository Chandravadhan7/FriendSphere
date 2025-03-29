package com.xyz.social_media.models;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    @Column(nullable = false)
    private Long postId;
    private Long parentId;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String content;
    private Integer votes;
    @Column(nullable = false)
    private Long createdAt;

    public Comments(Long commentId, Long postId, Long parentId, Long userId, String content, Integer votes, Long createdAt) {
        this.commentId = commentId;
        this.postId = postId;
        this.parentId = parentId;
        this.userId = userId;
        this.content = content;
        this.votes = votes;
        this.createdAt = createdAt;
    }

    public Comments(){

    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
