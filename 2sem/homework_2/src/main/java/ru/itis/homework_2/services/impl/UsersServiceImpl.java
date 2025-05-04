package ru.itis.homework_2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.homework_2.dto.UserDto;
import ru.itis.homework_2.models.User;
import ru.itis.homework_2.repository.UsersRepository;
import ru.itis.homework_2.services.UsersService;

import java.util.List;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(usersRepository.findAll());
    }

    @Override
    public UserDto getByUsername(String username) {
        User user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserDto.from(user);
    }
}
