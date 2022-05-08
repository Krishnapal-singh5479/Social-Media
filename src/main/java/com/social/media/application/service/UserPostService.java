package com.social.media.application.service;

import com.social.media.application.model.UserPost;
import com.social.media.application.repository.UserPostRepository;
import com.social.media.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPostService {

    @Autowired
    private  UserPostRepository userPostRepository;

    @Autowired
    private UserRepository userRepository;

    // It is use to save new post
    public void saveUserPost(UserPost userPost){
        userPostRepository.save(userPost);

    }

    // It returns all the post of specific user.
    public UserPost getPost(Long id) {
        return userPostRepository.findById(id).orElse(null);
    }

    // It returns all the post.
    public List<UserPost> getAllPost(long id) {
        return userRepository.getById(id).getAllPosts();
    }
}
