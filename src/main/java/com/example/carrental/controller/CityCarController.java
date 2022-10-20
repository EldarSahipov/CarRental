package com.example.carrental.controller;

import com.example.carrental.service.CityCarService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CityCarController(CityCarService cityCarService) {
        this.cityCarService = cityCarService;
    }

    @GetMapping()
    public String getCityCar(Model model) {
        model.addAttribute("cities", cityCarService.getAll());
        return "CityCar";
    }

    @PostMapping("/add")
    public String addCityCar(@RequestParam String nameCityCar) {
        cityCarService.add(nameCityCar);
        return "redirect:/city-car";
    }

    @PostMapping("/delete")
    public String deleteCityCar(@RequestParam int idCityCar) {
        cityCarService.delete(idCityCar);
        return "redirect:/city-car";
    }

    @PostMapping("/update")
    public String updateCityCar(@RequestParam String nameCityCar, @RequestParam int idCityCar) {
        cityCarService.update(nameCityCar, idCityCar);
        return "redirect:/city-car";
    }
}
