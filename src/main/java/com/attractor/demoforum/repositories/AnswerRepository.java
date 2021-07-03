package com.attractor.demoforum.repositories;

import com.attractor.demoforum.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    List<Answer> findAnswerByTopic_Id(Long topicId);
}
