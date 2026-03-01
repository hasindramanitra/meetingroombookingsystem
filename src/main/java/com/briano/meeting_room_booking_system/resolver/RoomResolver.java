package com.briano.meeting_room_booking_system.resolver;

import org.springframework.stereotype.Component;


import com.briano.meeting_room_booking_system.entity.Room;
import com.briano.meeting_room_booking_system.repository.RoomRepository;

@Component
public class RoomResolver {

	private final RoomRepository roomRepository;
	
	public RoomResolver(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}
	
	public String toName(Room room) {
		return room != null ? room.getName() : null;
	}
	
	public Room resolveByName(String name) {
		if (name == null) {
			return null;
		}
		
		return this.roomRepository.findByName(name)
				.orElseThrow(() -> new IllegalArgumentException("Room not found: " + name));
	}
}
