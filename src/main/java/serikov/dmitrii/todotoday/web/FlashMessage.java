package serikov.dmitrii.todotoday.web;

public class FlashMessage {
  public enum Status {
    SUCCESS, FAILURE
  }

  private String message;
  private Status status;

  public FlashMessage(String message, Status status) {
    this.message = message;
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
