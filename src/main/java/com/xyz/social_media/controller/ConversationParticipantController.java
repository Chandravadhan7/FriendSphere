package com.xyz.social_media.controller;

import com.xyz.social_media.models.ConversationParticipants;
import com.xyz.social_media.service.ConversationParticipantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversation-participants")
public class ConversationParticipantController {
    private ConversationParticipantsService conversationParticipantsService;

    @Autowired
    public ConversationParticipantController(ConversationParticipantsService conversationParticipantsService) {
        this.conversationParticipantsService = conversationParticipantsService;
    }

    @PostMapping()
    public ResponseEntity<ConversationParticipants> addParticipant(@RequestBody ConversationParticipants conversationParticipants){
        return new ResponseEntity<>(conversationParticipantsService.addParticipant(conversationParticipants), HttpStatus.OK);
    }

    @GetMapping("/{conversationId}")
    public List<ConversationParticipants> getParticipantsOfConversation(@PathVariable("conversationId") Long conversationId){
        return conversationParticipantsService.getParticipantsOfConversation(conversationId);
    }
}
