package com.example.carrental.repo;

import com.example.carrental.entity.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TransmissionRepository extends JpaRepository<Transmission, Short> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO dbo.transmission(name) values(?1)", nativeQuery = true)
    void add(String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dbo.transmission WHERE id = ?1", nativeQuery = true)
    void delete(short id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dbo.transmission SET name = ?1 WHERE id = ?2", nativeQuery = true)
    void update(String name, short id);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM dbo.transmission", nativeQuery = true)
    List<Transmission> getAll();
}
