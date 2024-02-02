package com.example.service;

import com.example.controller.request.CommentRequest;
import com.example.controller.request.PostRequest;
import com.example.controller.response.PostDetailResponse;
import com.example.domain.Post;
import com.example.repository.CommentRepository;
import com.example.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    public void addPost(PostRequest postRequest) {
        Post post = Post.from(postRequest);
        postRepository.save(post);
    }

    public PostDetailResponse getPost(Long id) {
        System.out.println("id = " + id);
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return PostDetailResponse.from(post);
    }

    public void addComment(Long id, CommentRequest commentRequest) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        commentRepository.save(post.addComment(commentRequest));
    }
}
