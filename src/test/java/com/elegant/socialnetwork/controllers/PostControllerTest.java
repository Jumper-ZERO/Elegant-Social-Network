package com.elegant.socialnetwork.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.elegant.socialnetwork.models.Post;
import com.elegant.socialnetwork.services.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PostController.class)
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PostService postService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void testCreatePost() throws Exception {
        Post post = new Post(1, "Caption example", "example-image.png", "example-video.mp4", null, LocalDateTime.now());

        int userId = 1;

        when(postService.createNewPost(any(Post.class), eq(userId))).thenReturn(post);

        mockMvc.perform(post("/posts/user/{userId}", userId)
            .with(csrf())
            .with(httpBasic("user", "password"))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(post)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.caption").value("Caption example"));
        
        verify(postService).createNewPost(any(Post.class), eq(userId));
    }
}
