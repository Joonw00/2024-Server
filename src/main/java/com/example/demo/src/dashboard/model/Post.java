package com.example.demo.src.dashboard.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 4000)
    private String content;

    @Column(nullable = false)
    private boolean visible = true;


    // 상태 변경 메서드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        // 추가적인 유효성 검사나 로직을 구현
    }

    public void markAsInvisible() {
        this.visible = false;
        // 추가 로직을 여기에 구현
    }
}
