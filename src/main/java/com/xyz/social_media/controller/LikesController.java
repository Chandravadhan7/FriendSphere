package com.xyz.social_media.controller;

import com.xyz.social_media.models.Likes;
import com.xyz.social_media.repository.LikesRepo;
import com.xyz.social_media.response.UserResponseDto;
import com.xyz.social_media.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikesController {
    private LikesService likesService;
    private LikesRepo likesRepo;

    @Autowired
    public LikesController(LikesService likesService, LikesRepo likesRepo) {
        this.likesService = likesService;
        this.likesRepo = likesRepo;
    }

    @PostMapping("/post")
    public ResponseEntity<String> likePost(@RequestHeader("userId") Long userId, @RequestParam("postId") Long postId){
        Likes likes = likesService.likePost(userId,postId);
        return new ResponseEntity<>("you liked post", HttpStatus.OK);
    }

    @DeleteMapping("/post/dislike")
    public ResponseEntity<Void> dislikePost(@RequestHeader("userId") Long userId,@RequestParam("postId") Long postId){
        likesService.dislikePost(userId, postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public List<UserResponseDto> getLikesOfPost(@RequestParam("postId") Long postId){
        return likesService.getLikesByPostId(postId);
    }

    @GetMapping("/userLikes")
    public ResponseEntity<List<Likes>> getUserLikes(@RequestHeader("userId") Long userId) {
        List<Likes> userLikes = likesService.getLikesByUserId(userId);
        if (userLikes != null && !userLikes.isEmpty()) {
            return ResponseEntity.ok(userLikes);
        } else {
            return ResponseEntity.noContent().build();  // If no likes found
        }
    }
}
