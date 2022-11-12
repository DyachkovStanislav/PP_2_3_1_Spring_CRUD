package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;
import java.util.List;

@Controller

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAllUsers(Model model) {
        List<User> usersList = userService.findAllUsers();
        model.addAttribute("userList", usersList);
        return "users";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:/users";
    }

    @GetMapping("/createdUser")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "add";
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        User user = userService.findById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

}

