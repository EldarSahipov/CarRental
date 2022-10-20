package com.example.carrental.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "body_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private short id;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "bodyType")
    @ToString.Exclude
    private List<Car> cars;
}
