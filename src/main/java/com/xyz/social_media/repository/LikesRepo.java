package com.xyz.social_media.repository;

import com.xyz.social_media.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface LikesRepo extends JpaRepository<Likes,Long> {

    Likes save(Likes likes);

    @Modifying
    @Transactional
    @Query(value = "delete from likes l where l.user_id= :userId and l.post_id= :postId",nativeQuery = true)
    void deleteLikeByuserIdAndPostId(@Param("userId") Long userId,@Param("postId") Long postId);

    @Query(value = "select * from likes l where l.post_id= :postId",nativeQuery = true)
    List<Likes> getLikeByPostId(@Param("postId") Long postId);

    @Query(value = "select * from likes l where l.user_id= :userId",nativeQuery = true)
    List<Likes> findByUserId(@Param("userId") Long userId);

}
