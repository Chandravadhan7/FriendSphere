package com.xyz.social_media.service;

import com.xyz.social_media.models.Messages;
import com.xyz.social_media.repository.MessageRepo;
import com.xyz.social_media.utilities.UtilityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private MessageRepo messageRepo;

    @Autowired
    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Messages sendMessage(Messages messages){
        messages.setCreatedAt(UtilityHelper.getCurrentMillis());
        return messageRepo.save(messages);
    }

    public List<Messages> getMessagesByConversationId(Long conversationId){
        return messageRepo.getMessagesByConversationId(conversationId);
    }

    public void deleteMessage(Long messageId){
        messageRepo.deleteMessageById(messageId);
    }
}
