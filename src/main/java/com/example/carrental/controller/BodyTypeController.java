package com.example.carrental.controller;

import com.example.carrental.entity.BodyType;
import com.example.carrental.service.BodyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/body-type")
public class BodyTypeController {
    private final BodyTypeService bodyTypeService;

    @Autowired
    public BodyTypeController(BodyTypeService bodyTypeService) {
        this.bodyTypeService = bodyTypeService;
    }

    @GetMapping()
    public String getBodyType(Model model) {
        model.addAttribute("bodyType", new BodyType());
        model.addAttribute("bodyTypes", bodyTypeService.getAll());
        return "BodyType";
    }

    @PostMapping("/add")
    public String addBodyType(@RequestParam String nameBodyType) {
        bodyTypeService.add(nameBodyType);
        return "redirect:/body-type";
    }

    @PostMapping("/delete")
    public String deleteBodyType(@RequestParam short idBodyType) {
        bodyTypeService.delete(idBodyType);
        return "redirect:/body-type";
    }

    @PostMapping("/update")
    public String updateBodyType(@RequestParam String nameBodyType, @RequestParam short idBodyType) {
        bodyTypeService.update(nameBodyType, idBodyType);
        return "redirect:/body-type";
    }
}
