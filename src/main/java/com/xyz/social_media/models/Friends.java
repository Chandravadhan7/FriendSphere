package com.xyz.social_media.models;

import javax.persistence.*;

@Entity
@Table(name = "friends")
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long user_id1;
    private Long user_id2;
    private String status;

    public Friends(Long id, Long user_id1, Long user_id2, String status) {
        this.id = id;
        this.user_id1 = user_id1;
        this.user_id2 = user_id2;
        this.status = status;
    }

    public Friends(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id1() {
        return user_id1;
    }

    public void setUser_id1(Long user_id1) {
        this.user_id1 = user_id1;
    }

    public Long getUser_id2() {
        return user_id2;
    }

    public void setUser_id2(Long user_id2) {
        this.user_id2 = user_id2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
