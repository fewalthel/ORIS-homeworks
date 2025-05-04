package ru.itis.homework_2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.homework_2.dto.ArticleForm;
import ru.itis.homework_2.dto.CommentForm;
import ru.itis.homework_2.security.details.UserDetailsImpl;
import ru.itis.homework_2.services.ArticleService;
import ru.itis.homework_2.services.CommentService;
import ru.itis.homework_2.services.impl.ProfanityFilterServiceImpl;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProfanityFilterServiceImpl profanityFilterService;

    @PostMapping("/articles")
    //оставить публикацию от имени пользователя с каким-то id
    public String addArticle(@ModelAttribute ArticleForm articleForm,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long currentUserId = userDetails.getUser().getAccountId();

        articleService.addArticle(currentUserId, articleForm);
        return "redirect:/articles";
    }

    @GetMapping("/articles")
    //просмотр вообще всех публикаций
    public String getAllArticles(Model model,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long currentUserId = userDetails.getUser().getAccountId();

        model.addAttribute("articles", articleService.getAllArticles());
        model.addAttribute("favoritesArticles", articleService.getFavoritesArticles(currentUserId));
        model.addAttribute("userId", currentUserId);
        return "articles_page";
    }

    @GetMapping("/article/{article-id}")
    public String getArticle(@PathVariable("article-id") Long articleId, Model model,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long currentUserId = userDetails.getUser().getAccountId();

        model.addAttribute("article", articleService.getById(articleId));
        model.addAttribute("comments", commentService.getCommentsByArticle(articleId));
        model.addAttribute("commentForm", new CommentForm());
        model.addAttribute("currentUserId", currentUserId);
        return "article_detail";
    }

    @PostMapping("/article/{article-id}/comments")
    //оставить комментарий от имени пользователя с каким-то id
    public String addComment(@PathVariable("article-id") Long articleId,
                             @ModelAttribute CommentForm commentForm,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (profanityFilterService.containsProfanity(commentForm.getText())) {
            redirectAttributes.addFlashAttribute("error", "The comment will not be published because it contains bad words:(");
            return "redirect:/article/" + articleId;
        }

        Long currentUserId = userDetails.getUser().getAccountId();
        commentService.addComment(articleId, currentUserId, commentForm);
        return "redirect:/article/" + articleId;
    }

    @PostMapping("/favourites_articles/{article-id}")
    public String updateFavorites(@PathVariable("article-id") Long articleId,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long currentUserId = userDetails.getUser().getAccountId();

        if (articleService.inFavorites(articleId, currentUserId)) {
            articleService.removeFromFavorites(articleId, currentUserId);
        } else {
            articleService.saveToFavorites(articleId, currentUserId);
        }
        return "redirect:/articles";
    }
}