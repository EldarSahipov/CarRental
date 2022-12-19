package com.example.carrental.repo;

import com.example.carrental.entity.BodyType;
import com.example.carrental.entity.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SupportRepository extends JpaRepository<Support, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO dbo.support(email_from, text) values(?1, ?2)", nativeQuery = true)
    void add(String emailFrom, String text);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dbo.support WHERE id = ?1", nativeQuery = true)
    void delete(Integer id);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM dbo.support", nativeQuery = true)
    List<Support> getAll();
}
