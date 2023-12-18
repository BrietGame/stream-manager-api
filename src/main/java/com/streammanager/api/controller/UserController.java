package com.streammanager.api.controller;

import com.streammanager.api.model.User;
import com.streammanager.api.model.dto.UserDTO;
import com.streammanager.api.response.ResponseJson;
import com.streammanager.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    System.Logger logger = System.getLogger(UserController.class.getName());
    
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseJson<List<User>> getAllUsers() {
        logger.log(System.Logger.Level.INFO, "Getting all users");
        return new ResponseJson<>(userService.getAll(), 200);
    }
    
    @GetMapping("/{id}")
    public ResponseJson<User> getUser(@PathVariable String id) {
        logger.log(System.Logger.Level.INFO, "Getting user with id: " + id);
        return new ResponseJson<>(userService.get(id), 200);
    }
    
    @PostMapping
    public ResponseJson<User> createUser(@RequestBody UserDTO userDTO) {
        logger.log(System.Logger.Level.INFO, "Creating user: " + userDTO);
        return new ResponseJson<>(userService.create(userDTO), 200);
    }
    
    @PutMapping("/{id}")
    public ResponseJson<User> updateUser(@PathVariable String id, @RequestBody User user) {
        logger.log(System.Logger.Level.INFO, "Updating user with id: " + id);
        user.setId(id);
        return new ResponseJson<>(userService.update(user), 200);
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        logger.log(System.Logger.Level.WARNING, "Deleting user with id: " + id);
        userService.delete(id);
    }
}
