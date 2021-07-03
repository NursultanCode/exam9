package com.attractor.demoforum.controllers;

import com.attractor.demoforum.entities.Topic;
import com.attractor.demoforum.repositories.AnswerRepository;
import com.attractor.demoforum.repositories.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class TopicsController {
    private final TopicRepository topicRepository;
    private final AnswerRepository answerRepository;

    @GetMapping("topics")
    public String displayAllTopics(Model model){
        List<Topic> topics = topicRepository.findAll(Sort.by(Sort.Direction.DESC,"createdDate"));
        model.addAttribute("topics", topics);
        model.addAttribute("answerRepository", answerRepository);
        return "topics";
    }
}
