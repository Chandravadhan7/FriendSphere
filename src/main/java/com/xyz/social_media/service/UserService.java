package com.xyz.social_media.service;

import com.xyz.social_media.models.Session;
import com.xyz.social_media.models.User;
import com.xyz.social_media.repository.SessionRepo;
import com.xyz.social_media.repository.UserRepo;
import com.xyz.social_media.requestDto.LoginRequestDto;
import com.xyz.social_media.requestDto.SignupRequestDto;
import com.xyz.social_media.response.LoginResponseDto;
import com.xyz.social_media.utilities.UniqueHelper;
import com.xyz.social_media.utilities.UtilityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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

    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws Exception {
        User user = userRepo.getUserByEmail(loginRequestDto.getEmail());
        if (user.getPassword().equals(loginRequestDto.getPassword())) {

            Session session =
                    new Session(
                            UniqueHelper.getSessionId(),
                            user.getId(),
                            UtilityHelper.getCurrentMillis() + TimeUnit.DAYS.toMillis(1),
                            "active");

            Session ses = sessionRepo.save(session);
            return new LoginResponseDto(ses.getSessionId(), ses.getExpiresAt(), user.getId());
        } else throw new Exception("Invalid credits");
    }

}
