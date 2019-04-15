package com.example.ec.security;

import com.example.ec.domain.User;
import com.example.ec.repo.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static org.springframework.security.core.userdetails.User.withUsername;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service to associate user with password and roles setup in the database.
 *
 * Created by Mary Ellen Bowman
 */
@Component
public class ExploreCaliUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExploreCaliUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		LOGGER.info("INSIDE loadUserByUsername method");
		User user = userRepository.findByUsername(s).orElseThrow(() ->
		new UsernameNotFoundException(String.format("User with name %s does not exist", s)));

		Set<GrantedAuthority> grantedAuthorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRolename()))
				.collect(Collectors.toSet());
		
		//org.springframework.security.core.userdetails.User.withUsername() builder
		return withUsername(user.getUsername())
				.password(user.getPassword())
				.authorities(grantedAuthorities)
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
	}
}