package com.xyz.social_media.service;

import com.xyz.social_media.models.ConversationParticipants;
import com.xyz.social_media.repository.ConversationParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationParticipantsService {
    private ConversationParticipantRepository conversationParticipantRepository;

    @Autowired
    public ConversationParticipantsService(ConversationParticipantRepository conversationParticipantRepository) {
        this.conversationParticipantRepository = conversationParticipantRepository;
    }

    public ConversationParticipants addParticipant(ConversationParticipants conversationParticipants){
        return conversationParticipantRepository.save(conversationParticipants);
    }

    public List<ConversationParticipants> getParticipantsOfConversation(Long conversationId){
        return conversationParticipantRepository.getParticipantsByConversationId(conversationId);
    }

}
