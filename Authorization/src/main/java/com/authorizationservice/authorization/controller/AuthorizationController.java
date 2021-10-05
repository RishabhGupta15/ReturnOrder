package com.authorizationservice.authorization.controller;

import org.springframework.http.MediaType;
import com.authorizationservice.authorization.dto.VaildatingDTO;
import com.authorizationservice.authorization.exceptions.BadCredentialException;
import com.authorizationservice.authorization.exceptions.LoginException;
import com.authorizationservice.authorization.model.AuthenticationRequest;
import com.authorizationservice.authorization.model.AuthenticationResponse;
import com.authorizationservice.authorization.model.PasswordChangeRequest;
import com.authorizationservice.authorization.repository.AuthRequestRepo;
import com.authorizationservice.authorization.service.AppUserDetailsService;
import com.authorizationservice.authorization.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(value = "Login and Validation endpoints for Authorization Service")
public class AuthorizationController {

	@Autowired
	private AppUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private AuthRequestRepo userRepo;

	private VaildatingDTO vaildatingDTO = new VaildatingDTO();

	@PostMapping("/login")
	@ApiOperation(value = "customerLogin", notes = "takes customer credentials and generates the unique JWT for each customer", httpMethod = "POST", response = ResponseEntity.class)
	public ResponseEntity<Object> createAuthorizationToken(
			@ApiParam(name = "customerLoginCredentials", value = "Login credentials of the Customer") @RequestBody AuthenticationRequest authenticationRequest)
			throws LoginException {
		log.info("BEGIN - [login(customerLoginCredentials)]");
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		log.debug("{}", userDetails);
		if (userDetails.getPassword().equals(authenticationRequest.getPassword())) {
			log.info("END - [login(customerLoginCredentials)]");
			return new ResponseEntity<>(new AuthenticationResponse(authenticationRequest.getUserName(),
					jwtTokenUtil.generateToken(userDetails)), HttpStatus.OK);
		} else {
			log.info("END - [login(customerLoginCredentials)]");
			throw new LoginException("Invalid Username or Password");
		}
	}

	@GetMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "tokenValidation", notes = "returns boolean after validating JWT", httpMethod = "GET", response = ResponseEntity.class)
	public ResponseEntity<VaildatingDTO> validatingAuthorizationToken(
			@ApiParam(name = "JWT-token", value = "JWT generated for current customer present") @RequestHeader(name = "Authorization") String tokenDup) {
		log.info("BEGIN - [validatingAuthorizationToken(JWT-token)]");
		String token = tokenDup.substring(7);
		try {
			UserDetails user = userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(token));
			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(token, user))) {
				log.debug("Token matched is Valid");
				log.info("Token matched is Valid");
				log.info("END - validate()");
				vaildatingDTO.setValidStatus(true);
				return new ResponseEntity<>(vaildatingDTO, HttpStatus.OK);
			} else {
				log.debug("Token matched is Invalid");
				log.info("END Else Part- validatingAuthorizationToken()");
				vaildatingDTO.setValidStatus(false);
				return new ResponseEntity<>(vaildatingDTO, HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			log.debug("Invalid token - Bad Credentials Exception");
			log.info("END Exception - validatingAuthorizationToken()");
			vaildatingDTO.setValidStatus(false);
			return new ResponseEntity<>(vaildatingDTO, HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping("/changepassword")
	public boolean changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest)
			throws BadCredentialException, NullPointerException, LoginException {
		boolean isvalid = false;
		boolean status = false;
		log.info("Login Authenticating");
		try {
			final UserDetails userDetails = userDetailsService.loadUserByUsername(passwordChangeRequest.getUsername());
			log.debug("{}", userDetails);
			if (userDetails.getPassword().equals(passwordChangeRequest.getOldPassword())) {
				log.info("END - [login(customerLoginCredentialsif)]");
				isvalid = true;
			} 
			else {
				log.info("END - [login(customerLoginCredentialselse)]");
				status=false;
			}

			if (isvalid) {

				AuthenticationRequest user1 = userRepo.findByUserName(passwordChangeRequest.getUsername());
				user1.setPassword(passwordChangeRequest.getNewPassword());
				userRepo.save(user1);
				status = true;
			}
		} 
		
//		catch (LoginException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		catch(Exception ex) 
		{
			log.info("Bad Credential");
			isvalid = false;
			
			//throw new BadCredentialException();

		} 
		

		return status;
	}

	@GetMapping(path = "/health-check")
	public ResponseEntity<String> healthCheck() {
		log.info("Authorization Microservice is Up and Running....");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
}
