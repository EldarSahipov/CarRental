package com.example.carrental.controller;

import com.example.carrental.controller.valid.ValidRegex;
import com.example.carrental.service.BrandCarService;
import com.example.carrental.service.UserService;
import com.example.carrental.springsecurity.model.Role;
import com.example.carrental.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("brand-car")
public class BrandCarController {
    private final BrandCarService brandCarService;
    private final UserService userService;

    @Autowired
    public BrandCarController(BrandCarService bodyTypeService, UserService userService) {
        this.brandCarService = bodyTypeService;
        this.userService = userService;
    }

    @GetMapping()
    public String getBrandCar(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        verificationUser(currentUser, model);
        model.addAttribute("brands", brandCarService.getAll());
        return "BrandCar";
    }

    @PostMapping("/add")
    public String addBrandCar(@AuthenticationPrincipal UserDetails currentUser,
                              @RequestParam String nameBrandCar, Model model) {
        verificationUser(currentUser, model);
        String regex = "^([А-Я]{1,23}[а-яё]{1,23}|[A-Z]{1,23}[a-z]{1,23})$";
        if(!ValidRegex.validRegex(regex, nameBrandCar)) {
            model.addAttribute("notValidNameBrand", true);
            model.addAttribute("brands", brandCarService.getAll());
            return "BrandCar";
        }
        if(!(brandCarService.getBrandCarByName(nameBrandCar) == null)) {
            model.addAttribute("brands", brandCarService.getAll());
            model.addAttribute("alreadyNameBrand", true);
            return "BrandCar";
        }
        model.addAttribute("brands", brandCarService.getAll());
        model.addAttribute("addedNameBrand", true);
        brandCarService.add(nameBrandCar);
        return "BrandCar";
    }

    @PostMapping("/delete")
    public String deleteBrandCar(@AuthenticationPrincipal UserDetails currentUser,
                                 @RequestParam int idBrandCar, Model model) {
        verificationUser(currentUser, model);
        brandCarService.delete(idBrandCar);
        return "BrandCar";
    }

    @PostMapping("/update")
    public String updateBrandCar(@AuthenticationPrincipal UserDetails currentUser,
                                 @RequestParam String nameBrandCar, @RequestParam int idBrandCar, Model model) {
        verificationUser(currentUser, model);
        String regex = "^([А-Я]{1,23}[а-яё]{1,23}|[A-Z]{1,23}[a-z]{1,23})$";
        if(!ValidRegex.validRegex(regex, nameBrandCar)) {
            model.addAttribute("notUpdateNameBrand", true);
            model.addAttribute("brands", brandCarService.getAll());
            return "BrandCar";
        }
        model.addAttribute("brands", brandCarService.getAll());
        model.addAttribute("updatedNameBrand", true);
        brandCarService.update(nameBrandCar, idBrandCar);
        return "BrandCar";
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
