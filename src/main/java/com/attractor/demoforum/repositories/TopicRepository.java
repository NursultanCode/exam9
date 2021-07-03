package com.attractor.demoforum.repositories;

import com.attractor.demoforum.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic findTopById(Long id);
}
