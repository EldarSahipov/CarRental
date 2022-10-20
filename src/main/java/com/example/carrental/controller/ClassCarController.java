package com.example.carrental.controller;

import com.example.carrental.service.ClassCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("class-car")
public class ClassCarController {
    private final ClassCarService classCarService;

    @Autowired
    public ClassCarController(ClassCarService classCarService) {
        this.classCarService = classCarService;
    }

    @GetMapping()
    public String getClassCar(Model model) {
        model.addAttribute("classes", classCarService.getAll());
        return "ClassCar";
    }

    @PostMapping("/add")
    public String addClassCar(@RequestParam String nameClassCar) {
        classCarService.add(nameClassCar);
        return "redirect:/class-car";
    }

    @PostMapping("/delete")
    public String deleteClassCar(@RequestParam short idClassCar) {
        classCarService.delete(idClassCar);
        return "redirect:/class-car";
    }

    @PostMapping("/update")
    public String updateClassCar(@RequestParam String nameClassCar, @RequestParam short idClassCar) {
        classCarService.update(nameClassCar, idClassCar);
        return "redirect:/class-car";
    }
}

