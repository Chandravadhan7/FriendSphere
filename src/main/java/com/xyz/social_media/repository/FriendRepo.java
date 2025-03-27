package com.xyz.social_media.repository;

import com.xyz.social_media.models.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FriendRepo extends JpaRepository<Friends,Long> {

    Friends save(Friends friends);

    @Query(value = "select * from friends f where f.id = :id",nativeQuery = true)
    Friends getFriendShipById(Long id);

    @Query(value = "select * from friends f where (f.user_id1= :userId or f.user_id2 = :userId) and f.status= 'accept'",nativeQuery = true)
    List<Friends> getFriendsByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = "DELETE FROM friends f WHERE f.user_id1 = :userId1 AND f.user_id2 = :userId2 AND f.status = 'pending'", nativeQuery = true)
    void cancelRequest(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

    @Query(value = "SELECT f.user_id2 FROM Friends f WHERE f.user_id1 = :userId AND f.status = 'pending'", nativeQuery = true)
    List<Long> findPendingRequests(@Param("userId") Long userId);

    @Query(value = "SELECT f.id FROM friends f WHERE f.user_id2 = :userId AND f.status = :status", nativeQuery = true)
    List<Long> getFriendRequests(@Param("userId") Long userId, @Param("status") String status);


    @Query("SELECT f FROM Friends f WHERE f.id IN :friendShipIds")
    List<Friends> getFriendShipsByIds(@Param("friendShipIds") List<Long> friendShipIds);

}
