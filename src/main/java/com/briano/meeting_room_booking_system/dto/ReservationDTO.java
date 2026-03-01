package com.briano.meeting_room_booking_system.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

	private Long id;
	
	private LocalDateTime startDateTime;
	
	private LocalDateTime endDateTime;
	
	private String username;
	
	private String roomName;
	
	private LocalDateTime createdAt;
}
