package com.example.carrental.controller;

import com.example.carrental.repo.SupportRepository;
import com.example.carrental.service.UserService;
import com.example.carrental.springsecurity.model.Role;
import com.example.carrental.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/support")
public class SupportController {

    private final SupportRepository supportRepository;
    private final UserService userService;

    @Autowired
    public SupportController(SupportRepository supportRepository, UserService userService) {
        this.supportRepository = supportRepository;
        this.userService = userService;
    }

    @GetMapping
    public String getPageSupport(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        verificationUser(currentUser, model);
        model.addAttribute("feedbacks", supportRepository.getAll());
        return "support";
    }

    @PostMapping("/add")
    public String addFeedback(@AuthenticationPrincipal UserDetails currentUser, @RequestParam(required = false) String text, Model model) {
        if (!text.equals("")) {
            verificationUser(currentUser, model);
            supportRepository.add(currentUser.getUsername(), text);
            model.addAttribute("added", true);
            model.addAttribute("feedbacks", supportRepository.getAll());
            return "support";
        } else {
            model.addAttribute("notAdded", true);
            return getPageSupport(currentUser, model);
        }
    }

    @PostMapping("/delete")
    public String deleteFeedback(@AuthenticationPrincipal UserDetails currentUser, @RequestParam(required = false) Integer id, Model model) {
        if (id == null) {
            return "redirect:/support";
        }
        verificationUser(currentUser, model);
        supportRepository.delete(id);
        model.addAttribute("deleted", true);
        model.addAttribute("feedbacks", supportRepository.getAll());
        return "support";
    }

    private void verificationUser(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findUserByEmail(currentUser.getUsername()).orElse(null);
        if (user != null && user.getRole() == Role.ADMIN) {
            model.addAttribute("user", "ADMIN");
        } else {
            model.addAttribute("user", "USER");
        }
    }
}
