package com.example.demo.src.user.entity;

import com.example.demo.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@Getter
@Entity // 필수, Class 를 Database Table화 해주는 것이다
@Table(name = "USER") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class User extends BaseEntity {

    @Id // PK를 의미하는 어노테이션
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private boolean isOAuth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoginType loginType; // 로그인 타입 (SELF, KAKAO, NAVER, GOOGLE, APPLE 등)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus accountStatus; // 계정 상태 (ACTIVE, DORMANT, BLOCKED, WITHDRAWN 등)

    private Boolean isPersonalInfoAgreed; // 개인정보 처리 동의 여부
    private LocalDateTime personalInfoAgreementDate; // 개인정보 처리 동의 일시
    @Builder
    public User(String email, String password, String name, Boolean isOAuth, LoginType loginType, AccountStatus accountStatus, Boolean isPersonalInfoAgreed, LocalDateTime personalInfoAgreementDate) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.isOAuth = isOAuth;
        this.loginType = loginType;
        this.accountStatus = accountStatus;
        this.isPersonalInfoAgreed = isPersonalInfoAgreed;
        this.personalInfoAgreementDate = personalInfoAgreementDate;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void deleteUser() {
        this.state = State.INACTIVE;
    }

}

// 로그인 타입을 위한 열거형 예시
enum LoginType {
    SELF, KAKAO, NAVER, GOOGLE, APPLE
}

// 계정 상태를 위한 열거형 예시
enum AccountStatus {
    ACTIVE, DORMANT, BLOCKED, WITHDRAWN
}