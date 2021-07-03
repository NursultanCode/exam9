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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class TopicController {
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final AnswerRepository answerRepository;

    @GetMapping("topic/{id}")
    public String displayTopic(@PathVariable String id, Model model){
        Long idUser=1000l;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principal.equals("anonymousUser")){
            String username = ((UserDetails)principal).getUsername();
            idUser = userRepository.getUserByUsername(username).getId();
        }

        Topic topic = topicRepository.findTopicById(Long.valueOf(id));
        List<Answer> answers = answerRepository.findAnswerByTopic_Id(Long.valueOf(id));
        model.addAttribute("topic", topic);
        model.addAttribute("answers", answers);
        model.addAttribute("idUser", idUser);
        return "topic";
    }
@PostMapping("topic")
    public RedirectView addAnswer(@RequestParam("content")String content, @RequestParam("id_topic")String id_topic,
                                  @RequestParam("id_user") String id_user, HttpServletRequest request){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreatedDate(LocalDateTime.now());
        answer.setTopic(topicRepository.findTopicById(Long.valueOf(id_topic)));
        answer.setUser(userRepository.getUserById(Long.valueOf(id_user)));
        answerRepository.save(answer);
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath+"/topic/"+id_topic);
    }
}
