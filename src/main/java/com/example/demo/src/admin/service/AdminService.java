package com.example.demo.src.admin.service;

import com.example.demo.src.dashboard.model.Post;
import com.example.demo.src.dashboard.repository.DashboardRepository;
import com.example.demo.src.admin.entity.ReportHistory;
import com.example.demo.src.admin.repository.ReportHistoryRepository;
import com.example.demo.src.user.entity.User;
import com.example.demo.src.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final DashboardRepository dashboardRepository;
    private final ReportHistoryRepository reportHistoryRepository;

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserDetails(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return dashboardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post getPostDetails(Long postId) {
        return dashboardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
    }

    @Transactional(readOnly = true)
    public List<ReportHistory> getAllReports() {
        return reportHistoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ReportHistory getReportDetails(Long reportId) {
        return reportHistoryRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("Report not found with id: " + reportId));
    }
}
