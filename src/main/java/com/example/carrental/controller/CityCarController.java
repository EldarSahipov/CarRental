package com.example.carrental.controller;

import com.example.carrental.service.CityCarService;
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
@RequestMapping("city-car")
public class CityCarController {
    private final CityCarService cityCarService;
    private final UserService userService;

    @Autowired
    public CityCarController(CityCarService cityCarService, UserService userService) {
        this.cityCarService = cityCarService;
        this.userService = userService;
    }

    @GetMapping()
    public String getCityCar(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        verificationUser(currentUser, model);
        model.addAttribute("cities", cityCarService.getAll());
        return "CityCar";
    }

    @PostMapping("/add")
    public String addCityCar(@AuthenticationPrincipal UserDetails currentUser, @RequestParam String nameCityCar, Model model) {
        verificationUser(currentUser, model);
        cityCarService.add(nameCityCar);
        return "CityCar";
    }

    @PostMapping("/delete")
    public String deleteCityCar(@AuthenticationPrincipal UserDetails currentUser, @RequestParam int idCityCar, Model model) {
        verificationUser(currentUser, model);
        cityCarService.delete(idCityCar);
        return "CityCar";
    }

    @PostMapping("/update")
    public String updateCityCar(@AuthenticationPrincipal UserDetails currentUser, @RequestParam String nameCityCar, @RequestParam int idCityCar, Model model) {
        verificationUser(currentUser, model);
        cityCarService.update(nameCityCar, idCityCar);
        return "CityCar";
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
