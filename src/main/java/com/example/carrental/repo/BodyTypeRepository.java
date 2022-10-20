package com.example.carrental.repo;

import com.example.carrental.entity.BodyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BodyTypeRepository extends JpaRepository<BodyType, Short> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO dbo.body_type(name) values(?1)", nativeQuery = true)
    void add(String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dbo.body_type WHERE id = ?1", nativeQuery = true)
    void delete(short id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dbo.body_type SET name = ?1 WHERE id = ?2", nativeQuery = true)
    void update(String name, short id);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM dbo.body_type", nativeQuery = true)
    List<BodyType> getAll();

}
