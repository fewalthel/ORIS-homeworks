package ru.itis.homework_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.homework_3.models.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticle_ArticleId(Long articleId);
}
