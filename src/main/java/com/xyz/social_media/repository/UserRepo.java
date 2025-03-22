package com.xyz.social_media.repository;

import com.xyz.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User,Long> {

    User save(User user);

    @Query(value = "select * from user u where u.email = :email",nativeQuery = true)
    User getUserByEmail(@Param("email") String email);
}
