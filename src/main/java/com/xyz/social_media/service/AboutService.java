package com.xyz.social_media.service;

import com.xyz.social_media.models.About;
import com.xyz.social_media.repository.AboutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AboutService {
    private AboutRepo aboutRepo;

    @Autowired
    public AboutService(AboutRepo aboutRepo) {
        this.aboutRepo = aboutRepo;
    }


    public About addOrUpdateInfo(Long userId,About about){
        Optional<About> about2 = aboutRepo.findByUserId(userId);
        About about1 = about2.orElse(new About());
        about1.setUserId(userId);
        if (about.getBio() != null) about1.setBio(about.getBio());
        if (about.getDob() != null) about1.setDob(about.getDob());
        if (about.getGender() != null) about1.setGender(about.getGender());
        if (about.getCurrentCity() != null) about1.setCurrentCity(about.getCurrentCity());
        if (about.getHomeTown() != null) about1.setHomeTown(about.getHomeTown());
        if (about.getRelationShipStatus() != null) about1.setRelationShipStatus(about.getRelationShipStatus());
        if (about.getSecondarySchool() != null) about1.setSecondarySchool(about.getSecondarySchool());
        if (about.getUniversity() != null) about1.setUniversity(about.getUniversity());
        if (about.getWorkPlace() != null) about1.setWorkPlace(about.getWorkPlace());
        return aboutRepo.save(about1);
    }
}
