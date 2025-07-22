package br.com.dougluciano.boadtaskjava.controller;

import br.com.dougluciano.boadtaskjava.entities.User;
import br.com.dougluciano.boadtaskjava.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service){ this.service = service; }

    @GetMapping("/new")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result){
        if (result.hasErrors()){
            return "register";
        }

        service.create(user);
        return "redirect:/login?success";
    }

}
