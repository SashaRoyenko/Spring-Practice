package com.royenko;

import com.royenko.domain.User;
import com.royenko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "main";
    }

    @PostMapping("register")
    public String add(@RequestParam String firstName, @RequestParam String lastName,
                      @RequestParam String phone, @RequestParam String email, @RequestParam String password,
                      Map<String, Object> model) {
        User user = User.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .phone(phone)
                .build();
        userRepository.save(user);

        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "main";
    }
}