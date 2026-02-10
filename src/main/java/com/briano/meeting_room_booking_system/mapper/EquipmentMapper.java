package com.briano.meeting_room_booking_system.mapper;

import java.util.List;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.briano.meeting_room_booking_system.dto.EquipmentDTO;
import com.briano.meeting_room_booking_system.entity.Equipment;

@Mapper(
		componentModel = "spring",
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EquipmentMapper {
	
	Equipment toEquipment(EquipmentDTO equipmentDTO);
	
	EquipmentDTO toEquipmentDTO(Equipment equipment);
	
	List<EquipmentDTO> toEquipmentDTOs(List<Equipment> equipments);
	
	//@Mapping(target = "id", ignore = true)
	void updateEntityFromDTO(EquipmentDTO equipmentDTO, @MappingTarget Equipment equipment);
}
