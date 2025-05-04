package ru.itis.homework_2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "author")
    private List<Article> createdArticles;

    @ManyToMany
    @JoinTable(
            name = "favoritesArticles",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "accountId"),
            inverseJoinColumns = @JoinColumn(name = "id_article", referencedColumnName = "articleId")
    )
    private List<Article> favoritesArticles = new ArrayList<>();
}