package com.briano.meeting_room_booking_system.service;

import java.util.List;

import com.briano.meeting_room_booking_system.dto.ReservationDTO;

public interface ReservationService {
	
	List<ReservationDTO> getAllReservations();
	
	ReservationDTO createReservation(ReservationDTO dto);
	
	ReservationDTO getReservationById(Long id);
	
	ReservationDTO updateReservation(ReservationDTO dto, Long id);
	
	void deleteReservation(Long id);
}
