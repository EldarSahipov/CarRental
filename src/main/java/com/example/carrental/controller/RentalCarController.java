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
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/car/rental-car")
public class RentalCarController {
    private final RentalCarService rentalCarService;
    private final TenantService tenantService;
    private final CarService carService;
    private static final Logger LOGGER = Logger.getLogger(RentalCarController.class.getName());
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
        LOGGER.log(Level.INFO, "Добавлена аренда автомобиля - " + car);
        return "redirect:/car";
    }

    @PostMapping("/delete")
    public String deleteRentalCar(@RequestParam long idRentalCar) {
        rentalCarService.delete(idRentalCar);
        LOGGER.log(Level.INFO, "Удаление аренды автомобиля - " + idRentalCar);
        return "redirect:/car";
    }

    @PostMapping("/update")
    public String updateRentalCar(@RequestParam long idRentalCar,
                                  @RequestParam Date endLease,
                                  @RequestParam Date startLease) {
        rentalCarService.update(idRentalCar, endLease, startLease, carService.getCarByIdRentalCar(idRentalCar).getPrice());
        LOGGER.log(Level.INFO, "Обновлены данные об аренде (" + idRentalCar );
        return "redirect:/car";
    }


}
