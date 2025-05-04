package ru.itis.homework_2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.homework_2.models.Article;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String name;
    private String text;
    private Long authorId;
    private String authorEmail;

    public static ArticleDto from(Article article) {
        return ArticleDto.builder()
                .id(article.getArticleId())
                .name(article.getName())
                .text(article.getText())
                .authorId(article.getAuthor().getAccountId())
                .authorEmail(article.getAuthor().getEmail())
                .build();
    }

    public static List<ArticleDto> articleList(List<Article> articles) {
        return articles.stream().map(ArticleDto::from).collect(Collectors.toList());
    }
}