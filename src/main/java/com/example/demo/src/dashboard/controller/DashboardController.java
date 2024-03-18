package com.example.demo.src.dashboard.controller;

import com.example.demo.src.dashboard.model.Post;
import com.example.demo.src.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    // 목록 조회
    @GetMapping("/posts")
    public ResponseEntity<?> getPosts(@RequestParam(required = false) Integer pageIndex,
                                      @RequestParam(required = false) Integer size) {
        // pageIndex와 size의 유효성 검사
        if (pageIndex == null || size == null || pageIndex < 0 || size <= 0) {
            return ResponseEntity.badRequest().body("Invalid pageIndex or size");
        }
        return ResponseEntity.ok(dashboardService.getPosts(pageIndex, size));
    }

    // 게시물 생성
    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(dashboardService.createPost(post));
    }

    // 게시물 수정
    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody Post post) {
        return ResponseEntity.ok(dashboardService.updatePost(postId, post));
    }

    // 게시물 삭제
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        try {
            dashboardService.deletePost(postId);
            return ResponseEntity.ok().body(Map.of(
                    "status", "success",
                    "data", Map.of("message", "Post deleted successfully")
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    // 게시물 신고
    @PostMapping("/posts/{postId}/report")
    public ResponseEntity<?> reportPost(@PathVariable Long postId) {
        try {
            dashboardService.reportPost(postId);
            return ResponseEntity.ok().body(Map.of(
                    "status", "success",
                    "data", Map.of("message", "Post reported successfully")
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

}
