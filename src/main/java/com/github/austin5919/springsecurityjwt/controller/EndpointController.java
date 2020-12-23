package com.github.austin5919.springsecurityjwt.controller;

import com.github.austin5919.springsecurityjwt.model.AuthenticationRequest;
import com.github.austin5919.springsecurityjwt.model.AuthenticationResponse;
import com.github.austin5919.springsecurityjwt.service.UserDetailsService;
import com.github.austin5919.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointController {

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private UserDetailsService userDetailsService;

  @Autowired private JwtUtil jwtTokenUtil;

  @GetMapping("/hello")
  public String hello() {
    return "Hello world!";
  }

  @PostMapping("/authenticate")
  public ResponseEntity createAuthenticationToken(
      @RequestBody AuthenticationRequest authenticationRequest) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              authenticationRequest.getUsername(), authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      throw new AccessDeniedException("Incorrect username or password", e);
    }
    final UserDetails userDetails =
        userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

    final String jwt = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}
