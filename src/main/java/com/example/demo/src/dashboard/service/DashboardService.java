package com.example.demo.src.dashboard.service;

import com.example.demo.src.dashboard.model.Post;
import com.example.demo.src.dashboard.repository.DashboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardRepository dashboardRepository;


    public List<Post> getPosts(int pageIndex, int size) {
        Pageable pageable = PageRequest.of(pageIndex, size, Sort.by("id").descending());
        Page<Post> postPage = dashboardRepository.findAll(pageable);
        return postPage.getContent();
    }

    public Post createPost(Post post) {
        validatePost(post);
        // 게시물 생성 전 유효성 검사 로직이 필요하다면, validatePost 메서드에서 구현합니다.
        return dashboardRepository.save(post);
    }

    public Post updatePost(Long postId, Post updatedPost) {
        return dashboardRepository.findById(postId)
                .map(post -> {
                    post.update(updatedPost.getTitle(), updatedPost.getContent());
                    // 게시물 수정 전 유효성 검사가 필요하다면, 여기서 수행하거나 update 메서드 내부에서 수행
                    return dashboardRepository.save(post);
                }).orElseThrow(() -> new IllegalArgumentException("Post not found with id " + postId));
    }
    public void deletePost(Long postId) {
        if (!dashboardRepository.existsById(postId)) {
            throw new IllegalArgumentException("Post not found with id " + postId);
        }
        dashboardRepository.deleteById(postId);
    }


    public void reportPost(Long postId) {
        Post post = dashboardRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("Post not found with id " + postId));
        post.markAsInvisible();
        dashboardRepository.save(post);
    }



    private void validatePost(Post post) {
        // 게시물의 유효성 검사 로직을 구현합니다.  텍스트 길이나 첨부 파일의 유형 및 개수 검사 등
        if (post.getTitle() == null || post.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (post.getContent() == null || post.getContent().length() > 4000) {
            throw new IllegalArgumentException("Content is too long");
        }
        // 필요에 따라 추가 유효성 검사 로직을 구현합니다.
    }
}
