package br.com.dougluciano.boadtaskjava.controller;

import br.com.dougluciano.boadtaskjava.entities.Task;
import br.com.dougluciano.boadtaskjava.entities.User;
import br.com.dougluciano.boadtaskjava.service.TaskService;
import br.com.dougluciano.boadtaskjava.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;
    private final UserService userService;

    public TaskController(TaskService service, UserService userService){
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public String showTaskBoard(Model model, Authentication authentication){

        String username = authentication.getName();

        User user = userService.findByUsername(username);

        List<Task> tasks = service.findTaskByUserId(user.getId());

        model.addAttribute("tasks", tasks);
        model.addAttribute("userName", user.getName());

        return "board";
    }
}
