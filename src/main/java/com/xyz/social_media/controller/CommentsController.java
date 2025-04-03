package com.xyz.social_media.controller;

import com.xyz.social_media.models.Comments;
import com.xyz.social_media.requestDto.CommentRequestDto;
import com.xyz.social_media.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/postcomment")
    public ResponseEntity<Comments> postComment(@RequestHeader("userId") Long userId, @RequestParam("postId") Long postId, @RequestBody CommentRequestDto commentRequestDto, @RequestParam(value = "parentId",required = false) Long parentId ){
        Comments comments = commentsService.postComment(postId,userId,commentRequestDto.getContent(),parentId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("")
    public List<Comments> getCommentsByPostId(@RequestParam("postId") Long postId){
        return commentsService.getCommentsByPostId(postId);
    }

    @GetMapping("/replies/{parentId}")
    public List<Comments> getReplies(@PathVariable("parentId") Long parentId){
        return commentsService.getReplies(parentId);
    }
}
