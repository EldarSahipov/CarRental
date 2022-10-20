package com.example.carrental.repo;

import com.example.carrental.entity.Car;
import com.example.carrental.entity.RentalCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface RentalCarRepository extends JpaRepository<RentalCar, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO dbo.rental_car(tenant_phone, end_lease, start_lease, car_id, price) values(?1, ?2, ?3, ?4, ?5*datediff(day, ?3, ?2))", nativeQuery = true)
    void add(String phoneNumber, Date endLease, Date startLease, long carId, int price);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dbo.rental_car WHERE id = ?1", nativeQuery = true)
    void delete(long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dbo.rental_car SET end_lease = ?2, start_lease = ?3, price = ?4*datediff(day, ?3, ?2) WHERE id = ?1", nativeQuery = true)
    void update(long idRentalCar, Date endLease, Date startLease, int price);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM dbo.rental_car ORDER BY start_lease, end_lease", nativeQuery = true)
    List<RentalCar> getAll();
}
