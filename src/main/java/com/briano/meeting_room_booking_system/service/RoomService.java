package com.briano.meeting_room_booking_system.service;

import java.util.List;


import com.briano.meeting_room_booking_system.dto.RoomDTO;


public interface RoomService {

	RoomDTO createRoom(RoomDTO roomDTO);
	
	List<RoomDTO> getAllRooms();
	
	RoomDTO getRoomById(Long id);
	
	RoomDTO updateRoom(Long id, RoomDTO roomDTO);
	
	void deleteRoom(Long id);
}
