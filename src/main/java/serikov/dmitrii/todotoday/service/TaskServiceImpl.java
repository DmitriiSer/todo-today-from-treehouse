package serikov.dmitrii.todotoday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import serikov.dmitrii.todotoday.dao.TaskDao;
import serikov.dmitrii.todotoday.model.Task;

@Service
public class TaskServiceImpl implements TaskService {

  @Autowired
  private TaskDao taskDao;

  @Override
  public List<Task> findAll() {
    Iterable<Task> iterable= taskDao.findAll();
    List<Task> tasks =new ArrayList<>();
    iterable.forEach(tasks::add);
    return tasks;
  }

  @Override
  public void save(Task task) {
    taskDao.save(task);
  }
}
