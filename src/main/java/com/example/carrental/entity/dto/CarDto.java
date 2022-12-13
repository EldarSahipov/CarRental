package com.example.carrental.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CarDto {
    private long idCar;
    private String classCar;
    private String brandCar;
    private String numberCar;
    private int price;
    private String cityCar;
    private  String bodyCar;
    private String transmissionCar;
    private String modelCar;
    private int countRentalCar;
    private int profitCar;
}
