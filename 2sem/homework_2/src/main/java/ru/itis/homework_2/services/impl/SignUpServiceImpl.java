package ru.itis.homework_2.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.homework_2.dto.UserForm;
import ru.itis.homework_2.models.Role;
import ru.itis.homework_2.models.User;
import ru.itis.homework_2.repository.UsersRepository;
import ru.itis.homework_2.services.SignUpService;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserForm userForm) {
        User user = User.builder()
                .email(userForm.getEmail())
                .username(userForm.getUsername())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .role(Role.USER)
                .build();

        usersRepository.save(user);
    }
}