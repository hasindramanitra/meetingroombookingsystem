package com.briano.meeting_room_booking_system.config;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briano.meeting_room_booking_system.entity.Reservation;
import com.briano.meeting_room_booking_system.entity.User;
import com.briano.meeting_room_booking_system.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationSecurity {

	private final ReservationRepository repository;
	
	@Transactional(readOnly = true)
	public boolean isOwner(final Long reservationId) {
		final Authentication authentication = SecurityContextHolder
				.getContext()
				.getAuthentication();
		
		final Integer userId = ((User) authentication.getPrincipal()).getId();
		
		final Reservation reservation = this.repository.findById(reservationId)
					.orElseThrow(() -> new RuntimeException("Reservation not found with ID : " + reservationId));
		
		return reservation.getUser().getId().equals(userId);
	}
	
}
