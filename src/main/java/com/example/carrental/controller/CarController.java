package com.example.carrental.controller;

import com.example.carrental.entity.Car;
import com.example.carrental.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Validated
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
    private static final Logger LOGGER = Logger.getLogger(CarController.class.getName());

    @GetMapping()
    public String getCarPage(Model model) {
        LOGGER.log(Level.INFO, "Запрос страницы /car");
        return getResourcesForm(model);
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
    public String addCar(@RequestParam short classId, @RequestParam int brandId, @RequestParam(required = false) String numberCar,
                         @RequestParam(required = false) Integer priceCar, @RequestParam int cityId, @RequestParam short bodyTypeId,
                         @RequestParam short transmissionId, @RequestParam(required = false) String modelCar, Model model) {
        try {
            String regex = "^(([АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{1,2})(\\d{2,3})|(\\d{4}(?<!0000)[АВЕКМНОРСТУХ]{2})(\\d{2})|(\\d{3}(?<!000)(C?D|[ТНМВКЕ])\\d{3}(?<!000))(\\d{2}(?<!00))|([ТСК][АВЕКМНОРСТУХ]{2}\\d{3}(?<!000))(\\d{2})|([АВЕКМНОРСТУХ]{2}\\d{3}(?<!000)[АВЕКМНОРСТУХ])(\\d{2})|([АВЕКМНОРСТУХ]\\d{4}(?<!0000))(\\d{2})|(\\d{3}(?<!000)[АВЕКМНОРСТУХ])(\\d{2})|(\\d{4}(?<!0000)[АВЕКМНОРСТУХ])(\\d{2})|([АВЕКМНОРСТУХ]{2}\\d{4}(?<!0000))(\\d{2})|([АВЕКМНОРСТУХ]{2}\\d{3}(?<!000))(\\d{2,3})|(^Т[АВЕКМНОРСТУХ]{2}\\d{3}(?<!000)\\d{2,3}))";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(numberCar);
            if (matcher.matches() && !(modelCar == null) && !(priceCar == null) ) {
                carService.add(classId, brandId, numberCar, priceCar, cityId, bodyTypeId, transmissionId, modelCar);
                LOGGER.log(Level.INFO, "Добавлен автомобиль " + classId + " " + numberCar);
                model.addAttribute("addedCar", true);
            } else {
                model.addAttribute("notAddedCar", true);
            }
            return getResourcesForm(model);
        } catch (Exception exception) {
            return "error";
        }

    }

    private String getResourcesForm(Model model) {
        model.addAttribute("cars", carService.getAll());
        model.addAttribute("classes", classCarService.getAll());
        model.addAttribute("brands", brandCarService.getAll());
        model.addAttribute("cities", cityCarService.getAll());
        model.addAttribute("bodyTypes", bodyTypeService.getAll());
        model.addAttribute("transmissions", transmissionService.getAll());
        return "Car";
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam long idCar) {
        carService.delete(idCar);
        LOGGER.log(Level.INFO, "Удаление автомобиля " + idCar);
        return "redirect:/car";
    }

    @PostMapping("/update")
    public String updateCar(@RequestParam short classId, @RequestParam int brandId, @RequestParam(required = false) String numberCar,
                            @RequestParam(required = false) Integer priceCar, @RequestParam int cityId, @RequestParam short bodyTypeId,
                            @RequestParam short transmissionId, @RequestParam long idCar, @RequestParam(required = false) String modelCar, Model model) {
        String regex = "^(([АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{1,2})(\\d{2,3})|(\\d{4}(?<!0000)[АВЕКМНОРСТУХ]{2})(\\d{2})|(\\d{3}(?<!000)(C?D|[ТНМВКЕ])\\d{3}(?<!000))(\\d{2}(?<!00))|([ТСК][АВЕКМНОРСТУХ]{2}\\d{3}(?<!000))(\\d{2})|([АВЕКМНОРСТУХ]{2}\\d{3}(?<!000)[АВЕКМНОРСТУХ])(\\d{2})|([АВЕКМНОРСТУХ]\\d{4}(?<!0000))(\\d{2})|(\\d{3}(?<!000)[АВЕКМНОРСТУХ])(\\d{2})|(\\d{4}(?<!0000)[АВЕКМНОРСТУХ])(\\d{2})|([АВЕКМНОРСТУХ]{2}\\d{4}(?<!0000))(\\d{2})|([АВЕКМНОРСТУХ]{2}\\d{3}(?<!000))(\\d{2,3})|(^Т[АВЕКМНОРСТУХ]{2}\\d{3}(?<!000)\\d{2,3}))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numberCar);
        if (matcher.matches() && !(modelCar == null) && !(priceCar == null)) {
            carService.update(classId, brandId, numberCar, priceCar, cityId, bodyTypeId, transmissionId, idCar, modelCar);
            LOGGER.log(Level.INFO, "Обновление данных об автомобилее " + idCar, numberCar);
            model.addAttribute("updatedCar", true);
        } else {
            model.addAttribute("notUpdatedCar", true);
        }
        return getResourcesForm(model);
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
        if(endLease.getYear() > date.getYear() + 1)
            return "redirect:/car";
        if(endLease.before(startLease) || startLease.before(date) || startLease.getDay() == date.getDay()) {
            return "redirect:/car";
        } else {
            model.addAttribute("cars", carService.getFreeCars(startLease, endLease));
            return getResources(startLease, endLease, model);
        }
    }
}
