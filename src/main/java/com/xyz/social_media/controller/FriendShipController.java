package com.xyz.social_media.controller;

import com.xyz.social_media.models.Friends;
import com.xyz.social_media.repository.FriendRepo;
import com.xyz.social_media.response.UserResponseDto;
import com.xyz.social_media.service.FriendShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendship")
public class FriendShipController {

    private FriendShipService friendShipService;
    private FriendRepo friendRepo;

    @Autowired
    public FriendShipController(FriendShipService friendShipService, FriendRepo friendRepo) {
        this.friendShipService = friendShipService;

        this.friendRepo = friendRepo;
    }

    @PostMapping("/friendrequest/{userId2}")
    public Friends friendRequest(@RequestHeader("userId") Long userId1, @PathVariable("userId2") Long userId2){
        Friends friends = friendShipService.friendRequest(userId1,userId2);

        return friends;
    }

    @DeleteMapping("cancelrequest/{id}")
    public ResponseEntity<String> cancelRequest(@PathVariable("id") Long id){
        friendRepo.cancelRequest(id);
        return new ResponseEntity<>("request cancelled",HttpStatus.OK);
    }


    @PatchMapping("/acceptrequest/{friendShipId}")
    public ResponseEntity<Friends> acceptFriendRequest(@PathVariable("friendShipId") Long friendShipId){
        Friends friends = friendShipService.acceptFriendRequest(friendShipId);
        return new ResponseEntity<>(friends,HttpStatus.OK);
    }

    @PatchMapping("/rejectrequest/{friendShipId}")
    public ResponseEntity<Friends> rejectFriendRequest(@PathVariable("friendShipId") Long friendShipId){
        Friends friends = friendShipService.rejectFriendRequest(friendShipId);
        return new ResponseEntity<>(friends,HttpStatus.OK);
    }

    @GetMapping("/friends/{userId}")
    public List<Long> getFriendsByUserId(@PathVariable("userId") Long userId){
        return friendShipService.getFriendsByUserId(userId);
    }

    @GetMapping("/suggestions")
    public List<UserResponseDto> getSuggestions(@RequestHeader Long userId){
        List<UserResponseDto> userResponseDtos = friendShipService.getFriendSuggestion(userId);
        return userResponseDtos;
    }
}
