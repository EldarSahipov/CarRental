package com.example.carrental.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassCar classCar;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandCar brandCar;

    @Column(name = "number_car")
    private String numberCar;

    @Column(name = "model")
    private String modelCar;

    @Column(name = "price")
    private int price;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityCar cityCar;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "body_type_id")
    private BodyType bodyType;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @JsonBackReference
    @OneToMany(mappedBy = "car")
    @ToString.Exclude
    private List<RentalCar> rentalCar;
}
