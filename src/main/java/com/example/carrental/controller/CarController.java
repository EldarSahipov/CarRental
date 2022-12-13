package com.example.carrental.controller;

import com.example.carrental.entity.Car;
import com.example.carrental.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/car")
@AllArgsConstructor
public class CarController {
    private final CarService carService;
    private final ClassCarService classCarService;
    private final BrandCarService brandCarService;
    private final CityCarService cityCarService;
    private final BodyTypeService bodyTypeService;
    private final TransmissionService transmissionService;
    private final RentalCarService rentalCarService;
    private final TenantService tenantService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping()
    public String getCarPage(Model model) {
        List<Car> historyCars = new ArrayList<>();
        model.addAttribute("cars", carService.getAll());
        model.addAttribute("classes", classCarService.getAll());
        model.addAttribute("brands", brandCarService.getAll());
        model.addAttribute("cities", cityCarService.getAll());
        model.addAttribute("bodyTypes", bodyTypeService.getAll());
        model.addAttribute("transmissions", transmissionService.getAll());
        return "Car";
    }

    @GetMapping("/rental-car/filter-values")
    public String getCarsByFilter(@RequestParam Date startLease, @RequestParam Date endLease,
                                  @RequestParam String nameClass, @RequestParam String nameTransmission,
                                  @RequestParam String nameBrandCar, @RequestParam String nameCity,
                                  @RequestParam String nameBodyType, @RequestParam String typeSort, Model model) {
        if (typeSort.equals("brandCarName")) {
            model.addAttribute("cars", carService.getCarsByFilterByNameBrand(startLease, endLease, nameClass, nameTransmission, nameBrandCar, nameCity, nameBodyType));
        }
        if (typeSort.equals("priceAsc")) {
            model.addAttribute("cars", carService.getCarsByFilterByPriceAsc(startLease, endLease, nameClass, nameTransmission, nameBrandCar, nameCity, nameBodyType));
        }
        if (typeSort.equals("priceDesc")) {
            model.addAttribute("cars", carService.getCarsByFilterByPriceDesc(startLease, endLease, nameClass, nameTransmission, nameBrandCar, nameCity, nameBodyType));
        }
        return getResources(startLease, endLease, model);
    }

    private String getResources(@RequestParam Date startLease, @RequestParam Date endLease, Model model) {
        model.addAttribute("rentalCars", rentalCarService.getAll());
        model.addAttribute("tenants", tenantService.getAll());
        model.addAttribute("startLease", dateFormat.format(startLease));
        model.addAttribute("endLease", dateFormat.format(endLease));
        model.addAttribute("classes", classCarService.getAll());
        model.addAttribute("brands", brandCarService.getAll());
        model.addAttribute("cities", cityCarService.getAll());
        model.addAttribute("bodies", bodyTypeService.getAll());
        model.addAttribute("transmissions", transmissionService.getAll());
        return "RentalCar";
    }

    @PostMapping("/add")
    public String addCar(@RequestParam short classId, @RequestParam int brandId, @RequestParam String numberCar,
                         @RequestParam int priceCar, @RequestParam int cityId, @RequestParam short bodyTypeId,
                         @RequestParam short transmissionId, @RequestParam String modelCar) {
        carService.add(classId, brandId, numberCar, priceCar, cityId, bodyTypeId, transmissionId, modelCar);
        return "redirect:/car";
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam long idCar) {
        carService.delete(idCar);
        return "redirect:/car";
    }

    @PostMapping("/update")
    public String updateCar(@RequestParam short classId, @RequestParam int brandId, @RequestParam String numberCar,
                            @RequestParam int priceCar, @RequestParam int cityId, @RequestParam short bodyTypeId,
                            @RequestParam short transmissionId, @RequestParam long idCar, @RequestParam String modelCar) {
        carService.update(classId, brandId, numberCar, priceCar, cityId, bodyTypeId, transmissionId, idCar, modelCar);
        return "redirect:/car";
    }

    @GetMapping("/rental-car")
    public String getFormRentingCarPage(@RequestParam(required = false) Date startLease, @RequestParam(required = false) Date endLease, Model model) {
        if(startLease == null || endLease == null) {
           return  "redirect:/car";
        }
        Date date = new Date();
        date.setHours(startLease.getHours());
        date.setMinutes(startLease.getMinutes());
        date.setSeconds(startLease.getSeconds());
        if(endLease.before(startLease) || startLease.before(date) || startLease.getDay() == date.getDay()) {
            return "redirect:/car";
        } else {
            model.addAttribute("cars", carService.getFreeCars(startLease, endLease));
            return getResources(startLease, endLease, model);
        }
    }
}
