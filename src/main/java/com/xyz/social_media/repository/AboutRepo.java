package com.xyz.social_media.repository;

import com.xyz.social_media.models.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AboutRepo extends JpaRepository<About,Long> {
    About save(About about);

    @Query(value = "select * from about a where a.user_id = :userId",nativeQuery = true)
    Optional<About> findByUserId(Long userId);
}
