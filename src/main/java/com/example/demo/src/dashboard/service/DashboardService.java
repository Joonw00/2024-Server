package com.example.demo.src.dashboard.service;

import com.example.demo.src.dashboard.model.Post;
import com.example.demo.src.dashboard.repository.DashboardRepository;
import com.example.demo.src.user.entity.User;
import com.example.demo.src.user.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.demo.utils.JwtService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO : update에서 로그가 이상하게 뜸 -> reponse 수정할 때 참고

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    public List<Post> getPosts(int pageIndex, int size) {
        Pageable pageable = PageRequest.of(pageIndex, size, Sort.by("id").descending());
        Page<Post> postPage = dashboardRepository.findAll(pageable);
        return postPage.getContent();
    }


    public Post createPost(Post post) {
        validatePost(post); // 게시물 유효성 검사

        Long jwtUserId = jwtService.getUserId(); // 현재 로그인된 사용자의 ID 추출

        // 사용자 ID를 기반으로 User 엔티티 조회
        User user = userRepository.findById(jwtUserId) // userRepository 인스턴스를 사용하도록 수정
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다."));

        // Post 엔티티에 User 엔티티 연결
        post.setUser(user);

        // Post 엔티티를 데이터베이스에 저장
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
        // 게시물 제목의 최소 글자수와 최대 글자수 설정
        int titleMinLength = 1; // 1글자를 최소 길이로 설정
        int titleMaxLength = 255; // TODO :  데이터베이스와 일치하는 길이로 설정할 것

        // 게시물 내용의 최대 글자수 설정
        int contentMaxLength = 4000;

        if (post.getTitle() == null || post.getTitle().trim().length() < titleMinLength || post.getTitle().trim().length() > titleMaxLength) {
            throw new IllegalArgumentException("Title must be between " + titleMinLength + " and " + titleMaxLength + " characters long");
        }
        if (post.getContent() == null || post.getContent().length() > contentMaxLength) {
            throw new IllegalArgumentException("Content must be up to " + contentMaxLength + " characters long");
        }
        // 여기에 더 많은 필드에 대한 유효성 검사를 추가할 수 있습니다.
    }
}
