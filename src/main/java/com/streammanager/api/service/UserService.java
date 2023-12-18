package com.streammanager.api.service;

import com.streammanager.api.mapper.UserMapper;
import com.streammanager.api.model.User;
import com.streammanager.api.model.dto.UserDTO;
import com.streammanager.api.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    UserRepository userRepository;
    
    UserMapper userMapper = new UserMapper();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
    
    public User get(String id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public User getUserByLogin(String login) {
        return userRepository.findByEmailOrUsername(login, login);
    }
    
    public User create(UserDTO userDTO) {
        return userRepository.save(userMapper.toEntity(userDTO));
    }
    
    public User update(User user) {
        return userRepository.save(user);
    }
    
    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
