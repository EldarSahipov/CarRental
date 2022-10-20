package com.example.carrental.repo;

import com.example.carrental.entity.BrandCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BrandCarRepository extends JpaRepository<BrandCar, Integer> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO dbo.brand_car(name) values(?1)", nativeQuery = true)
    void add(String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dbo.brand_car WHERE id = ?1", nativeQuery = true)
    void delete(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dbo.brand_car SET name = ?1 WHERE id = ?2", nativeQuery = true)
    void update(String name, int id);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM dbo.brand_car", nativeQuery = true)
    List<BrandCar> getAll();
}
