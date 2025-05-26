package com.xyz.social_media.controller;

import com.xyz.social_media.models.Post;
import com.xyz.social_media.requestDto.PostRequestDto;
import com.xyz.social_media.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/createpost")
    public Post createPost(
            @RequestParam("userId") Long userId,
            @RequestParam("content") String content,
            @RequestParam("description") String description,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        PostRequestDto postRequestDto = new PostRequestDto();
        postRequestDto.setUserId(userId);
        postRequestDto.setContent(content);
        postRequestDto.setDescription(description);

        return postService.createPost(postRequestDto, file);
    }

    @GetMapping("/posts/{user_id}")
    public List<Post> getPostsOfUser(@PathVariable("user_id") Long user_id){
        List<Post> posts = postService.getAllPostsOfUser(user_id);
        return posts;
    }

    @GetMapping("/feed")
    public List<Post> getPostsOfFriendsOfUser(@RequestHeader Long userId){
        List<Post> posts = postService.getAllPostsOfFriendsOfUser(userId);
        return posts;
    }

    @DeleteMapping("/delete-post")
    public ResponseEntity<Void> letPost(@RequestParam("postId") Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
