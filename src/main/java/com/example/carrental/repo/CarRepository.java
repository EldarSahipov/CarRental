package com.example.carrental.repo;

import com.example.carrental.entity.Car;
import com.example.carrental.entity.dto.PopularAuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO dbo.car(class_id, brand_id, number_car, price, city_id," +
            "body_type_id," +
            "transmission_id, model) values(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    void add(short classId, int brandId, String numberCar, int priceCar, int cityId,
             short bodyTypeId,
             short transmissionId, String model);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dbo.car WHERE id = ?1", nativeQuery = true)
    void delete(long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dbo.car SET class_id = ?1, brand_id = ?2, number_car = ?3, price = ?4," +
            "city_id = ?5, body_type_id = ?6, transmission_id = ?7, model = ?9" +
            " WHERE id = ?8", nativeQuery = true)
    void update(short classId, int brandId, String numberCar, int priceCar, int cityId,
                short bodyTypeId,
                short transmissionId, long idCar, String modelCar);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM dbo.car", nativeQuery = true)
    List<Car> getAll();

    @Transactional
    @Query(value = """
            SELECT DISTINCT dbo.car.id, dbo.car.class_id, dbo.car.brand_id, dbo.car.number_car, dbo.car.price, dbo.car.city_id, dbo.car.body_type_id, dbo.car.transmission_id, dbo.car.model
            FROM dbo.car
             LEFT OUTER JOIN dbo.rental_car ON dbo.car.id = dbo.rental_car.car_id
                WHERE (dbo.rental_car.car_id IS NULL)
                    OR ((?1 > dbo.rental_car.end_lease) OR (?2 < dbo.rental_car.start_lease)
                            AND ?1 > CONVERT(date, GETDATE()))""", nativeQuery = true)
    List<Car> getFreeCars(Date startLease, Date endLease);

    @Transactional
    @Query(value = """
            SELECT dbo.car.id, dbo.car.class_id, dbo.car.brand_id, dbo.car.number_car, dbo.car.price, dbo.car.city_id, dbo.car.body_type_id, dbo.car.transmission_id, dbo.car.model
            FROM dbo.car LEFT OUTER JOIN
            dbo.rental_car ON dbo.car.id = dbo.rental_car.car_id
            WHERE dbo.rental_car.id = ?1""", nativeQuery = true)
    Car getCarByIdRentalCar(long idRentalCar);

    @Transactional
    @Query(value = "SELECT * FROM dbo.car WHERE id = ?1", nativeQuery = true)
    Car getCarById(long idCar);

    @Query(value = """
                SELECT DISTINCT dbo.car.id, dbo.car.class_id, dbo.car.brand_id, dbo.car.number_car,
                dbo.car.price, dbo.car.city_id, dbo.car.body_type_id, dbo.car.transmission_id, dbo.car.model, dbo.class_car.name
                FROM dbo.car
                LEFT OUTER JOIN dbo.rental_car ON dbo.car.id = dbo.rental_car.car_id
                                JOIN dbo.class_car ON dbo.car.class_id = dbo.class_car.id
                                    JOIN dbo.transmission ON dbo.car.transmission_id = dbo.transmission.id
                                        JOIN dbo.brand_car ON dbo.car.brand_id = dbo.brand_car.id
                                            JOIN dbo.city_car ON dbo.car.city_id = dbo.city_car.id
                                                JOIN dbo.body_type ON dbo.car.body_type_id = dbo.body_type.id
                WHERE  ((dbo.rental_car.car_id IS NULL)
                OR ((?1 > dbo.rental_car.end_lease) OR (?2 < dbo.rental_car.start_lease)
                  AND ?1 > CONVERT(date, GETDATE())))
                          AND dbo.class_car.name LIKE '%' + ?3
                              AND dbo.transmission.name LIKE '%' + ?4
                                  AND dbo.brand_car.name LIKE '%' + ?5
                                      AND dbo.city_car.name LIKE '%' + ?6
                                          AND dbo.body_type.name LIKE '%' + ?7
                                          """, nativeQuery = true)
    List<Car> getCarsByFilterByNameBrand(Date startLease,
                                         Date endLease,
                                         String nameClass,
                                         String nameTransmission,
                                         String nameBrandCar,
                                         String nameCity,
                                         String nameBodyType);

    @Query(value = """
                SELECT DISTINCT dbo.car.id, dbo.car.class_id, dbo.car.brand_id, dbo.car.number_car,
                dbo.car.price, dbo.car.city_id, dbo.car.body_type_id, dbo.car.transmission_id, dbo.car.model, dbo.class_car.name
                FROM dbo.car
                LEFT OUTER JOIN dbo.rental_car ON dbo.car.id = dbo.rental_car.car_id
                                JOIN dbo.class_car ON dbo.car.class_id = dbo.class_car.id
                                    JOIN dbo.transmission ON dbo.car.transmission_id = dbo.transmission.id
                                        JOIN dbo.brand_car ON dbo.car.brand_id = dbo.brand_car.id
                                            JOIN dbo.city_car ON dbo.car.city_id = dbo.city_car.id
                                                JOIN dbo.body_type ON dbo.car.body_type_id = dbo.body_type.id
                WHERE  ((dbo.rental_car.car_id IS NULL)
                OR ((?1 > dbo.rental_car.end_lease) OR (?2 < dbo.rental_car.start_lease)
                  AND ?1 > CONVERT(date, GETDATE())))
                          AND dbo.class_car.name LIKE '%' + ?3
                              AND dbo.transmission.name LIKE '%' + ?4
                                  AND dbo.brand_car.name LIKE '%' + ?5
                                      AND dbo.city_car.name LIKE '%' + ?6
                                          AND dbo.body_type.name LIKE '%' + ?7
                                          ORDER BY price""", nativeQuery = true)
    List<Car> getCarsByFilterByPriceAsc(Date startLease,
                                         Date endLease,
                                         String nameClass,
                                         String nameTransmission,
                                         String nameBrandCar,
                                         String nameCity,
                                         String nameBodyType);

    @Query(value = """
                SELECT DISTINCT dbo.car.id, dbo.car.class_id, dbo.car.brand_id, dbo.car.number_car,
                dbo.car.price, dbo.car.city_id, dbo.car.body_type_id, dbo.car.transmission_id, dbo.car.model, dbo.class_car.name
                FROM dbo.car
                LEFT OUTER JOIN dbo.rental_car ON dbo.car.id = dbo.rental_car.car_id
                                JOIN dbo.class_car ON dbo.car.class_id = dbo.class_car.id
                                    JOIN dbo.transmission ON dbo.car.transmission_id = dbo.transmission.id
                                        JOIN dbo.brand_car ON dbo.car.brand_id = dbo.brand_car.id
                                            JOIN dbo.city_car ON dbo.car.city_id = dbo.city_car.id
                                                JOIN dbo.body_type ON dbo.car.body_type_id = dbo.body_type.id
                WHERE  ((dbo.rental_car.car_id IS NULL)
                OR ((?1 >= dbo.rental_car.end_lease) OR (?2 <= dbo.rental_car.start_lease)
                  AND ?1 > CONVERT(date, GETDATE())))
                          AND dbo.class_car.name LIKE '%' + ?3
                              AND dbo.transmission.name LIKE '%' + ?4
                                  AND dbo.brand_car.name LIKE '%' + ?5
                                      AND dbo.city_car.name LIKE '%' + ?6
                                          AND dbo.body_type.name LIKE '%' + ?7
                                          ORDER BY price DESC""", nativeQuery = true)
    List<Car> getCarsByFilterByPriceDesc(Date startLease,
                                        Date endLease,
                                        String nameClass,
                                        String nameTransmission,
                                        String nameBrandCar,
                                        String nameCity,
                                        String nameBodyType);

    @Query(value = """
            SELECT TOP(3) car_id, class_id, brand_id, number_car, c.price, city_id, body_type_id, transmission_id, model, COUNT(*) as 'CountRental' FROM rental_car
            join car c on c.id = rental_car.car_id
            group by car_id, class_id, brand_id, number_car, c.price, city_id, body_type_id, transmission_id, model
            order by CountRental desc""", nativeQuery = true)
    List<Car> getPopularCars();
}
