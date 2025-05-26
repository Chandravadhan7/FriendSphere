package com.xyz.social_media.repository;

import com.xyz.social_media.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {
    Post save(Post post);

    @Query(value = "select * from post p where p.user_id = :userId",nativeQuery = true)
    List<Post> getPostsByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "delete from post p where p.post_id = :postId",nativeQuery = true)
    void deletePostById(Long postId);
}
