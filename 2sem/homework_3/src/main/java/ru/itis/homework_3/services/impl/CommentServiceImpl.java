package ru.itis.homework_3.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.homework_3.dto.CommentDto;
import ru.itis.homework_3.dto.CommentForm;
import ru.itis.homework_3.models.*;
import ru.itis.homework_3.repository.*;
import ru.itis.homework_3.services.CommentService;

import java.util.List;

@Component
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<CommentDto> getCommentsByArticle(Long articleId) {
        List<Comment> comments = commentRepository.findByArticle_ArticleId(articleId);
        return CommentDto.commentList(comments);
    }

    @Override
    public void addComment(Long articleId, Long authorId, CommentForm commentForm) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("Article not found"));
        User author = usersRepository.findById(authorId).orElseThrow(() -> new EntityNotFoundException("Author not found"));

        Comment newComment = Comment.builder()
                .text(commentForm.getText())
                .article(article)
                .author(author)
                .build();

        commentRepository.save(newComment);
    }
}