package com.example.carrental.service;

import com.example.carrental.entity.Car;
import com.example.carrental.entity.CarDto;
import com.example.carrental.repo.RentalCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CarDtoService {
    private final RentalCarRepository rentalCarRepository;

    @Autowired
    public CarDtoService(RentalCarRepository rentalCarRepository) {
        this.rentalCarRepository = rentalCarRepository;
    }

    public static CarDto mapToCarDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setIdCar(car.getId());
        carDto.setClassCar(car.getClassCar().getName());
        carDto.setBrandCar(car.getBrandCar().getName());
        carDto.setNumberCar(car.getNumberCar());
        carDto.setPrice(car.getPrice());
        carDto.setCityCar(car.getCityCar().getName());
        carDto.setBodyCar(car.getBodyType().getName());
        carDto.setTransmissionCar(car.getTransmission().getName());
        carDto.setModelCar(car.getModelCar());
        return carDto;
    }

    private int getCountRental(long idCar, Date startLease, Date endLease) {
        return rentalCarRepository.getCountRentalByCar(idCar, startLease, endLease);
    }

    private int getProfitRental(long idCar, Date startLease, Date endLease) {
        return rentalCarRepository.getProfitRentalCar(idCar, startLease, endLease);
    }

    public List<CarDto> getCarDtoFromCar(List<Car> carList) {
        return carList.stream()
                .map(CarDtoService::mapToCarDto)
                .toList();
    }

    public List<CarDto> getInfoRentalCarList(List<CarDto> carDtoList, Date startLease, Date endLease) {
        if (!carDtoList.isEmpty()) {
            for (CarDto carDto : carDtoList) {
                try {
                    carDto.setCountRentalCar(getCountRental(carDto.getIdCar(), startLease, endLease));
                    carDto.setProfitCar(getProfitRental(carDto.getIdCar(), startLease, endLease));
                } catch (Exception e) {
                    return null;
                }
            }
            return carDtoList;
        }
        return carDtoList;
    }
}
