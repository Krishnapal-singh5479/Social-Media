package com.social.media.application.service;

import com.social.media.application.model.Following;
import com.social.media.application.repository.FollowingRepository;
import com.social.media.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FollowingService {
    @Autowired
    private FollowingRepository followingRepository;

    @Autowired
    private UserRepository userRepository;

    // This function creates connection between users.
    public void makeRelation(Following following) {
        followingRepository.save(following);
    }

    // It returns a list of following of a user.
    public List<String> getFollowing(Long userId)
    {
        List<Following> list = followingRepository.findAllByUserId(userId);
        List<String> toSend = new ArrayList<>();

        for(Following following:list)
        {
            toSend.add(userRepository.findById(following.getGotId()).orElse(null).getFullName());
        }
        return toSend;
    }

    // It returns a list of followers of a user.
    public List<String> getFollowers(Long userId) {
        List<Following> list = followingRepository.findAllByGotId(userId);
        List<String> toSend = new ArrayList<>();

        for(Following following:list)
        {
            toSend.add(userRepository.findById(following.getUserId()).orElse(null).getFullName());
        }
        return toSend;
    }

    // It returns 2nd degree connection
    public Set<Long> getMutual(Long userId) {
        List<Following> getFollowing = followingRepository.findAllByUserId(userId);
        Set<Long> getFollowingId=new HashSet<>();
        for(Following following:getFollowing)
        {
            getFollowingId.add(following.getGotId());
        }

        Set<Long> followingFollowing = new HashSet<>();

        for(Long x : getFollowingId)
        {
            List<Following> temp=followingRepository.findAllByUserId(x);
            for(Following chhotaFollowing : temp)
            {
                followingFollowing.add(chhotaFollowing.getGotId());
            }
        }
        followingFollowing.removeAll(getFollowingId);
        if(followingFollowing.contains(userId))
            followingFollowing.remove(userId);
        return followingFollowing;
    }
}
