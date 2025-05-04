package ru.itis.homework_2.services;

import ru.itis.homework_2.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> getAllUsers();

    UserDto getByUsername(String username);
}
