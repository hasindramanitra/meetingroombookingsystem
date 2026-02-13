package com.briano.meeting_room_booking_system.resolver;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.briano.meeting_room_booking_system.entity.Equipment;
import com.briano.meeting_room_booking_system.repository.EquipmentRepository;

@Component
public class EquipmentResolver {

	private final EquipmentRepository equipmentRepository;
	
	public EquipmentResolver(EquipmentRepository equipmentRepository) {
		this.equipmentRepository = equipmentRepository;
	}
	
	public String toName(Equipment equipment) {
		return equipment != null ? equipment.getName() : null;
	}
	
	public Equipment resolveByName(String name) {
		if (name == null) {
			return null;
		}
		
		return this.equipmentRepository.findByName(name)
				.orElseThrow(() -> new IllegalArgumentException("Equipment not found: " + name));
	}
	
	public Set<String> toNameSet(Set<Equipment> equipments) {
		if (equipments == null) return null;
		
		return equipments.stream()
				.map(this::toName)
				.collect(Collectors.toSet());
	}
	
	public Set<Equipment> resolveByNameSet(Set<String> names) {
		if (names == null) return null;
		
		return new HashSet<>(this.equipmentRepository.findAllByNameIn(names));
	}
}
