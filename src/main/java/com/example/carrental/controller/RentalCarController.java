package com.example.carrental.controller;

import com.example.carrental.entity.Car;
import com.example.carrental.service.CarService;
import com.example.carrental.service.RentalCarService;
import com.example.carrental.service.TenantService;
import com.example.carrental.service.UserService;
import com.example.carrental.springsecurity.model.Role;
import com.example.carrental.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final UserService userService;
    private static final Logger LOGGER = Logger.getLogger(RentalCarController.class.getName());
    @Autowired
    public RentalCarController(RentalCarService rentalCarService, TenantService tenantService, CarService carService, UserService userService) {
        this.rentalCarService = rentalCarService;
        this.tenantService = tenantService;
        this.carService = carService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public String addRentalCar(@AuthenticationPrincipal UserDetails currentUser,
                               @RequestParam String phoneNumber,
                               @RequestParam Date endLease,
                               @RequestParam Date startLease,
                               @RequestParam long idCar, Model model) {
        verificationUser(currentUser, model);
        Car car = carService.getCarByIdCar(idCar);
        rentalCarService.add(phoneNumber, endLease, startLease, idCar, car.getPrice());
        LOGGER.log(Level.INFO, "Добавлена аренда автомобиля - " + car);
        return "Car";
    }

    @PostMapping("/delete")
    public String deleteRentalCar(@AuthenticationPrincipal UserDetails currentUser,
                                  @RequestParam long idRentalCar, Model model) {
        verificationUser(currentUser, model);
        rentalCarService.delete(idRentalCar);
        LOGGER.log(Level.INFO, "Удаление аренды автомобиля - " + idRentalCar);
        return "Car";
    }

    @PostMapping("/update")
    public String updateRentalCar(@AuthenticationPrincipal UserDetails currentUser,
                                  @RequestParam long idRentalCar,
                                  @RequestParam Date endLease,
                                  @RequestParam Date startLease, Model model) {
        verificationUser(currentUser, model);
        rentalCarService.update(idRentalCar, endLease, startLease, carService.getCarByIdRentalCar(idRentalCar).getPrice());
        LOGGER.log(Level.INFO, "Обновлены данные об аренде (" + idRentalCar );
        return "Car";
    }

    private void verificationUser(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findUserByEmail(currentUser.getUsername()).orElse(null);
        if (user != null && user.getRole() == Role.ADMIN) {
            model.addAttribute("user", "ADMIN");
        } else {
            model.addAttribute("user", "USER");
        }
    }

}
