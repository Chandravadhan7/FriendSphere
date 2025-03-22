package com.xyz.social_media.service;

import com.xyz.social_media.models.Friends;
import com.xyz.social_media.repository.FriendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
