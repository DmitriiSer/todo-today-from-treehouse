package serikov.dmitrii.todotoday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import serikov.dmitrii.todotoday.dao.UserDao;
import serikov.dmitrii.todotoday.model.User;
import serikov.dmitrii.todotoday.model.UserBlockedException;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Autowired
  private LoginAttemptService loginAttemptService;

  @Override
  public User findByUsername(String username) {
    // load user from the DB
    return userDao.findByUsername(username);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,
      UserBlockedException {
    // check if user is blocked
    if (loginAttemptService.isBlocked()) {
      throw new UserBlockedException("User blocked");
    }

    // load user from the DB
    User user = userDao.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }

    return user;
  }

}
