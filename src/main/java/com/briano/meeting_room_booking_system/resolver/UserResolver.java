package com.briano.meeting_room_booking_system.resolver;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import com.briano.meeting_room_booking_system.entity.User;
import com.briano.meeting_room_booking_system.repository.UserRepository;

@Component
public class UserResolver {

	private final UserRepository userRepository;

	public UserResolver(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Named("toUsername")
	public String toUsername(User user) {
		return user != null ? user.getUsername() : null;
	}

	@Named("resolveByUsername")
	public User resolveByUsername(String username) {
		if (username == null) {
			return null;
		}
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
	}
}
