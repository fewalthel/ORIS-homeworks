package ru.itis.homework_2.services;

import ru.itis.homework_2.dto.CommentDto;
import ru.itis.homework_2.dto.CommentForm;

import java.util.List;

public interface CommentService {
    List<CommentDto> getCommentsByArticle(Long articleId);
    CommentDto addComment(Long articleId, Long authorId, CommentForm commentForm);
}
