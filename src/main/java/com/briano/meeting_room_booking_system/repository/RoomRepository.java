package com.briano.meeting_room_booking_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briano.meeting_room_booking_system.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{

	boolean existsByName(String name);

}
