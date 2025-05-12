package ru.itis.homework_3.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.homework_3.dto.UserDto;
import ru.itis.homework_3.models.User;
import ru.itis.homework_3.repository.UsersRepository;
import ru.itis.homework_3.services.UsersService;

import java.util.List;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(usersRepository.findAll());
    }

    public boolean confirmUser(String confirmCode) {
        User user = usersRepository.findByConfirmCode(confirmCode)
                .orElseThrow(() -> new EntityNotFoundException("User with this confirmation code not found"));

        if (user.getConfirmed().equals("true")) {
            return false;
        }

        user.setConfirmed("true");
        user.setConfirmCode(null);
        usersRepository.save(user);
        return true;
    }
}
