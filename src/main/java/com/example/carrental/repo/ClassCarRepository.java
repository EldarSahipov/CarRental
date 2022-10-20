package com.example.carrental.repo;

import com.example.carrental.entity.ClassCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClassCarRepository extends JpaRepository<ClassCar, Short> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO dbo.class_car(name) values(?1)", nativeQuery = true)
    void add(String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dbo.class_car WHERE id = ?1", nativeQuery = true)
    void delete(short id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dbo.class_car SET name = ?1 WHERE id = ?2", nativeQuery = true)
    void update(String name, short id);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM dbo.class_car", nativeQuery = true)
    List<ClassCar> getAll();
}
