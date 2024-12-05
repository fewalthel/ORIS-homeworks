package org.example.orissemwork;

import org.example.orissemwork.db.*;
import org.example.orissemwork.model.*;

public class Main {
    public static void main(String[] args) {
        User user = UserDAO.getById(31);
        Answer answer = AnswerDAO.getById(16);
        Rating rating = RatingDAO.getByIdOfUser(user, answer);

        print(rating);
    }

    public static void print(User user) {
        System.out.println("Id: " + user.getId());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Role: " + user.getRole());
    }

    public static void print(Question question) {
        System.out.println("Id: " + question.getId());
        System.out.println("Titile: " + question.getTitle());
        System.out.println("Description: " + question.getDescription());
        System.out.println("Username of author: " + question.getAuthor().getUsername());
        System.out.println("Category: " + question.getCategory().getName());
    }

    public static void print(Answer answer) {
        System.out.println("Id: " + answer.getId());
        System.out.println("Titile of question: " + answer.getQuestion().getTitle());
        System.out.println("Content: " + answer.getContent());
        System.out.println("Username of author: " + answer.getAuthor().getUsername());
    }

    public static void print(Category category) {
        System.out.println("Id: " + category.getId());
        System.out.println("Name of category: " + category.getName());
    }

    public static void print(Rating rating) {
        System.out.println("Content of answer: " + rating.getAnswer().getContent());
        System.out.println("Username of author: " + rating.getAuthor().getUsername());
        System.out.println("Liked: " + rating.getLiked());
    }
}
