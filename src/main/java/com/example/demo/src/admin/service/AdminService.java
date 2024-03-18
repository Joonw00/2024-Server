package com.example.demo.src.admin.service;

import com.example.demo.src.admin.repository.ReportHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ReportHistoryRepository reportHistoryRepository;


    // 신고 내역 조회, 차단 처리 등의 메소드 구현
}
