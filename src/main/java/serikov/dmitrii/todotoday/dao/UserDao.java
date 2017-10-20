package serikov.dmitrii.todotoday.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import serikov.dmitrii.todotoday.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
  User findByUsername(String username);
}
