package serikov.dmitrii.todotoday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import serikov.dmitrii.todotoday.dao.UserDao;
import serikov.dmitrii.todotoday.model.User;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public User findByUsername(String username) {
    // load user from the DB
    return userDao.findByUsername(username);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // load user from the DB
    User user = userDao.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }

    return user;
  }
}
