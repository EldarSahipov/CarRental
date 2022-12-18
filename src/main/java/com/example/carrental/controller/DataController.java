package com.example.carrental.controller;

import com.example.carrental.entity.Car;
import com.example.carrental.repo.CarRepository;
import com.example.carrental.repo.RentalCarRepository;
import com.example.carrental.service.CarDtoService;
import com.example.carrental.service.CarService;
import com.example.carrental.service.RentalCarService;
import com.example.carrental.service.UserService;
import com.example.carrental.springsecurity.model.Role;
import com.example.carrental.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/data")
public class DataController {
    private final RentalCarService rentalCarService;
    private final CarService carService;
    private final RentalCarRepository rentalCarRepository;
    private final CarRepository carRepository;
    private final CarDtoService carDtoService;
    private final UserService userService;

    @Autowired
    public DataController(RentalCarService rentalCarService, CarService carService, RentalCarRepository rentalCarRepository, CarRepository carRepository, CarDtoService carDtoService, UserService userService) {
        this.rentalCarService = rentalCarService;
        this.carService = carService;
        this.rentalCarRepository = rentalCarRepository;
        this.carRepository = carRepository;
        this.carDtoService = carDtoService;
        this.userService = userService;
    }

    @GetMapping()
    public String getDataPage(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        verificationUser(currentUser, model);
        model.addAttribute("rentalCars", rentalCarService.getAll());
        List<Car> cars = carService.getTopThreePopularCars();
        model.addAttribute("popularTopThreeCars", cars);
        return "Data";
    }

    @GetMapping("/filter")
    public String getDateByFilterPage(@AuthenticationPrincipal UserDetails currentUser, Model model, @RequestParam Date startDate, @RequestParam Date endDate) {
        verificationUser(currentUser, model);
        model.addAttribute("rentalCars", rentalCarService.getAll());
        model.addAttribute("popularTopThreeCars", carService.getTopThreePopularCars());
        model.addAttribute("popularCars", carService.getPopularCars());
        model.addAttribute("countRental", carService.getCountRentalCars(startDate, endDate));
        model.addAttribute("sum", carService.getSumCars(startDate, endDate));
        model.addAttribute("countRentalByDate", carDtoService
                .getInfoRentalCarList(carDtoService
                        .getCarDtoFromCar(carService
                                .getRentedCars()), startDate, endDate));
        model.addAttribute("profit", rentalCarService.getProfitForThePeriod(startDate, endDate));
        return "Data";
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
