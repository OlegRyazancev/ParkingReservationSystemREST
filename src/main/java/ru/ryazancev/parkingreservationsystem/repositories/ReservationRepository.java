package ru.ryazancev.parkingreservationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ryazancev.parkingreservationsystem.models.reservation.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = """
            SELECT * FROM reservations
            WHERE user_id = :userId
            """, nativeQuery = true)
    List<Reservation> findAllByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = """
            DELETE
            FROM reservations r
            WHERE r.time_to < :currentTime
            """, nativeQuery = true)
    void deleteExpiredReservations(@Param("currentTime") LocalDateTime currentTime);

    @Query(value = """
            SELECT r.id, r.time_from, r.time_to
            FROM reservations r
                     LEFT JOIN cars c ON r.car_id = c.id
            WHERE c.id = :carId
            """, nativeQuery = true)
    Optional<Reservation> findAllByCarId(@Param("carId") Long carId);

}
