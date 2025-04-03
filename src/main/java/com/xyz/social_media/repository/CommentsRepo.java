package com.xyz.social_media.repository;

import com.xyz.social_media.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepo extends JpaRepository<Comments,Long> {

   Comments save(Comments comments);

   @Query(value = "SELECT * FROM comments c WHERE c.post_id = :postId AND (c.parent_id IS NULL OR c.parent_id = '')", nativeQuery = true)
   List<Comments> getCommentsByPostId(@Param("postId") Long postId);


   @Query(value = "select * from comments c where c.parent_id= :parentId",nativeQuery = true)
   List<Comments> getRepliesByParentId(@Param("parentId") Long parentId);

}
