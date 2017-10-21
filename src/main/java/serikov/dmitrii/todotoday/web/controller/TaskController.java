package serikov.dmitrii.todotoday.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import serikov.dmitrii.todotoday.model.Task;
import serikov.dmitrii.todotoday.service.TaskService;

@Controller
public class TaskController {

  @Autowired
  private TaskService taskService;

  @RequestMapping({"/"})
  public String home(ModelMap model) {

    List<Task> tasks = taskService.findAll();
    model.put("tasks", tasks);

    return "todo";
  }
}
