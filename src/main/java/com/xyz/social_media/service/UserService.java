package com.xyz.social_media.service;

import com.xyz.social_media.models.User;
import com.xyz.social_media.repository.SessionRepo;
import com.xyz.social_media.repository.UserRepo;
import com.xyz.social_media.requestDto.SignupRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo userRepo;
    private SessionRepo sessionRepo;

    @Autowired
    public UserService(UserRepo userRepo, SessionRepo sessionRepo){
        this.userRepo = userRepo;
        this.sessionRepo = sessionRepo;
    }

    public User signUp(SignupRequestDto signupRequestDto){
        User user = new User();
        user.setEmail(signupRequestDto.getEmail());
        user.setName(signupRequestDto.getName());
        user.setPassword(signupRequestDto.getPassword());
        user.setDob(null);
        user.setCover_pic_url(null);
        user.setProfile_img_url(null);

        User user1 = userRepo.save(user);
        return user1;
    }

}
