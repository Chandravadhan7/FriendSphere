package com.xyz.social_media.service;

import com.xyz.social_media.models.Friends;
import com.xyz.social_media.models.User;
import com.xyz.social_media.repository.FriendRepo;
import com.xyz.social_media.repository.UserRepo;
import com.xyz.social_media.response.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendShipService {
    private FriendRepo friendRepo;
    private UserRepo userRepo;

    @Autowired
    public FriendShipService(FriendRepo friendRepo, UserRepo userRepo) {
        this.friendRepo = friendRepo;
        this.userRepo = userRepo;
    }

    public Friends friendRequest(Long userId1,Long userId2){
        Friends friends = new Friends();
        friends.setUser_id1(userId1);
        friends.setUser_id2(userId2);
        friends.setStatus("pending");
        Friends friends1 = friendRepo.save(friends);
        return friends1;
    }

    public Friends acceptFriendRequest(Long friendShipId){
        Friends friends = friendRepo.getFriendShipById(friendShipId);
        friends.setStatus("accept");
        Friends friends1 = friendRepo.save(friends);
        return friends1;
    }
    public Friends rejectFriendRequest(Long friendShipId){
        Friends friends = friendRepo.getFriendShipById(friendShipId);
        friends.setStatus("reject");
        Friends friends1 = friendRepo.save(friends);
        return friends1;
    }

    public List<Long> getFriendsByUserId(Long userId){
        List<Friends> friends = friendRepo.getFriendsByUserId(userId);
        return friends.stream().map(friends1 -> friends1.getUser_id1().equals(userId)?friends1.getUser_id2():friends1.getUser_id1()).collect(Collectors.toList());
    }

    public List<UserResponseDto> getFriendSuggestion(Long userId){
        List<Long> userIds = userRepo.findSuggestedFriendIds(userId);
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for(Long x : userIds){
            User user = userRepo.getUserByUserId(x);
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setName(user.getName());
            userResponseDto.setUserId(user.getId());
            userResponseDto.setDob(user.getDob());
            userResponseDto.setProfile_img_url(user.getProfile_img_url());
            userResponseDto.setCover_pic_url(user.getCover_pic_url());

            userResponseDtos.add(userResponseDto);
        }
        return userResponseDtos;
    }

    public List<UserResponseDto> getAllFriendRequests(Long userId){
        // Step 1: Fetch all pending friend request IDs
        List<Long> friendShipIds = friendRepo.getFriendRequests(userId, "pending");

        // Step 2: Fetch all Friends objects in one query
        List<Friends> friendsList = friendRepo.getFriendShipsByIds(friendShipIds);

        // Step 3: Convert to DTO and return
        return friendsList.stream().map(f -> {
            User user = userRepo.getUserByUserId(f.getUser_id1()); // Fetch sender details
            return new UserResponseDto(
                    user.getId(),
                    user.getName(),
                    user.getDob(),
                    user.getProfile_img_url(),
                    user.getCover_pic_url()
            );
        }).collect(Collectors.toList());
    }


}
