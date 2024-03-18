package com.example.demo.src.admin.model;

import java.util.Date;

public class ReportDTO {
    private Long id;
    private String postId;
    private String userId;
    private String reason;
    private Date createdAt;

    // 생성자, Getter와 Setter
    public ReportDTO() {
    }

    public ReportDTO(Long id, String postId, String userId, String reason, Date createdAt) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    // Getter와 Setter 생략
}
