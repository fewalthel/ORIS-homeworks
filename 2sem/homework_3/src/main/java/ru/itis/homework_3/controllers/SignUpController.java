package ru.itis.homework_3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.homework_3.dto.UserForm;

import freemarker.template.TemplateException;
import ru.itis.homework_3.services.SignUpService;

import java.io.IOException;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "sign_up_page";
    }

    @PostMapping("/signUp")
    public String signUp(UserForm form) throws TemplateException, IOException {
        signUpService.addUser(form);
        return "redirect:/signUp";
    }
}