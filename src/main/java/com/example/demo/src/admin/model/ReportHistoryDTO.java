package com.example.demo.src.admin.model;

import java.util.Date;

public class ReportHistoryDTO {
    private Long id;
    private Long reportId;
    private String changedData;
    private Date changedAt;

    // 생성자, Getter와 Setter
    public ReportHistoryDTO() {
    }

    public ReportHistoryDTO(Long id, Long reportId, String changedData, Date changedAt) {
        this.id = id;
        this.reportId = reportId;
        this.changedData = changedData;
        this.changedAt = changedAt;
    }

    // Getter와 Setter 생략
}
