package com.xyz.social_media.controller;

import com.xyz.social_media.models.Post;
import com.xyz.social_media.requestDto.PostRequestDto;
import com.xyz.social_media.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/createpost")
    public Post createPost(@RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(postRequestDto);
    }

    @GetMapping("/posts")
    public List<Post> getPostsOfUser(@RequestHeader Long userId){
        List<Post> posts = postService.getAllPostsOfUser(userId);
        return posts;
    }
}
