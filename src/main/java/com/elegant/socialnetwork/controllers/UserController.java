package com.elegant.socialnetwork.controllers;

import com.elegant.socialnetwork.models.User;
import com.elegant.socialnetwork.repository.UserRepository;
import com.elegant.socialnetwork.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) throws Exception {
        User user = userService.findUserById(id);
        return user;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return savedUser;
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable int id) throws Exception {
        User updatUser = userService.updateUser(user, id);
        return updatUser;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User no exists with id " + id));

        userRepository.delete(oldUser);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
        User user = userService.followUser(userId1, userId2);
        return user;
    }

    @GetMapping("/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        List<User> users = userService.searchUser(query);
        return users;
    }
}
