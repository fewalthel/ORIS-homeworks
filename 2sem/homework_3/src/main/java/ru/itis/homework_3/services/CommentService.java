package ru.itis.homework_3.services;

import ru.itis.homework_3.dto.CommentDto;
import ru.itis.homework_3.dto.CommentForm;

import java.util.List;

public interface CommentService {
    List<CommentDto> getCommentsByArticle(Long articleId);
    void addComment(Long articleId, Long authorId, CommentForm commentForm);
}
