package com.example.demo.src.admin.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ReportHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reporter;
    private String reported;
    private String reason;
    private LocalDateTime reportedAt;

    // CUD 이력 관리를 위한 필드
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String updatedBy;

    // Getters and Setters
}
