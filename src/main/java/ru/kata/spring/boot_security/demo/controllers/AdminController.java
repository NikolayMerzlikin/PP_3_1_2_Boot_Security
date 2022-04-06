package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String admin(Model model, Principal principal) {
        List<User> list = userService.findAll();
        // User user = userService.findUserByUserName(principal.getName());
        model.addAttribute("Users", list);
        return "user";
    }

    @GetMapping(value = "/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping(value = "/add")
    public String addNewUser(@ModelAttribute("newUser") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.findById(id).get();
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "/edit/")
    public String editUser(@ModelAttribute(value = "user") User user) {
        userService.edit(user);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/remove/{id}")
    public String remove(@PathVariable(name = "id") long id) {
        userService.remove(id);
        return "redirect:/admin/";
    }
}
