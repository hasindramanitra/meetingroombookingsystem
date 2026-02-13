package com.briano.meeting_room_booking_system.repository;



import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briano.meeting_room_booking_system.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long>{

	boolean existsByName(String name);
	
	Optional<Equipment> findByName(String name);
	
	List<Equipment> findAllByNameIn(Set<String> names);
}
