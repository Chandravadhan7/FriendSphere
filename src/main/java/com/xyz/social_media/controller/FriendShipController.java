package com.xyz.social_media.controller;

import com.xyz.social_media.models.Friends;
import com.xyz.social_media.service.FriendShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friendship")
public class FriendShipController {

    private FriendShipService friendShipService;

    @Autowired
    public FriendShipController(FriendShipService friendShipService) {
        this.friendShipService = friendShipService;
    }

    @PostMapping("/friendrequest/{userId1}/{userId2}")
    public Friends friendRequest(@PathVariable("userId1") Long userId1, @PathVariable("userId2") Long userId2){
        Friends friends = friendShipService.friendRequest(userId1,userId2);

        return friends;
    }
}
