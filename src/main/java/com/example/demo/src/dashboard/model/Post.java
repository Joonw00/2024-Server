package com.example.demo.src.dashboard.model;

import com.example.demo.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import com.example.demo.src.user.entity.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// TODO : 날짜 형식 수정 -> response 수정하면서 같이할 것
@Entity
@Getter
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 4000)
    private String content;

    @Column(nullable = false)
    private boolean visible = true;

    @ManyToOne(fetch = FetchType.LAZY) // 게시물과 사용자는 다대일 관계
    @JoinColumn(name = "user_id") // 외래키가 될 컬럼명 지정
    private User user; // 게시물의 작성자


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments = new ArrayList<>(); // 첨부파일


    // 상태 변경 메서드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        // 추가적인 유효성 검사나 로직을 구현
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void markAsInvisible() {
        this.visible = false;
        // 추가 로직을 여기에 구현
    }


}
