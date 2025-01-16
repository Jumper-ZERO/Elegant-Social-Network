package com.elegant.socialnetwork.services;

import java.util.List;

import com.elegant.socialnetwork.models.User;

public interface UserService {
    public User registerUser(User user);

    public User findUserById(Integer userId) throws Exception;

    public User findUserByEmail(String email);

    public User followUser(Integer userId1, Integer UserId2) throws Exception;

    public User updateUser(User user, Integer userId) throws Exception;

    public List<User> searchUser(String query);
}
