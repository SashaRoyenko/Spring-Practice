package com.royenko.controller;

import com.royenko.domain.User;
import com.royenko.domain.enums.Role;
import com.royenko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String email,
            @RequestParam String role,
            @RequestParam("userId") User user){
        user.setEmail(email);
        user.setRole(Role.valueOf(role));
        userRepository.save(user);
        return "redirect:/user";
    }
}
