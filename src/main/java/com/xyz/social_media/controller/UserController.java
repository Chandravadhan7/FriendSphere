package com.xyz.social_media.controller;

import com.xyz.social_media.models.User;
import com.xyz.social_media.requestDto.LoginRequestDto;
import com.xyz.social_media.requestDto.SignupRequestDto;
import com.xyz.social_media.response.LoginResponseDto;
import com.xyz.social_media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/signup")
    public ResponseEntity<String> signUp(@RequestBody SignupRequestDto signupRequestDto){
        User user = userService.signUp(signupRequestDto);
        return new ResponseEntity<>("user created successfully",HttpStatus.OK);
    }

    @PostMapping("/api/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        return loginResponseDto;
    }

}
