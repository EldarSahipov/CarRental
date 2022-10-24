package com.example.carrental.controller;

import com.example.carrental.entity.dto.PopularAuto;
import com.example.carrental.service.CarService;
import com.example.carrental.service.RentalCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/data")
public class DataController {
    private final RentalCarService rentalCarService;
    private final CarService carService;

    @Autowired
    public DataController(RentalCarService rentalCarService, CarService carService) {
        this.rentalCarService = rentalCarService;
        this.carService = carService;
    }

    @GetMapping()
    public String getDataPage(Model model) {
        model.addAttribute("rentalCars", rentalCarService.getAll());
        model.addAttribute("popularCars", carService.getPopularCars());
        return "Data";
    }
}
