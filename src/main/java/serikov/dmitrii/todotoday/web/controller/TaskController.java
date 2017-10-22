package serikov.dmitrii.todotoday.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;
import serikov.dmitrii.todotoday.model.Task;
import serikov.dmitrii.todotoday.model.User;
import serikov.dmitrii.todotoday.service.TaskService;
import serikov.dmitrii.todotoday.service.UserService;

@Controller
public class TaskController {

  @Autowired
  private TaskService taskService;

  @RequestMapping({"/"})
  public String home(ModelMap model) {

    List<Task> tasks = taskService.findAll();
    model.put("tasks", tasks);
    model.put("newTask", new Task());

    return "todo";
  }

  @RequestMapping(path = "/tasks", method = RequestMethod.POST)
  public String addTask(@ModelAttribute Task task, Principal principal) {

    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;

    User user = (User)token.getPrincipal();

    task.setUser(user);

    taskService.save(task);
    return "redirect:/";
  }

}
