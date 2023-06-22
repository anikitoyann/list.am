package com.example.listam.controller;

import com.example.listam.entity.User;
import com.example.listam.entity.UserType;
import com.example.listam.repository.UserRepository;
import com.example.listam.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MailService mailService;
    @Value("${site.url}")
    private String siteUrl;


    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        Optional<User> userFromDB = userRepository.findByEmail(user.getEmail());
        if (userFromDB.isEmpty()) {
            String password = user.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setUserType(UserType.USER);
            user.setEnabled(false);
            UUID token = UUID.randomUUID();
            user.setToken(token.toString());
            userRepository.save(user);
//send mail
            mailService.sendMail(user.getEmail(), "Welcome", "Hi" + user.getName() + "\n" +
                    "please verify your email by clicking on this url: " +
                    siteUrl + "/user/verify?email=" + user.getEmail() + "&token="+token);
        }
        return "redirect:/";
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("email") String email, @RequestParam("token") String token) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isEmpty()) {
            return "redirect:/";
        }
        if (byEmail.get().isEnabled()) {
            return "redirect:/";
        }
        if (byEmail.get().getToken().equals(token)) {
            User user = byEmail.get();
            user.setEnabled(true);
            user.setToken(null);
            userRepository.save(user);
        }
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
