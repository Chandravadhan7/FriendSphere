package com.xyz.social_media.controller;

import com.xyz.social_media.models.Session;
import com.xyz.social_media.models.User;
import com.xyz.social_media.repository.UserRepo;
import com.xyz.social_media.requestDto.LoginRequestDto;
import com.xyz.social_media.requestDto.SignupRequestDto;
import com.xyz.social_media.response.LoginResponseDto;
import com.xyz.social_media.response.UserResponseDto;
import com.xyz.social_media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private final UserRepo userRepo;

    @Autowired
    public UserController(UserService userService,
                          UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
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

    @GetMapping("/{userId}")
    public UserResponseDto getUserDetails(@PathVariable Long userId){
        User user = userRepo.getUserByUserId(userId);
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(userId);
        userResponseDto.setCover_pic_url(user.getCover_pic_url());
        userResponseDto.setDob(user.getDob());
        userResponseDto.setName(user.getName());
        userResponseDto.setProfile_img_url(user.getProfile_img_url());
        return userResponseDto;
    }
    @PostMapping("/api/logout")
    public ResponseEntity<Void> logout(@RequestHeader("sessionId") String sessionId) {
        userService.logout(sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/api/validate")
    public ResponseEntity<?> validate(@RequestHeader("sessionId") String sessionId) {
        try {
            Session session = userService.getValidSession(sessionId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired session");
        }
    }

    @PatchMapping("/update-profile-pic")
    public ResponseEntity<String> updateProfilePic(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("userId") Long userId) {
        String imageUrl = userService.updateProfilePicture(userId, file);
        return ResponseEntity.ok(imageUrl);
    }

    @PatchMapping("/update-cover-pic")
    public ResponseEntity<String> updateCoverPic(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("userId") Long userId) {
        String imageUrl = userService.updateCoverPicture(userId, file);
        return ResponseEntity.ok(imageUrl);
    }

}
