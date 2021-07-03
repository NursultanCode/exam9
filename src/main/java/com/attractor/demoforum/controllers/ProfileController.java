package com.attractor.demoforum.controllers;

import com.attractor.demoforum.entities.Topic;
import com.attractor.demoforum.entities.User;
import com.attractor.demoforum.repositories.AnswerRepository;
import com.attractor.demoforum.repositories.TopicRepository;
import com.attractor.demoforum.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class ProfileController {
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final AnswerRepository answerRepository;

@GetMapping("profile")
    public String displayMyProfile(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userRepository.getUserByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("profile")
    public RedirectView addTask(@RequestParam("title")String title, @RequestParam("content")String content,
                                @RequestParam("id_user")String id_user, HttpServletRequest request){
        Topic topic = new Topic();
        topic.setContent(content);
        topic.setTitle(title);
        topic.setCreatedDate(LocalDateTime.now());
        topic.setUser(userRepository.getUserById(Long.valueOf(id_user)));
        topicRepository.save(topic);
        String contentPath = request.getContextPath();
        return new  RedirectView(contentPath+"/profile");


    }
}
