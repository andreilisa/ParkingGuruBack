package parking.guru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import parking.guru.models.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import parking.guru.models.enums.Status;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.latitude = :latitude AND r.longitude = :longitude")
    Optional<Reservation> findByLocation(@Param("latitude") String latitude, @Param("longitude") String longitude);

    @Query("SELECT r FROM Reservation r WHERE r.status = :status")
    Optional<Reservation> findByStatus(@Param("status") Status status);

    // Find reservations by user ID where endDateTime is not null (history)
    List<Reservation> findByUserIdAndEndDateTimeIsNotNull(Long userId);

    // Find the active reservation for a user where endDateTime is null
    Reservation findByUserIdAndEndDateTimeIsNull(Long userId);

    // Find reservation by plate number
    Reservation findByPlateNumber(String plateNumber);
}
