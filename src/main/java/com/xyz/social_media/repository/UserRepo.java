package com.xyz.social_media.repository;

import com.xyz.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    User save(User user);
}
