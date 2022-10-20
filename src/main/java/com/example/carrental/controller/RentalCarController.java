package com.example.carrental.controller;

import com.example.carrental.entity.Car;
import com.example.carrental.service.CarService;
import com.example.carrental.service.RentalCarService;
import com.example.carrental.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/car/rental-car")
public class RentalCarController {
    private final RentalCarService rentalCarService;
    private final TenantService tenantService;
    private final CarService carService;

    @Autowired
    public RentalCarController(RentalCarService rentalCarService, TenantService tenantService, CarService carService) {
        this.rentalCarService = rentalCarService;
        this.tenantService = tenantService;
        this.carService = carService;
    }

    @PostMapping("/add")
    public String addRentalCar(@RequestParam String phoneNumber,
                               @RequestParam Date endLease,
                               @RequestParam Date startLease,
                               @RequestParam long idCar) {
        Car car = carService.getCarByIdCar(idCar);
        rentalCarService.add(phoneNumber, endLease, startLease, idCar, car.getPrice());
        return "redirect:/car";
    }

    @PostMapping("/delete")
    public String deleteRentalCar(@RequestParam long idRentalCar) {
        rentalCarService.delete(idRentalCar);
        return "redirect:/car";
    }

    @PostMapping("/update")
    public String updateRentalCar(@RequestParam long idRentalCar,
                                  @RequestParam Date endLease,
                                  @RequestParam Date startLease) {
        rentalCarService.update(idRentalCar, endLease, startLease, carService.getCarByIdRentalCar(idRentalCar).getPrice());
        return "redirect:/car";
    }


}
