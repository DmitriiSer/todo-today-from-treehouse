package serikov.dmitrii.todotoday.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import serikov.dmitrii.todotoday.model.User;
import serikov.dmitrii.todotoday.web.FlashMessage;

@Controller
public class LoginController {

  @RequestMapping(path = "/login", method = RequestMethod.GET)
  public String loginForm(ModelMap model, HttpServletRequest request) {

    User user;
    try {
      user = (User) request.getSession().getAttribute("user");
      request.getSession().removeAttribute("user");
      if (user == null) {
        user = new User();
      }
    } catch (Exception e) {
      user = new User();
    }
    model.put("user", user);

    try {
      FlashMessage flash = (FlashMessage) request.getSession().getAttribute("flash");
      model.put("flash", flash);
      request.getSession().removeAttribute("flash");
    } catch (Exception e) {
    }

    return "login";
  }

}
