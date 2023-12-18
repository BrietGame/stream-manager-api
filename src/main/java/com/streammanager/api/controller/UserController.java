package com.streammanager.api.controller;

import com.streammanager.api.model.User;
import com.streammanager.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/user")
public class UserController {
    
    UserService userService;
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.get(id);
    }
    
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }
    
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        user.setId(id);
        return userService.update(user);
    }
    
    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable String id) {
        return userService.delete(id);
    }
}
