package com.xyz.social_media.repository;

import com.xyz.social_media.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface MessageRepo extends JpaRepository<Messages,Long> {
    Messages save(Messages messages);

    @Query(value = "select * from messages m where m.conversation_id= :conversationId", nativeQuery = true)
    List<Messages> getMessagesByConversationId(@Param("conversationId") Long conversationId);

    @Transactional
    @Modifying
    @Query(value = "delete from messages m where m.message_id= :messageId",nativeQuery = true)
    void deleteMessageById(Long messageId);
}