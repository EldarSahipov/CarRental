package com.example.carrental.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "class_car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassCar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private short id;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "classCar")
    private List<Car> cars;
}
