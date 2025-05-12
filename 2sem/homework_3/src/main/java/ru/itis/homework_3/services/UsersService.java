package ru.itis.homework_3.services;

import ru.itis.homework_3.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> getAllUsers();

    boolean confirmUser(String code);
}
