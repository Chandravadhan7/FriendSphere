package com.xyz.social_media.repository;

import com.xyz.social_media.models.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepo extends JpaRepository<Friends,Long> {

    Friends save(Friends friends);

    @Query(value = "select * from friends f where f.id = :id",nativeQuery = true)
    Friends getFriendShipById(Long id);

    @Query(value = "select * from friends f where (f.user_id1= :userId or f.user_id2 = :userId) and f.status= 'accept'",nativeQuery = true)
    List<Friends> getFriendsByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = "delete from friends f where f.id = :id",nativeQuery = true)
    void cancelRequest(@Param("id") Long id);

}
