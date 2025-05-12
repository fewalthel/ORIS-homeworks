package ru.itis.homework_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.homework_3.models.Article;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM User u JOIN u.favoritesArticles a WHERE u.accountId = :userId")
    List<Article> findFavoritesArticlesByUserId(@Param("userId") Long userId);
}