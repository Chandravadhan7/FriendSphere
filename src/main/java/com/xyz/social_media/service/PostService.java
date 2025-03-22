package com.xyz.social_media.service;

import com.xyz.social_media.models.Post;
import com.xyz.social_media.repository.PostRepo;
import com.xyz.social_media.requestDto.PostRequestDto;
import com.xyz.social_media.utilities.UtilityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostRepo postRepo;

    @Autowired
    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public Post createPost(PostRequestDto postRequestDto){
        Post post = new Post();
        post.setUserId(postRequestDto.getUserId());
        post.setContent(postRequestDto.getContent());
        post.setDescription(postRequestDto.getDescription());
        post.setCreatedAt(UtilityHelper.getCurrentMillis());
        Post post1 = postRepo.save(post);
        return post1;
    }

    public List<Post> getAllPostsOfUser(Long userId){
        List<Post> posts = postRepo.getPostsByUserId(userId);

        return posts;
    }
}
