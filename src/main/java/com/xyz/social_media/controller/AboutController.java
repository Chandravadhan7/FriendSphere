package com.xyz.social_media.controller;

import com.xyz.social_media.models.About;
import com.xyz.social_media.repository.AboutRepo;
import com.xyz.social_media.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bio")
public class AboutController {
    private AboutService aboutService;
    private AboutRepo aboutRepo;

    @Autowired
    public AboutController(AboutService aboutService, AboutRepo aboutRepo) {
        this.aboutService = aboutService;
        this.aboutRepo = aboutRepo;
    }

    @PostMapping("")
    public About addOrUpdateInfo(@RequestHeader Long userId, @RequestBody About about){
        return aboutService.addOrUpdateInfo(userId,about);
    }

    @GetMapping("/{userId}")
    public About getBio(@PathVariable Long userId){
        Optional<About> about = aboutRepo.findByUserId(userId);
        if(about.isPresent()) {
            return about.get();
        }else {
            return null;
        }
    }
}
