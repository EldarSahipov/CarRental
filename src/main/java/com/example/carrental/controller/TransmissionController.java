package com.example.carrental.controller;

import com.example.carrental.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("transmission")
public class TransmissionController {
    private final TransmissionService transmissionService;

    @Autowired
    public TransmissionController(TransmissionService transmissionService) {
        this.transmissionService = transmissionService;
    }

    @GetMapping()
    public String transmissionPage(Model model) {
        model.addAttribute("transmissions", transmissionService.getAll());
        return "Transmission";
    }

    @PostMapping("/add")
    public String addTransmission(@RequestParam String nameTransmission) {
        transmissionService.add(nameTransmission);
        return "redirect:/transmission";
    }

    @PostMapping("/delete")
    public String deleteClassCar(@RequestParam short idTransmission) {
        transmissionService.delete(idTransmission);
        return "redirect:/transmission";
    }

    @PostMapping("/update")
    public String updateTransmission(@RequestParam String nameTransmission, @RequestParam short idTransmission) {
        transmissionService.update(nameTransmission, idTransmission);
        return "redirect:/transmission";
    }
}
