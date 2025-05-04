package ru.itis.homework_2.services;

import ru.itis.homework_2.dto.ArticleDto;
import ru.itis.homework_2.dto.ArticleForm;

import java.util.List;

public interface ArticleService {
    List<ArticleDto> getByUser(Long id);
    List<ArticleDto> getAllArticles();
    void addArticle(Long userId, ArticleForm articleForm);
    ArticleDto getById(Long articleId);
    void saveToFavorites(Long articleId, Long userId);
    void removeFromFavorites(Long articleId, Long userId);
    boolean inFavorites(Long articleId, Long userId);
    List<ArticleDto> getFavoritesArticles(Long userId);
}