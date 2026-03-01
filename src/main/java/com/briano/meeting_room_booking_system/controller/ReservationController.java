package com.briano.meeting_room_booking_system.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briano.meeting_room_booking_system.dto.ReservationDTO;
import com.briano.meeting_room_booking_system.service.ReservationServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationServiceImpl reservationServiceImpl;
	
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<ReservationDTO>> getAllReservations() {
		
		List<ReservationDTO> reservationDTOs = this.reservationServiceImpl.getAllReservations();
		
		return ResponseEntity.ok(reservationDTOs);
	}
	
	
	@PostMapping
	public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO dto) {
		ReservationDTO reservationDTO = this.reservationServiceImpl.createReservation(dto);
		
		URI location = URI.create("/api/v1/reservations/" + reservationDTO.getId());
		
		return ResponseEntity.created(location).body(reservationDTO);
	}
	
	@PutMapping("/{reservation-id}")
	@PreAuthorize("@reservationSecurity.isOwner(#reservationId)")
	public ResponseEntity<ReservationDTO> updateReservation(
			@PathVariable("reservation-id")
			Long reservationId, 
			@RequestBody @Valid ReservationDTO dto) {
		ReservationDTO reservationDTO = this.reservationServiceImpl.updateReservation(dto, reservationId);
		
		return ResponseEntity.ok(reservationDTO);
		
	}
	
	@DeleteMapping("/{reservation-id}")
	@PreAuthorize("@reservationSecurity.isOwner(#reservationId)")
	public ResponseEntity<Void> deleteReservation(@PathVariable("reservation-id") Long reservationId) {
		this.reservationServiceImpl.deleteReservation(reservationId);
		
		return ResponseEntity.noContent().build();
	}
	
}
