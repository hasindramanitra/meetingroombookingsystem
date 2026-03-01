package com.briano.meeting_room_booking_system.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.briano.meeting_room_booking_system.dto.ReservationDTO;
import com.briano.meeting_room_booking_system.entity.Reservation;
import com.briano.meeting_room_booking_system.repository.RoomRepository;
import com.briano.meeting_room_booking_system.repository.UserRepository;
import com.briano.meeting_room_booking_system.resolver.RoomResolver;
import com.briano.meeting_room_booking_system.resolver.UserResolver;

@Mapper(
		componentModel = "spring",
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
		uses = {RoomResolver.class, UserResolver.class}
)
public interface ReservationMapper {
	
	public static final RoomRepository roomRepository = null;
	public static final UserRepository userRepository = null;
	
	@Mapping(target = "username", source = "user", qualifiedByName = "toUsername")
	@Mapping(target = "roomName", source = "room")
	ReservationDTO toDto(Reservation reservation);
	
	@Mapping(target = "user", source = "username", qualifiedByName = "resolveByUsername")
	@Mapping(target = "room", source = "roomName")
	Reservation toEntity(ReservationDTO reservationDTO);
	
	List<ReservationDTO> toDtos(List<Reservation> reservations);
	
	void updateEntityFromDto(ReservationDTO dto, @MappingTarget Reservation reservation);
}
