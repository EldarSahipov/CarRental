package com.example.carrental.controller;

import com.example.carrental.service.BrandCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("brand-car")
public class BrandCarController {
    private final BrandCarService brandCarService;

    @Autowired
    public BrandCarController(BrandCarService bodyTypeService) {
        this.brandCarService = bodyTypeService;
    }

    @GetMapping()
    public String getBrandCar(Model model) {
        model.addAttribute("brands", brandCarService.getAll());
        return "BrandCar";
    }

    @PostMapping("/add")
    public String addBrandCar(@RequestParam String nameBrandCar) {
        brandCarService.add(nameBrandCar);
        return "redirect:/brand-car";
    }

    @PostMapping("/delete")
    public String deleteBrandCar(@RequestParam int idBrandCar) {
        brandCarService.delete(idBrandCar);
        return "redirect:/brand-car";
    }

    @PostMapping("/update")
    public String updateBrandCar(@RequestParam String nameBrandCar, @RequestParam int idBrandCar) {
        brandCarService.update(nameBrandCar, idBrandCar);
        return "redirect:/brand-car";
    }
}
