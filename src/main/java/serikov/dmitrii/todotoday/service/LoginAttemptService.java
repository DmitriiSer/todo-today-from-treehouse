package serikov.dmitrii.todotoday.service;

import java.util.concurrent.ExecutionException;

public interface LoginAttemptService {

  public Integer getAttemptsLeft();

  public void loginSucceeded();

  public void loginFailed();

  boolean isBlocked();

  public String getClientIP();
}
