package com.example.carrental.controller;

import com.example.carrental.entity.Car;
import com.example.carrental.repo.CarRepository;
import com.example.carrental.repo.RentalCarRepository;
import com.example.carrental.service.CarDtoService;
import com.example.carrental.service.CarService;
import com.example.carrental.service.RentalCarService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public DataController(RentalCarService rentalCarService, CarService carService, RentalCarRepository rentalCarRepository, CarRepository carRepository, CarDtoService carDtoService) {
        this.rentalCarService = rentalCarService;
        this.carService = carService;
        this.rentalCarRepository = rentalCarRepository;
        this.carRepository = carRepository;
        this.carDtoService = carDtoService;
    }

    @GetMapping()
    public String getDataPage(Model model) {
        model.addAttribute("rentalCars", rentalCarService.getAll());
        List<Car> cars = carService.getTopThreePopularCars();
        model.addAttribute("popularTopThreeCars", cars);

        return "Data";
    }

    @GetMapping("/filter")
    public String getDateByFilterPage(Model model, @RequestParam Date startDate, @RequestParam Date endDate) {
        model.addAttribute("rentalCars", rentalCarService.getAll());
        model.addAttribute("popularTopThreeCars", carService.getTopThreePopularCars());
        model.addAttribute("popularCars", carService.getPopularCars());
        model.addAttribute("countRental", carService.getCountRentalCars(startDate, endDate));
        model.addAttribute("sum", carService.getSumCars(startDate, endDate));
        model.addAttribute("countRentalByDate", carDtoService
                .getInfoRentalCarList(carDtoService
                        .getCarDtoFromCar(carService
                                .getRentedCars()), startDate, endDate));
        return "Data";
    }
}
