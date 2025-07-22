package br.com.dougluciano.boadtaskjava.controller;

import br.com.dougluciano.boadtaskjava.entities.Task;
import br.com.dougluciano.boadtaskjava.entities.User;
import br.com.dougluciano.boadtaskjava.enums.TaskPriority;
import br.com.dougluciano.boadtaskjava.enums.TaskStatus;
import br.com.dougluciano.boadtaskjava.service.TaskService;
import br.com.dougluciano.boadtaskjava.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("allStatus", TaskStatus.values());
        model.addAttribute("allPriorities", TaskPriority.values());
        return "task-form";
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute("task") Task task, BindingResult result, Authentication authentication, Model model){
        if(result.hasErrors()){
            model.addAttribute("allStatus", TaskStatus.values());
            model.addAttribute("allPriorities", TaskPriority.values());
            return "task-form";
        }

        var username = authentication.getName();
        var user = userService.findByUsername(username);

        service.createTask(task, user);

        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditTaskForm(@PathVariable("id")Long id, Model model){

        var task = service.findById(id);

        model.addAttribute("task", task);
        model.addAttribute("allStatus", TaskStatus.values());
        model.addAttribute("allPriorities", TaskPriority.values());

        return "task-form";
    }

    @PostMapping("/edit/{id}")
    public String updateTask(
            @PathVariable("id")Long id, @Valid @ModelAttribute("task") Task task,
            BindingResult result, Model model
    ){
        if(result.hasErrors()){
            model.addAttribute("allStatus", TaskStatus.values());
            model.addAttribute("allPriorities", TaskPriority.values());
            return "task-form";
        }

        service.updateTask(id, task);

        return "redirect:/tasks";

    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id, Authentication authentication){
        var username = authentication.getName();

        service.deleteTask(id, username);

        return "redirect:/tasks";
    }
}
