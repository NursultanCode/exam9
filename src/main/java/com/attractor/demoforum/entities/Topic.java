package com.attractor.demoforum.entities;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "topic")
public class Topic {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,length = 32)
    private String title;
    @Column(nullable = false, length = 1024)
    private String content;
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @OneToMany(mappedBy = "topic")
    private List<Answer> answers;
}
