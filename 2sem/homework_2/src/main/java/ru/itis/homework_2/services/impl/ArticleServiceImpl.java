package ru.itis.homework_2.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.homework_2.dto.ArticleDto;
import ru.itis.homework_2.dto.ArticleForm;
import ru.itis.homework_2.models.Article;
import ru.itis.homework_2.models.User;
import ru.itis.homework_2.repository.ArticleRepository;
import ru.itis.homework_2.repository.UsersRepository;
import ru.itis.homework_2.services.ArticleService;

import java.util.List;
import java.util.Optional;

@Component
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<ArticleDto> getByUser(Long id) {
        Optional<User> user = usersRepository.findById(id);
        if (!user.isPresent()) {
            throw new RuntimeException("User not found");
        }
        List<Article> articlesOfUser = user.get().getCreatedArticles();
        return ArticleDto.articleList(articlesOfUser);
    }

    @Override
    public List<ArticleDto> getAllArticles() {
        return ArticleDto.articleList(articleRepository.findAll());
    }

    @Override
    public void addArticle(Long userId, ArticleForm articleForm) {
        Optional<User> author = usersRepository.findById(userId);
        if (!author.isPresent()) {
            throw new RuntimeException("Author not found");
        }

        Article newArticle = Article.builder()
                .text(articleForm.getText())
                .name(articleForm.getName())
                .author(author.get())
                .build();

        articleRepository.save(newArticle);
    }

    @Override
    public ArticleDto getById(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        return ArticleDto.from(article);
    }

    @Override
    @Transactional
    public void saveToFavorites(Long articleId, Long userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article not found"));

        if (user.getFavoritesArticles().contains(article)) {
            throw new IllegalStateException("Article already in favorites");
        }
        user.getFavoritesArticles().add(article);
        usersRepository.save(user);
    }

    @Override
    @Transactional
    public void removeFromFavorites(Long articleId, Long userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article not found"));

        if (!user.getFavoritesArticles().contains(article)) {
            throw new IllegalStateException("Article not found in favorites");
        }
        user.getFavoritesArticles().remove(article);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean inFavorites(Long articleId, Long userId) {
        return usersRepository.existsByAccountIdAndFavoritesArticlesArticleId(userId, articleId);
    }

    @Override
    public List<ArticleDto> getFavoritesArticles(Long userId) {
        List<Article> articles = usersRepository.findFavoritesArticlesByUserId(userId);
        return ArticleDto.articleList(articles);
    }
}
