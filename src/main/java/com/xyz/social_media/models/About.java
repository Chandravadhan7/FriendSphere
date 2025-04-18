package com.xyz.social_media.models;

import javax.persistence.*;

@Entity
@Table(name = "about")
public class About {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    private String workPlace;
    private String secondarySchool;
    private String university;
    private String currentCity;
    private String homeTown;
    private String relationShipStatus;
    private Long dob;
    private String gender;
    @Column(length = 500)
    private String bio;

    public About(){

    }
    public About(Long id, Long userId, String workPlace, String secondarySchool, String university, String currentCity, String homeTown, String relationShipStatus, Long dob,String gender,String bio) {
        this.id = id;
        this.userId = userId;
        this.workPlace = workPlace;
        this.secondarySchool = secondarySchool;
        this.university = university;
        this.currentCity = currentCity;
        this.homeTown = homeTown;
        this.relationShipStatus = relationShipStatus;
        this.dob = dob;
        this.bio=bio;
        this.gender=gender;
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getSecondarySchool() {
        return secondarySchool;
    }

    public void setSecondarySchool(String secondarySchool) {
        this.secondarySchool = secondarySchool;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getRelationShipStatus() {
        return relationShipStatus;
    }

    public void setRelationShipStatus(String relationShipStatus) {
        this.relationShipStatus = relationShipStatus;
    }

    public Long getDob() {
        return dob;
    }

    public void setDob(Long dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
