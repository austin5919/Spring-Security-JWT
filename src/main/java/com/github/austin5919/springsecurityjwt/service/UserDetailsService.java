package com.github.austin5919.springsecurityjwt.service;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements
    org.springframework.security.core.userdetails.UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) {
    return new User("foo", "bar", new ArrayList<>());
  }
}
