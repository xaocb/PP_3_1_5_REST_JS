package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RolesRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.validation.EmailValidator;


import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final UserService userService;

    private final RolesRepository rolesRepository;

    private final EmailValidator emailValidator;

    @Autowired
    public AdminsController(UserService userService, RolesRepository rolesRepository, EmailValidator emailValidator) {
        this.userService = userService;
        this.rolesRepository = rolesRepository;
        this.emailValidator = emailValidator;
    }

    @GetMapping()
    public String findAllUsers(Model model) {
        User user = userService.getAuthUser();
        model.addAttribute("userlist", userService.findAllUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("rolelist", rolesRepository.findAll());
        model.addAttribute("title", user);
        return "pages/admin";
    }

//    @GetMapping("/{id}")
//    public String getUserById(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.getUserInfo(id));
//        return "pages/getUserInfo";
//    }

//    @GetMapping("/add")
//    public String addUser(Model model) {
//        model.addAttribute("user", new User());
//        return "pages/new";
//    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        emailValidator.validateNewUserEmail(user, bindingResult);
        if (bindingResult.hasErrors()){
            return "pages/admin";}
        userService.save(user);
        return "redirect:/admin";
    }

//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("user", userService.getUserInfo(id));
//        return "pages/edit";
//    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        emailValidator.validateEditedUserEmail(user, bindingResult);
        if (bindingResult.hasErrors()){
            return "pages/admin";}
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
