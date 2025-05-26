package com.xyz.social_media.controller;

import com.xyz.social_media.models.Messages;
import com.xyz.social_media.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    @PostMapping()
    public ResponseEntity<Messages> sendMessage(@RequestBody Messages messages){
        return new ResponseEntity<>(messageService.sendMessage(messages), HttpStatus.OK);
    }

    @GetMapping("/{conversationId}")
    public ResponseEntity<List<Messages>> getMessages(@PathVariable("conversationId") Long conversationId){
        return new ResponseEntity<>(messageService.getMessagesByConversationId(conversationId),HttpStatus.OK);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("messageId") Long messageId){
        messageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/latest-unseen-message/{conversationId}")
    public ResponseEntity<List<Messages>> getLatestUnseenMessage(@PathVariable Long conversationId, @RequestHeader Long userId) {
        List<Messages> latestMessages = messageService.getLatestUnseenMessage(conversationId, userId);

        return new ResponseEntity<>(latestMessages, HttpStatus.OK);
    }

    @GetMapping("/latest-message/{conversationId}")
    public ResponseEntity<Messages> getLatestMessages(@PathVariable Long conversationId){
        Messages latest = messageService.getLatestMessages(conversationId);
        return new ResponseEntity<>(latest,HttpStatus.OK);
    }

}
