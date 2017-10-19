package serikov.dmitrii.todotoday.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController {

  @RequestMapping({"/"})
  public String home() {
    return "todo";
  }
}
