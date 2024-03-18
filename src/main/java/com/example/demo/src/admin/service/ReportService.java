package com.example.demo.src.admin.service;

import com.example.demo.src.admin.entity.Report;
import com.example.demo.src.admin.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;


    // 신고 처리, 조회 등의 메소드 구현 생략
}