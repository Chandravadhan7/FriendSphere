package com.xyz.social_media.service;

import com.xyz.social_media.models.Comments;
import com.xyz.social_media.repository.CommentsRepo;
import com.xyz.social_media.utilities.UtilityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    private CommentsRepo commentsRepo;

    @Autowired
    public CommentsService(CommentsRepo commentsRepo) {
        this.commentsRepo = commentsRepo;
    }

    public Comments postComment(Long postId,Long userId,String content,Long parentId){
        Comments comments = new Comments();

        comments.setContent(content);
        comments.setPostId(postId);
        comments.setVotes(0);
        comments.setCreatedAt(UtilityHelper.getCurrentMillis());
        comments.setUserId(userId);

        comments.setParentId(parentId);

        Comments comments1 = commentsRepo.save(comments);

        return comments1;
    }

    public List<Comments> getCommentsByPostId(Long postId){
        List<Comments> comments = commentsRepo.getCommentsByPostId(postId);
        return comments;
    }

    public List<Comments> getReplies(Long parentId){
        List<Comments> replies = commentsRepo.getRepliesByParentId(parentId);
        return replies;
    }
}
