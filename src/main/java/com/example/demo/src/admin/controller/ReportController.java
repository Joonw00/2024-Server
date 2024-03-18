package com.example.demo.src.admin.controller;

import com.example.demo.src.admin.service.ReportHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ReportController {
    private final ReportHistoryService reportHistoryService;

    @GetMapping("/logs")
    public String getLogs(@RequestParam(required = false) Date startDate,
                          @RequestParam(required = false) Date endDate) {
        // 로그 조회 로직 구현
        return "Logs";
    }
}
