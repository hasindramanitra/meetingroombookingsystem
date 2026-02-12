package com.briano.meeting_room_booking_system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briano.meeting_room_booking_system.dto.RoomDTO;
import com.briano.meeting_room_booking_system.entity.Room;
import com.briano.meeting_room_booking_system.exception.DuplicateRoomNameException;
import com.briano.meeting_room_booking_system.exception.RoomNotFoundException;
import com.briano.meeting_room_booking_system.mapper.RoomMapper;
import com.briano.meeting_room_booking_system.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomServiceImpl implements RoomService{
	
	
	private final RoomRepository roomRepository;
	private final RoomMapper roomMapper;

	@Override
	@Transactional
	public RoomDTO createRoom(RoomDTO roomDTO) {
		boolean existRoom = this.roomRepository.existsByName(roomDTO.getName());
		
		if (existRoom) {
			throw new DuplicateRoomNameException(roomDTO.getName());
		}
		
		Room room = this.roomMapper.toEntity(roomDTO);
		Room createdRoom = this.roomRepository.save(room);
		
		return this.roomMapper.toDto(createdRoom);
	}

	@Override
	public List<RoomDTO> getAllRooms() {
		List<Room> rooms = this.roomRepository.findAll();
		
		return this.roomMapper.toListDtos(rooms);
	}

	@Override
	public RoomDTO getRoomById(Long id) {
		Room room = this.roomRepository.findById(id)
				.orElseThrow(() -> new RoomNotFoundException(id));
		
		return this.roomMapper.toDto(room);
	}

	@Override
	@Transactional
	public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {
		Room existingRoom = this.roomRepository.findById(id)
				.orElseThrow(() -> new RoomNotFoundException(id));
		
		this.roomMapper.updateEntityFromDto(roomDTO, existingRoom);
		Room updatedRoom = this.roomRepository.save(existingRoom);
		
		return this.roomMapper.toDto(updatedRoom);
		
	}

	@Override
	@Transactional
	public void deleteRoom(Long id) {
		Room existingRoom = this.roomRepository.findById(id)
				.orElseThrow(() -> new RoomNotFoundException(id));
		
		this.roomRepository.delete(existingRoom);
		
	}

}
