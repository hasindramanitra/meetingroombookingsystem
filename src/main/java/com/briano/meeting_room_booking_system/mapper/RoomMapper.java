package com.briano.meeting_room_booking_system.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import com.briano.meeting_room_booking_system.dto.RoomDTO;
import com.briano.meeting_room_booking_system.entity.Equipment;
import com.briano.meeting_room_booking_system.entity.Room;
import com.briano.meeting_room_booking_system.repository.EquipmentRepository;
import com.briano.meeting_room_booking_system.resolver.EquipmentResolver;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = EquipmentResolver.class)
public interface RoomMapper {

	public static final EquipmentRepository equipmentRepository = null;

	@Mapping(target = "equipments", source = "equipmentNames")
	Room toEntity(RoomDTO roomDTO);

	@Mapping(target = "equipmentNames", source = "equipments")
	RoomDTO toDto(Room room);

	List<RoomDTO> toListDtos(List<Room> rooms);

	//void updateEntityFromDto(RoomDTO roomDTO, @MappingTarget Room room);
}
