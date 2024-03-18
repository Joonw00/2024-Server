package com.example.demo.src.admin.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String postId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String reason;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // Getter, Setter 생략
}
