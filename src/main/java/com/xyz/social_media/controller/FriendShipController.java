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

    @DeleteMapping("/cancelrequest/{userId2}")
    public ResponseEntity<String> cancelRequest(@RequestHeader("userId") Long userId1, @PathVariable("userId2") Long userId2){
        friendRepo.cancelRequest(userId1, userId2);
        return ResponseEntity.ok("Friend request canceled");
    }


    @PatchMapping("/acceptrequest/{userId1}")
    public ResponseEntity<Friends> acceptFriendRequest(@PathVariable("userId1") Long userId1,@RequestHeader("userId") Long userId2){
        Friends friends = friendShipService.acceptFriendRequest(userId1,userId2);
        return new ResponseEntity<>(friends,HttpStatus.OK);
    }

    @PatchMapping("/rejectrequest/{userId1}")
    public ResponseEntity<Friends> rejectFriendRequest(@PathVariable("userId1") Long userId1,@RequestHeader("userId") Long userId2){
        Friends friends = friendShipService.rejectFriendRequest(userId1,userId2);
        return new ResponseEntity<>(friends,HttpStatus.OK);
    }

    @GetMapping("/friends/{userId}")
    public List<UserResponseDto> getFriendsByUserId(@PathVariable("userId") Long userId){
        return friendShipService.getFriendsByUserId(userId);
    }

    @GetMapping("/suggestions")
    public List<UserResponseDto> getSuggestions(@RequestHeader Long userId){
        List<UserResponseDto> userResponseDtos = friendShipService.getFriendSuggestion(userId);
        return userResponseDtos;
    }

    @GetMapping("/pending-requests")
    public List<Long> getPendingRequests(@RequestHeader("userId") Long userId) {
        return friendRepo.findPendingRequests(userId);
    }

    @GetMapping("/friendrequests")
    public List<UserResponseDto> getAllFriendRequests(@RequestHeader Long userId){
        List<UserResponseDto> userResponseDtos = friendShipService.getAllFriendRequests(userId);
        return userResponseDtos;
    }

    @GetMapping("mutual-friends/{userId}")
    public List<UserResponseDto> getMutualFriends(@RequestHeader("userId") Long userId1,@PathVariable Long userId){
        List<UserResponseDto> userResponseDtos = friendShipService.getMutualFriends(userId1,userId);
        return userResponseDtos;
    }
}
