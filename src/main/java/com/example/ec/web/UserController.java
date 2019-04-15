package com.example.ec.web;

import com.example.ec.domain.User;
import com.example.ec.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);   

	@PostMapping("/signin")
	public Authentication login(@RequestBody @Valid LoginDto loginDto) {
		LOGGER.info("USERNAME: " + loginDto.getUsername());
		LOGGER.info("PASSWORD: " + loginDto.getPassword());
		return userService.signin(loginDto.getUsername(), loginDto.getPassword()) ;
	}

	@PostMapping("/signup")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User signup(@RequestBody @Valid LoginDto loginDto){
		return userService.signup(loginDto.getUsername(), loginDto.getPassword(), loginDto.getFirstName(),
				loginDto.getLastName()).orElseThrow(() -> new RuntimeException("User already exists"));
	}
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getAllUsers() {
		return userService.getAll();
	}

	/**
	 * Exception handler if NoSuchElementException is thrown in this Controller
	 *
	 * @param ex exception
	 * @return Error message String.
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RuntimeException.class)
	public String return400(RuntimeException ex) {
		LOGGER.error("Unable to complete transaction", ex);
		return ex.getMessage();
	} 
}