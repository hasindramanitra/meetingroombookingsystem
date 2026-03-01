package com.briano.meeting_room_booking_system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briano.meeting_room_booking_system.dto.ReservationDTO;
import com.briano.meeting_room_booking_system.entity.Reservation;
import com.briano.meeting_room_booking_system.entity.Room;
import com.briano.meeting_room_booking_system.exception.ReservationConflictException;
import com.briano.meeting_room_booking_system.mapper.ReservationMapper;
import com.briano.meeting_room_booking_system.repository.ReservationRepository;
import com.briano.meeting_room_booking_system.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {
	
	private final ReservationRepository reservationRepository;
	private final ReservationMapper reservationMapper;
	private final RoomRepository roomRepository;
	
	@Override
	public List<ReservationDTO> getAllReservations() {
		
		List<Reservation> reservations = this.reservationRepository.findAll();
		List<ReservationDTO> reservationDTOs = this.reservationMapper.toDtos(reservations);
		
		return reservationDTOs;
	}
	
	
	@Override
	@Transactional
	public ReservationDTO createReservation(ReservationDTO dto) {
		Room room = this.roomRepository.findByName(dto.getRoomName())
				.orElseThrow(() -> new RuntimeException("Room not found with name :" + dto.getRoomName()));
		
		boolean isOverlapping = this.reservationRepository.existsOverlappingReservation(room.getId(), dto.getStartDateTime(), dto.getEndDateTime());
		
		if (isOverlapping) {
			throw new ReservationConflictException("There is already a reservation with this room at the date.");
		}
		
		Reservation reservation = this.reservationMapper.toEntity(dto);
		reservation.setRoom(room);
		Reservation createdReservation = this.reservationRepository.save(reservation);
		
		return this.reservationMapper.toDto(createdReservation);
	}
	
	
	@Override
	public ReservationDTO getReservationById(Long id) {
		Reservation reservation = this.reservationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reservation not found with the ID : " + id));
		
		return this.reservationMapper.toDto(reservation);
	}
	
	@Override
	@Transactional
	public ReservationDTO updateReservation(ReservationDTO dto, Long id) {
		Reservation existingReservation = this.reservationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reservation not found with this ID : " + id));
		
		Room room = this.roomRepository.findByName(dto.getRoomName())
				.orElseThrow(() -> new RuntimeException("Room not found with this name : " + dto.getRoomName()));
		existingReservation.setRoom(room);
		
		boolean isOverlapping = this.reservationRepository.existsOverlappingReservation(room.getId(), dto.getStartDateTime(), dto.getEndDateTime());
		
		if (isOverlapping) {
			throw new ReservationConflictException("There is already a reservation with this room at the date.");
		}
		
		this.reservationMapper.updateEntityFromDto(dto, existingReservation);
		Reservation updatedReservation = this.reservationRepository.save(existingReservation);
		
		return this.reservationMapper.toDto(updatedReservation);
	}
	
	@Override
	public void deleteReservation(Long id) {
		Reservation existingReservation = this.reservationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reservation not found with this ID : " + id));
		
		this.reservationRepository.delete(existingReservation);
		
	}

}
