package ru.itis.homework_2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.homework_2.dto.CommentDto;
import ru.itis.homework_2.dto.CommentForm;
import ru.itis.homework_2.models.*;
import ru.itis.homework_2.repository.*;
import ru.itis.homework_2.services.CommentService;

import java.util.List;
import java.util.Optional;

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
    public CommentDto addComment(Long articleId, Long authorId, CommentForm commentForm) {
        Optional<Article> article = articleRepository.findById(articleId);
        Optional<User> author = usersRepository.findById(authorId);

        if (!article.isPresent() || !author.isPresent()) {
            throw new RuntimeException("Article or author not found");
        }

        Comment newComment = Comment.builder()
                .text(commentForm.getText())
                .article(article.get())
                .author(author.get())
                .build();

        commentRepository.save(newComment);
        return CommentDto.from(newComment);
    }
}