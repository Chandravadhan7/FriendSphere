package com.xyz.social_media.service;

import com.xyz.social_media.models.Conversation;
import com.xyz.social_media.repository.ConversationRepo;
import com.xyz.social_media.utilities.UtilityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {
    private ConversationRepo conversationRepo;

    @Autowired
    public ConversationService(ConversationRepo conversationRepo) {
        this.conversationRepo = conversationRepo;
    }

    public Conversation createConversation(Conversation conversation){
        conversation.setCreatedAt(UtilityHelper.getCurrentMillis());
        return conversationRepo.save(conversation);
    }

    public Conversation getConversationById(Long conversationId){
        return conversationRepo.getConversationById(conversationId);
    }

    public List<Conversation> getAllConversationsOfUser(Long userId){
        List<Conversation> conversations = conversationRepo.getAllConversationByUserId(userId);
        return conversations;
    }
    public Conversation updateConversation(Long conversationId,Conversation conversation){
        Conversation existing = getConversationById(conversationId);
        existing.setTitle(conversation.getTitle());
        existing.setUpdatedAt(UtilityHelper.getCurrentMillis());
        return conversationRepo.save(existing);
    }

    public void deleteConversation(Long conversationId){
        conversationRepo.deleteById(conversationId);
    }
}
