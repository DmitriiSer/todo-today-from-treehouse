package serikov.dmitrii.todotoday.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import serikov.dmitrii.todotoday.model.UserBlockedException;

@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {

  @Autowired
  private HttpServletRequest request;

  private final int MAX_ATTEMPT = 3;
  private LoadingCache<String, Integer> attemptsCache;

  public LoginAttemptServiceImpl() {
    super();
    attemptsCache = CacheBuilder.newBuilder().
        expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
      public Integer load(String key) {
        return 0;
      }
    });
  }

  @Override
  public Integer getAttemptsLeft() {
    Integer totalAttempts = null;
    try {
      totalAttempts = attemptsCache.get(getClientIP());
    } catch (ExecutionException e) {
      totalAttempts = 0;
    }
    return MAX_ATTEMPT - totalAttempts;
  }

  @Override
  public void loginSucceeded() {
    attemptsCache.invalidate(getClientIP());
  }

  @Override
  public void loginFailed() {
    int attempts = 0;
    String ip = getClientIP();

    try {
      attempts = attemptsCache.get(ip);
    } catch (ExecutionException e) {
      attempts = 0;
    }
    attempts++;
    attemptsCache.put(ip, attempts);
  }

  @Override
  public boolean isBlocked() {
    try {
      String ip = getClientIP();
      return attemptsCache.get(ip) >= MAX_ATTEMPT;
    } catch (ExecutionException e) {
      return false;
    }
  }

  @Override
  public String getClientIP() {
    String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      return request.getRemoteAddr();
    }
    return xfHeader.split(",")[0];
  }

}
