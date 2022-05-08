package com.social.media.application.service;

import com.social.media.application.model.User;
import com.social.media.application.model.UserModel;
import com.social.media.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // It is use to add new user
    public long addUser(User user) {
        return userRepository.save(user).getId();
    }

    // It is validates the user.
    public boolean login(UserModel userModel) {
        User user=userRepository.findByUsernameAndPassword(userModel.getUsername(), userModel.getPassword());
        return !(user==null);
    }
}
