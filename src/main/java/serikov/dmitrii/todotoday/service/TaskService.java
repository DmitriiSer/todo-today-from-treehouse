package serikov.dmitrii.todotoday.service;

import java.util.List;
import serikov.dmitrii.todotoday.model.Task;

public interface TaskService {
  List<Task> findAll();
}
