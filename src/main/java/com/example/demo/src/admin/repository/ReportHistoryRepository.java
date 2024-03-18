package com.example.demo.src.admin.repository;

import com.example.demo.src.admin.entity.ReportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportHistoryRepository extends JpaRepository<ReportHistory, Long> {
}