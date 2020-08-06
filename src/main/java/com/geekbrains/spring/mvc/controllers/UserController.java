package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.models.Product;
import com.geekbrains.spring.mvc.models.User;
import com.geekbrains.spring.mvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("frontUsers", service.getAll());
        return "users-all";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam int age) {
        service.save(new User(name, age));
        return "redirect:/users/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        service.delete(id);
        return "redirect:/users/all";
    }

    @GetMapping("/find")
    @ResponseBody
    public User findUser(@RequestParam long id) {
        return service.find(id);
    }
}
