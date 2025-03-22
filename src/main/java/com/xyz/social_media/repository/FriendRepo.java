package com.xyz.social_media.repository;

import com.xyz.social_media.models.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepo extends JpaRepository<Friends,Long> {

    Friends save(Friends friends);
}
