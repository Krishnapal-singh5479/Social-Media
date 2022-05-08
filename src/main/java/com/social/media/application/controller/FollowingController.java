package com.social.media.application.controller;

import com.social.media.application.model.Following;
import com.social.media.application.service.FollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class FollowingController {
    @Autowired
    private FollowingService followingService;

    // Post mapping for establishing connection between users
    @PostMapping("/follow/{userId}/{gotId}")
    public void makeRelation(@PathVariable Long userId,@PathVariable Long gotId)
    {
        followingService.makeRelation(new Following(userId,gotId));
    }

    // Get mapping for return a list of all users who are followed by specific user.
    @GetMapping("/getFollowing/{userId}")
    public List<String> getFollowing(@PathVariable Long userId)
    {
        return followingService.getFollowing(userId);
    }

    // Get mapping for return a list of user who follows a specific user.
    @GetMapping("/getFollowers/{userId}")
    public List<String> getFollowers(@PathVariable Long userId)
    {
        return followingService.getFollowers(userId);
    }

    // Get mapping for return a list of all 2nd degree connections.
    @GetMapping("mutual/{userId}")
    public Set<Long> getMutual(@PathVariable Long userId)
    {
        return followingService.getMutual(userId);
    }
}
