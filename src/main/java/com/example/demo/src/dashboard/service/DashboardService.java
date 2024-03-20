package com.example.demo.src.dashboard.service;

import com.example.demo.src.admin.entity.ReportHistory;
import com.example.demo.src.dashboard.model.Attachment;
import com.example.demo.src.dashboard.repository.AttachmentRepository;
import com.example.demo.src.admin.repository.ReportHistoryRepository;
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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


//TODO : update에서 로그가 이상하게 뜸 -> reponse 수정할 때 참고

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final AttachmentRepository attachmentRepository;
    private final ReportHistoryRepository reportHistoryRepository;

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
    public Post createPost(Post post, List<MultipartFile> files) {
        // 게시물 저장
        Post savedPost = dashboardRepository.save(post);

        // 첨부파일 처리
//        if (files != null && !files.isEmpty()) {
//            List<Attachment> attachments = new ArrayList<>();
//            int imageCount = 0;
//
//            for (MultipartFile file : files) {
//                if (imageCount >= 5) {
//                    break; // 이미지 파일이 5개를 초과하면 더 이상 처리하지 않음
//                }
//
//                if (isImage(file)) { // 파일이 이미지인 경우에만 처리
//                    // 파일을 저장소에 저장하고, URL을 받아옵니다.
//                    // TODO : 실제 데이터 저장 로직 구현할 것.
////                    String fileUrl = yourFileStorageService.uploadFile(file);
//                    String fileUrl = null;
//                    // 첨부파일 객체 생성
//                    Attachment attachment = new Attachment(fileUrl, Attachment.FileType.IMAGE, savedPost);
//                    attachments.add(attachment);
//                    imageCount++;
//                }
//                if (isVideo(file)) { // 동영상 파일 처리
////                    String fileUrl = yourFileStorageService.uploadFile(file);
//                    String fileUrl = null;
//                    Attachment attachment = new Attachment(fileUrl, Attachment.FileType.VIDEO, savedPost);
//                    attachments.add(attachment);
//                }
//            }
//            // 첨부파일 정보 저장
//            if (!attachments.isEmpty()) {
//                attachmentRepository.saveAll(attachments);
//            }
//        }

        return savedPost;
    }

    //첨부파일 유형 제한
    private boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image");
    }


    private boolean isVideo(MultipartFile file) {
        String mimeType = file.getContentType();
        return mimeType != null && mimeType.startsWith("video/");
    }

    // 파일 타입 결정 메서드 (실제 구현 필요)
    private Attachment.FileType determineFileType(MultipartFile file) {
        // 파일 타입에 따라 IMAGE 또는 VIDEO 등을 반환합니다.
        return Attachment.FileType.IMAGE; // 예시
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


    // TODO : 단일책임 지키게 수정할 것
    public void reportPost(Long postId, String reason) {
        Post post = dashboardRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("Post not found with id " + postId));
        post.markAsInvisible();
        dashboardRepository.save(post);

        // ReportHistory 기록
        ReportHistory reportHistory = new ReportHistory();
        reportHistory.setReason(reason);
        reportHistory.setReportedAt(LocalDateTime.now());
        reportHistory.setCreatedAt(LocalDateTime.now());
        reportHistory.setUpdatedAt(LocalDateTime.now());

        reportHistoryRepository.save(reportHistory);
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
