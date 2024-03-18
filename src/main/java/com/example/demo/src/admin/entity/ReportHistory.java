package com.example.demo.src.admin.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "report_history")
public class ReportHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long reportId; // 원본 신고 ID

    @Column(nullable = false, length = 500)
    private String changedData; // 변경된 데이터의 JSON 형태 저장

    @Temporal(TemporalType.TIMESTAMP)
    private Date changedAt; // 변경 시간

    // Getter, Setter 생략
}
