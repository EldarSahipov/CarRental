package com.example.carrental.service;

import com.example.carrental.entity.Car;
import com.example.carrental.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void add(short classId, int brandId, String numberCar, int priceCar, int cityId,
                    short bodyTypeId,
                    short transmissionId, String modelCar) {
        carRepository.add(classId, brandId, numberCar, priceCar, cityId, bodyTypeId, transmissionId, modelCar);
    }

    public void delete(long idCar) {
        carRepository.delete(idCar);
    }

    public void update(short classId, int brandId, String numberCar, int priceCar, int cityId,
                       short bodyTypeId,
                       short transmissionId, long idCar, String modelCar) {
        carRepository.update(classId, brandId, numberCar, priceCar, cityId, bodyTypeId, transmissionId, idCar, modelCar);
    }

    public List<Car> getAll() {
        return carRepository.getAll();
    }

    public List<Car> getFreeCars(Date startLease, Date endLease) {
        return carRepository.getFreeCars(startLease, endLease);
    }

    public Car getCarByIdCar(long idCar) {
        return carRepository.getCarById(idCar);
    }

    public Car getCarByIdRentalCar(long idRentalCar) {
        return carRepository.getCarByIdRentalCar(idRentalCar);
    }

    public List<Car> getCarsByFilterByNameBrand(Date startLease, Date endLease, String nameClass, String nameTransmission,
                                                String nameBrandCar, String nameCity, String nameBodyType) {
        return carRepository.getCarsByFilterByNameBrand(startLease, endLease, nameClass, nameTransmission, nameBrandCar, nameCity, nameBodyType);
    }

    public List<Car> getCarsByFilterByPriceAsc(Date startLease, Date endLease, String nameClass, String nameTransmission,
                                               String nameBrandCar, String nameCity, String nameBodyType) {
        return carRepository.getCarsByFilterByPriceAsc(startLease, endLease, nameClass, nameTransmission, nameBrandCar, nameCity, nameBodyType);
    }

    public List<Car> getCarsByFilterByPriceDesc(Date startLease, Date endLease, String nameClass, String nameTransmission,
                                                String nameBrandCar, String nameCity, String nameBodyType) {
        return carRepository.getCarsByFilterByPriceDesc(startLease, endLease, nameClass, nameTransmission, nameBrandCar, nameCity, nameBodyType);
    }

    public List<Car> getPopularCars() {
        return carRepository.getPopularCars();
    }

    public List<Car> getTopThreePopularCars() {
        return carRepository.getTopThreePopularCars();
    }

    public List<Car> getCountRentalCarsByDate(Date startDate, Date endDate) {
        return carRepository.getCountRentalCarsByDate(startDate, endDate);
    }

    public List<Integer> getCountRentalCars(Date startDate, Date endDate) {
        return carRepository.getCountRentalCars(startDate, endDate);
    }

    public List<Integer> getSumCars(Date startDate, Date endDate) {
        return carRepository.getSumCars(startDate, endDate);
    }

    public List<Car> getRentedCars() {
        return carRepository.getRentedCars();
    }

    public Car getRentedCar(Long idCar) {
        return carRepository.getRentedCar(idCar);
    }
}
