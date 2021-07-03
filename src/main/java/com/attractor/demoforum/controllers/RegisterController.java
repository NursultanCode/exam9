package com.attractor.demoforum.controllers;

import com.attractor.demoforum.entities.User;
import com.attractor.demoforum.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class RegisterController {
    private final UserRepository userRepository;
    @GetMapping("register")
    public String displayRegister(){
        return "register";
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("register")
    public View registerUser(@RequestParam("username") String username, @RequestParam("password")String password,
                             @RequestParam("introduction") String introduction, HttpServletRequest request){
        String contextPath = request.getContextPath();
        User user = new User();
        if (userRepository.getUserByUsername(username)==null){
            user.setUsername(username);
            if (Objects.equals(introduction,""))user.setIntroduction(null);
            else user.setIntroduction(introduction);
            user.setPassword(passwordEncoder().encode(password));
            user.setCreatedDate(LocalDateTime.now());
            userRepository.save(user);
            return new RedirectView(contextPath+"/login");
        }

        return new  RedirectView(contextPath+"/register");
    }
}
