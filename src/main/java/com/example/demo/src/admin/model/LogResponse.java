package com.example.demo.src.admin.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class LogResponse {

    private Long id;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    private String logLevel; // 로그 레벨(INFO, WARN, ERROR 등)을 나타내는 필드

    // Constructors, Getters, and Setters
}
