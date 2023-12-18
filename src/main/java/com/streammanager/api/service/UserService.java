package com.streammanager.api.service;

import com.streammanager.api.model.User;
import com.streammanager.api.respository.UserRepository;

import java.util.List;

public class UserService {
    
    UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }
    
    public User get(String id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public User create(User user) {
        return userRepository.save(user);
    }
    
    public User update(User user) {
        return userRepository.save(user);
    }
    
    public User delete(String id) {
        User user = userRepository.findById(id).orElse(null);
        userRepository.deleteById(id);
        return user;
    }
}
