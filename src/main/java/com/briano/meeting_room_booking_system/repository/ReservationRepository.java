package com.briano.meeting_room_booking_system.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.briano.meeting_room_booking_system.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("SELECT COUNT(r) > 0 FROM Reservation r " + " WHERE r.room.id = :roomId " + " AND r.startDateTime < :endDateTime " + " AND r.endDateTime > :startDateTime")
	boolean existsOverlappingReservation(
			@Param("roomId") Long roomId,
			@Param("startDateTime") LocalDateTime startDateTime,
			@Param("endDateTime") LocalDateTime endDateTime
			);
}
