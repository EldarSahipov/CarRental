package com.example.carrental.controller;

import com.example.carrental.controller.valid.ValidRegex;
import com.example.carrental.service.BrandCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public String addBrandCar(@RequestParam String nameBrandCar, Model model) {
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
    public String deleteBrandCar(@RequestParam int idBrandCar) {
        brandCarService.delete(idBrandCar);
        return "redirect:/brand-car";
    }

    @PostMapping("/update")
    public String updateBrandCar(@RequestParam String nameBrandCar, @RequestParam int idBrandCar, Model model) {
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
}
