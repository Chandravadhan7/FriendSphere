package com.xyz.social_media.controller;

import com.xyz.social_media.models.Conversation;
import com.xyz.social_media.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    private ConversationService conversationService;

    @Autowired
    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping()
    public ResponseEntity<Conversation> createConversation(@RequestBody Conversation conversation){
        return new ResponseEntity<>(conversationService.createConversation(conversation), HttpStatus.OK);
    }

    @GetMapping()
    public List<Conversation> getAllConversationsOfUser(@RequestHeader("userId") Long userId){
        return conversationService.getAllConversationsOfUser(userId);
    }

    @GetMapping("/{conversationId}")
    public Conversation getConversation(@PathVariable Long conversationId){
        return conversationService.getConversationById(conversationId);
    }

    @PatchMapping("/{conversationId}")
    public Conversation updateConversation(Long conversationId,@RequestBody Conversation conversation){
        return conversationService.updateConversation(conversationId,conversation);
    }

    @DeleteMapping("/{conversationId}")
    public ResponseEntity<String> deleteConversation(@PathVariable Long conversationId){
        conversationService.deleteConversation(conversationId);
        return new ResponseEntity<>("chat deleted successfully",HttpStatus.OK);
    }

}
