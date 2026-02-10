package com.briano.meeting_room_booking_system.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDTO {
	
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;

}
