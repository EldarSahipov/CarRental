package com.example.carrental.repo;

import com.example.carrental.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO dbo.tenant(phone_number, first_name, last_name, year_driving) values(?1, ?2, ?3, ?4)", nativeQuery = true)
    void add(String phoneNumber, String first_name, String last_name, short yearDriving);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dbo.tenant WHERE phone_number = ?1", nativeQuery = true)
    void delete(String phoneNumber);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dbo.tenant SET first_name = ?1, last_name = ?2, year_driving = ?3 WHERE phone_number = ?4", nativeQuery = true)
    void update(String firstName, String lastName, short yearDriving, String phoneNumber);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM dbo.tenant", nativeQuery = true)
    List<Tenant> getAll();

    @Query(value = """
            select * from tenant
            where phone_number = ?1""", nativeQuery = true)
    Tenant getTenantByPhoneNumber(String phoneNumber);
}
