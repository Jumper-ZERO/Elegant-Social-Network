package com.elegant.socialnetwork.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private List<Integer> followers = new ArrayList<>();
    private List<Integer> followings = new ArrayList<>();
    @JsonIgnore
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String password, String email, String lastName, String firstName, List<Integer> followers, List<Integer> followings) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.followers = followers;
        this.followings = followings;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return List<Integer> return the followers
     */
    public List<Integer> getFollowers() {
        return followers;
    }

    /**
     * @param followers the followers to set
     */
    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    /**
     * @return List<Integer> return the followings
     */
    public List<Integer> getFollowings() {
        return followings;
    }

    /**
     * @param followings the followings to set
     */
    public void setFollowings(List<Integer> followings) {
        this.followings = followings;
    }


    /**
     * @return List<Post> return the savedPost
     */
    public List<Post> getSavedPost() {
        return savedPost;
    }

    /**
     * @param savedPost the savedPost to set
     */
    public void setSavedPost(List<Post> savedPost) {
        this.savedPost = savedPost;
    }

}
