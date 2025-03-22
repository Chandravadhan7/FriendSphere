package com.xyz.social_media.service;

import com.xyz.social_media.models.Friends;
import com.xyz.social_media.repository.FriendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendShipService {
    private FriendRepo friendRepo;

    @Autowired
    public FriendShipService(FriendRepo friendRepo) {
        this.friendRepo = friendRepo;
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
}
