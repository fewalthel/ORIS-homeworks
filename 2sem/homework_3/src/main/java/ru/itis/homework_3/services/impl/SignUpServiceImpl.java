package ru.itis.homework_3.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.homework_3.dto.UserForm;
import ru.itis.homework_3.models.Role;
import ru.itis.homework_3.models.User;
import ru.itis.homework_3.repository.UsersRepository;
import ru.itis.homework_3.services.MailService;
import ru.itis.homework_3.services.SignUpService;

import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    @Override
    public void addUser(UserForm userForm) {
        User user = User.builder()
                .email(userForm.getEmail())
                .username(userForm.getUsername())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .role(Role.USER)
                .confirmCode(UUID.randomUUID().toString())
                .confirmed("false")
                .build();

        mailService.sendEmailForConfirm(user.getEmail(), user.getConfirmCode());
        usersRepository.save(user);
    }
}