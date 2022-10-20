package com.example.carrental.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "rental_car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RentalCar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "tenant_phone")
    private Tenant tenant;

    @Column(name = "end_lease")
    private Date endLease;

    @Column(name = "start_lease")
    private Date startLease;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "price")
    private int price;
}
