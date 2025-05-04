package ru.itis.homework_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.homework_2.dto.ArticleDto;
import ru.itis.homework_2.models.Article;
import ru.itis.homework_2.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByAccountIdAndFavoritesArticlesArticleId(Long userId, Long articleId);

    @Query("SELECT a FROM User u JOIN u.favoritesArticles a WHERE u.accountId = :userId")
    List<Article> findFavoritesArticlesByUserId(@Param("userId") Long userId);
}