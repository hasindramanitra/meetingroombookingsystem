package com.briano.meeting_room_booking_system.dto;




import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
	
	private Long id;
	
	@NotBlank(message = "Name cannot be blank.")
	private String name;
	
	//@ValidInteger(message = "Capacity must be a valid integer and not empty")
	@NotNull(message = "Field cannot be empty.")
    // Ensures the value is at least 0 (not negative)
    @Min(value = 1, message = "Value cannot be negative.")
    // Ensures the value is no more than 299
    @Max(value = 299, message = "Value cannot be greater than 299.")
	private int capacity;
	
	private Set<String> equipmentNames;

}
