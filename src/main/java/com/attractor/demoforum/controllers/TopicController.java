package com.attractor.demoforum.controllers;

import com.attractor.demoforum.entities.Answer;
import com.attractor.demoforum.entities.Topic;
import com.attractor.demoforum.repositories.AnswerRepository;
import com.attractor.demoforum.repositories.TopicRepository;
import com.attractor.demoforum.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class TopicController {
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final AnswerRepository answerRepository;

    @GetMapping("topic/{id}")
    public String displayTopic(@PathVariable String id, Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        Long idUser = userRepository.getUserByUsername(username).getId();

        Topic topic = topicRepository.findTopicById(Long.valueOf(id));
        List<Answer> answers = answerRepository.findAnswerByTopic_Id(Long.valueOf(id));
        model.addAttribute("topic", topic);
        model.addAttribute("answers", answers);
        model.addAttribute("idUser", idUser);
        return "topic";
    }
}
