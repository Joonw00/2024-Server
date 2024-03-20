package com.example.demo.src.dashboard.repository;

// AttachmentRepository.java
import com.example.demo.src.dashboard.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
