package com.briano.meeting_room_booking_system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.briano.meeting_room_booking_system.entity.User;
import com.briano.meeting_room_booking_system.repository.UserRepository;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
			return Optional.empty();
		}
		
		return Optional.of(authentication.getName());
	}
}
