package com.svc.myproject.web.controllers;

import com.svc.myproject.payload.request.LoginRequest;
import com.svc.myproject.payload.request.SignupRequest;
import com.svc.myproject.payload.response.JwtResponse;
import com.svc.myproject.payload.response.MessageResponse;
import com.svc.myproject.security.jwt.JwtUtils;
import com.svc.myproject.security.services.UserDetailsImpl;
import com.svc.myproject.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final UserService userService;
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;



	public AuthController(UserService userService,
						  JwtUtils jwtUtils,
						  AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.jwtUtils = jwtUtils;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
											  UriComponentsBuilder builder) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
						loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(),
												 userDetails.getUsername(),
												 userDetails.getEmail(),
												 roles));
//		return ResponseEntity.created(builder.path("/api/auth/signin")
//				.buildAndExpand().toUri()).build();
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest,
										  UriComponentsBuilder builder) {

		if (userService.findUserByName(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userService.findUserByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		userService.createUser(signUpRequest);
		return ResponseEntity.created(builder.path("/api/auth/signup")
				.buildAndExpand().toUri()).build();
//		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
