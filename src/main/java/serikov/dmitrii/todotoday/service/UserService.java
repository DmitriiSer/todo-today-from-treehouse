package serikov.dmitrii.todotoday.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import serikov.dmitrii.todotoday.model.User;

public interface UserService extends UserDetailsService {
  User findByUsername(String username);
}
