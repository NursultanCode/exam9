package com.attractor.demoforum.entities;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, length = 1024)
    private String content;
    @Column(nullable = false)
    private boolean useful;
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_topic")
    private Topic topic;
}
