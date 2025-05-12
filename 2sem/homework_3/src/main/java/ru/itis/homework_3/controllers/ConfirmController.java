package ru.itis.homework_3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.homework_3.services.UsersService;

@Controller
public class ConfirmController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/confirm/{code}")
    public String confirmUser(@PathVariable("code") String code, Model model) {
        boolean isConfirmed = usersService.confirmUser(code);

        if (isConfirmed) {
            model.addAttribute("message", "Email successfully confirmed!");
            return "redirect:/signIn";
        } else {
            model.addAttribute("error", "Invalid confirmation code");
            return "redirect:/signUp";
        }
    }
}