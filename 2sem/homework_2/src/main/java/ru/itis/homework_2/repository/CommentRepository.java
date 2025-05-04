package ru.itis.homework_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.homework_2.models.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticle_ArticleId(Long articleId);
}
