package com.briano.meeting_room_booking_system.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rooms")
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
	
	@ManyToMany
	@JoinTable(name = "room_equipment",
		joinColumns = @JoinColumn(referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(referencedColumnName = "id")
			)
	private Set<Equipment> equipments = new HashSet<>();
}
