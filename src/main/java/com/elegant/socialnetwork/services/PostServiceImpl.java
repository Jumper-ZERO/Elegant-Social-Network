package com.elegant.socialnetwork.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.elegant.socialnetwork.models.Post;
import com.elegant.socialnetwork.models.User;
import com.elegant.socialnetwork.repository.PostRepository;
import com.elegant.socialnetwork.repository.UserRepository;

public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        // newPost.setCreatedAt(new LocalDateTime);
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);
        return newPost;
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if (post.getUser().getId() != user.getId()) {
            throw new Exception("You can't delete another user posts");
        }
        postRepository.delete(post);
        return "Post deleted successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> opt = postRepository.findById(postId);
        if (opt.isEmpty()) {
            throw new Exception("Post not found with id " + postId);
        }
        return opt.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (user.getSavedPost().contains(post)) user.getSavedPost().remove(post);
        else user.getSavedPost().add(post);

        userRepository.save(user);

        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        
        if (post.getLiked().contains(user)) post.getLiked().remove(user);
        else post.getLiked().add(user);

        return postRepository.save(post);
    }
    
}