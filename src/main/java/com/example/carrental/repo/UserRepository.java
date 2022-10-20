package com.example.carrental.repo;

import com.example.carrental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO dbo.users(email, password) values(?1, ?2)", nativeQuery = true)
    void add(String email, String password);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dbo.users WHERE email = ?1", nativeQuery = true)
    void delete(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dbo.users SET email = ?1, password = ?2 WHERE id = ?3", nativeQuery = true)
    void update(String email, String password, long id);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM dbo.users", nativeQuery = true)
    List<User> getAll();
}
