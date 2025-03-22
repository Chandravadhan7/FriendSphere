package com.xyz.social_media.repository;

import com.xyz.social_media.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SessionRepo extends JpaRepository<Session,Long> {

    Session save(Session session);

    @Query(value = "select * from session s where s.session_id = :sessionId", nativeQuery = true)
    Session getBySessionId(@Param("sessionId") String sessionId);

    @Query(
            value =
                    "select * from session s where s.session_id = :sessionId and s.expires_at > :currentTime and s.status = 'active'",
            nativeQuery = true)
    Session findBySessionIdAndStatusAndExpiresAtGreaterThan(
            @Param("sessionId") String sessionId, @Param("currentTime") Long currentTime);

    @Query(
            value = "select * from session s where s.session_id = :sessionId and s.status = :status",
            nativeQuery = true)
    Session findByValueAndStatus(
            @Param("sessionId") String sessionId, @Param("status") String status);

}
