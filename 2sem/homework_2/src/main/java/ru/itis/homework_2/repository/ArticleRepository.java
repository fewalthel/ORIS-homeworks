package ru.itis.homework_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.homework_2.models.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}