package ru.itis.homework_2.dto;

import lombok.*;
import ru.itis.homework_2.models.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long commentId;
    private String text;
    private Long authorId;
    private String authorEmail;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getCommentId())
                .text(comment.getText())
                .authorId(comment.getAuthor().getAccountId())
                .authorEmail(comment.getAuthor().getEmail())
                .build();
    }

    public static List<CommentDto> commentList(List<Comment> comments) {
        return comments.stream().map(CommentDto::from).collect(Collectors.toList());
    }
}