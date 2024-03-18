package com.example.demo.src.admin.controller;

import com.example.demo.src.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    // API 엔드포인트 메소드 구현
}
