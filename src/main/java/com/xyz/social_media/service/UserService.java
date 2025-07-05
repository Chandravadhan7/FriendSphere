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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    private UserRepo userRepo;
    private SessionRepo sessionRepo;
    private static final String UPLOAD_DIR = "/home/ec2-user/uploads/";

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
        user.setProfile_img_url("https://i.ibb.co/67HWYXmq/icons8-user-96.png");

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



    public String updateProfilePicture(Long userId, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Profile picture file is empty.");
        }

        String fileName = storeFile(file);

        User user = userRepo.getUserByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        String imageUrl = "http://ec2-13-203-205-26.ap-south-1.compute.amazonaws.com:8080/uploads/" + fileName;
        user.setProfile_img_url(imageUrl);
        userRepo.save(user);

        return imageUrl;
    }


    public String updateCoverPicture(Long userId, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Cover picture file is empty.");
        }

        String fileName = storeFile(file);

        User user = userRepo.getUserByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        String imageUrl = "http://ec2-13-203-205-26.ap-south-1.compute.amazonaws.com:8080/uploads/" + fileName;
        user.setCover_pic_url(imageUrl);
        userRepo.save(user);

        return imageUrl;
    }


    private String storeFile(MultipartFile file) {
        try {
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new RuntimeException("Only image files are allowed.");
            }

            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null) {
                throw new RuntimeException("File name is invalid.");
            }

            // ✅ Sanitize the file name
            String cleanFileName = originalFileName.replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");
            String fileName = System.currentTimeMillis() + "_" + cleanFileName;

            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage());
        }
    }

    public Session getValidSession(String sessionId) throws Exception {
        Long currentTime = System.currentTimeMillis();
        Session session =
                sessionRepo.findBySessionIdAndStatusAndExpiresAtGreaterThan(sessionId, currentTime);
        if (session == null) {
            throw new Exception("Invalid or expired session");
        }
        return session;
    }

    public void logout(String sessionId) {
        Session session = sessionRepo.findByValueAndStatus(sessionId, "active");
        session.setStatus("logged out");
        sessionRepo.save(session);
        return;
    }
}
