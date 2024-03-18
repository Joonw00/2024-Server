package com.example.demo.src.admin.repository;

import com.example.demo.src.admin.entity.ReportHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportHistoryRepository extends JpaRepository<ReportHistory, Long> {
    // 필요한 메소드 정의
}
