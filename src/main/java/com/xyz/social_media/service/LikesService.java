package com.xyz.social_media.service;

import com.xyz.social_media.models.Likes;
import com.xyz.social_media.models.User;
import com.xyz.social_media.repository.LikesRepo;
import com.xyz.social_media.repository.UserRepo;
import com.xyz.social_media.response.UserResponseDto;
import com.xyz.social_media.utilities.UtilityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikesService {

    private LikesRepo likesRepo;
    private UserRepo userRepo;

    @Autowired
    public LikesService(LikesRepo likesRepo, UserRepo userRepo) {
        this.likesRepo = likesRepo;
        this.userRepo = userRepo;
    }

    public Likes likePost(Long userId,Long postId){
        Likes likes1 = new Likes();
        likes1.setPostId(postId);
        likes1.setUserId(userId);
        likes1.setCreatedAt(UtilityHelper.getCurrentMillis());
        Likes like =likesRepo.save(likes1);
        return like;
    }

    public void dislikePost(Long userId,Long postId){
        likesRepo.deleteLikeByuserIdAndPostId(userId,postId);
    }

    public List<UserResponseDto> getLikesByPostId(Long postId){
        List<Likes> likes = likesRepo.getLikeByPostId(postId);
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        for(Likes x : likes){
            User user = userRepo.getUserByUserId(x.getUserId());
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setName(user.getName());
            userResponseDto.setUserId(user.getId());
            userResponseDto.setDob(user.getDob());
            userResponseDto.setProfile_img_url(user.getProfile_img_url());
            userResponseDto.setCover_pic_url(user.getCover_pic_url());

            userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }

    public List<Likes> getLikesByUserId(Long userId) {
        return likesRepo.findByUserId(userId); // Assuming you have a method to get likes by userId
    }
}
