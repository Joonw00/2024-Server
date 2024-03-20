package com.example.demo.src.admin.controller;

import com.example.demo.src.admin.service.AdminService;
import com.example.demo.src.dashboard.model.Post;
import com.example.demo.src.admin.entity.ReportHistory;
import com.example.demo.src.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    // 모든 유저 정보 조회
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // 특정 유저 상세 정보 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long userId) {
        User user = adminService.getUserDetails(userId);
        return ResponseEntity.ok(user);
    }

    // 모든 포스트 정보 조회
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = adminService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 특정 포스트 상세 정보 조회
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPostDetails(@PathVariable Long postId) {
        Post post = adminService.getPostDetails(postId);
        return ResponseEntity.ok(post);
    }

    // 모든 신고 정보 조회
    @GetMapping("/reports")
    public ResponseEntity<List<ReportHistory>> getAllReports() {
        List<ReportHistory> reports = adminService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    // 특정 신고 상세 정보 조회
    @GetMapping("/reports/{reportId}")
    public ResponseEntity<ReportHistory> getReportDetails(@PathVariable Long reportId) {
        ReportHistory reportHistory = adminService.getReportDetails(reportId);
        return ResponseEntity.ok(reportHistory);
    }
}
