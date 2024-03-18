package com.example.demo.src.admin.service;

import com.example.demo.src.admin.entity.ReportHistory;
import com.example.demo.src.admin.repository.ReportHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportHistoryService {
    private final ReportHistoryRepository reportHistoryRepository;

    // 로그 조회, 기록 등의 메소드 구현 생략
}