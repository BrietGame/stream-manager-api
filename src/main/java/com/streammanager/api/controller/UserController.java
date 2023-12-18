package com.streammanager.api.controller;

import com.streammanager.api.model.User;
import com.streammanager.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    
    System.Logger logger = System.getLogger(UserController.class.getName());
    
    UserService userService;
    
    @GetMapping
    public List<User> getAllUsers() {
        logger.log(System.Logger.Level.INFO, "Getting all users");
        return userService.getAll();
    }
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        logger.log(System.Logger.Level.INFO, "Getting user with id: " + id);
        return userService.get(id);
    }
    
    @PostMapping
    public User createUser(@RequestBody User user) {
        logger.log(System.Logger.Level.INFO, "Creating user: " + user);
        return userService.create(user);
    }
    
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        logger.log(System.Logger.Level.INFO, "Updating user with id: " + id);
        user.setId(id);
        return userService.update(user);
    }
    
    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable String id) {
        logger.log(System.Logger.Level.WARNING, "Deleting user with id: " + id);
        return userService.delete(id);
    }
}
