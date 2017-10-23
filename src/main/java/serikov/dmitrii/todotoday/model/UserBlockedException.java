package serikov.dmitrii.todotoday.model;

import org.springframework.security.core.AuthenticationException;

public class UserBlockedException extends AuthenticationException {
  public UserBlockedException(String msg, Throwable t) {
    super(msg, t);
  }

  public UserBlockedException(String msg) {
    super(msg);
  }
}
