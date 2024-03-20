package com.example.demo.src.admin.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ReportResponse {

    private Long id;
    private String reported;
    private String reason;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime reportedAt;

    // Constructors, Getters, and Setters
}
