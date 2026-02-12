package com.briano.meeting_room_booking_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "rooms")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int capacity;
}
