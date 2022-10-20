package com.example.carrental.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brand_car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrandCar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "brandCar")
    @ToString.Exclude
    private List<Car> cars;
}
