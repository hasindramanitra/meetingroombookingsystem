package com.briano.meeting_room_booking_system.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.briano.meeting_room_booking_system.dto.RoomDTO;
import com.briano.meeting_room_booking_system.entity.Room;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomMapper {

	Room toEntity(RoomDTO roomDTO);
	
	RoomDTO toDto(Room room);
	
	List<RoomDTO> toListDtos(List<Room> rooms);
	
	void updateEntityFromDto(RoomDTO roomDTO, @MappingTarget Room room);
}
