package com.example.demo.src.dashboard.repository;

import com.example.demo.src.dashboard.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends JpaRepository<Post, Long> {
    // 필요한 JPA 메소드를 정의하거나 커스텀 메소드를 추가할 수 있습니다.
}
