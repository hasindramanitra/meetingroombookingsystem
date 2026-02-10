package com.briano.meeting_room_booking_system.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.briano.meeting_room_booking_system.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long>{

	boolean existsByName(String name);
}
