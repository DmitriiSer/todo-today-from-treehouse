package serikov.dmitrii.todotoday.service;

import java.util.List;
import java.util.Optional;
import serikov.dmitrii.todotoday.model.Task;

public interface TaskService {
  List<Task> findAll();
  void save(Task task);
  Optional<Task> findById(Long id);
  void toggleComplete(Task task);
}
