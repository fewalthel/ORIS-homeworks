package ru.itis.homework_2.services;

import freemarker.template.TemplateException;
import ru.itis.homework_2.dto.UserForm;

import java.io.IOException;

public interface SignUpService {
    void addUser(UserForm userForm) throws TemplateException, IOException;
}
