package com.xyz.social_media.repository;

import com.xyz.social_media.models.ConversationParticipants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversationParticipantRepository extends JpaRepository<ConversationParticipants,Long> {

    ConversationParticipants save(ConversationParticipants conversationParticipants);

    @Query(value = "select * from conversation_participants where conversation_id= :conversationId",nativeQuery = true)
    List<ConversationParticipants> getParticipantsByConversationId(@Param("conversationId") Long conversationId);
}
