package com.elegant.socialnetwork.repository;

import com.elegant.socialnetwork.models.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
    @Query("select u from User u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
    public List<User> searchUser(@Param("query") String query);
    @Query("select u from User u JOIN u.savedPost sp WHERE sp.id = :postId")
    public List<User> findAllBySavedPost(@Param("postId") Integer postId);
}
