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
}
