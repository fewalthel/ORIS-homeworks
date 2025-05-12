package ru.itis.homework_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.homework_3.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByConfirmCode(String confirmCode);
    boolean existsByAccountIdAndFavoritesArticlesArticleId(Long userId, Long articleId);
}