package ru.itis.homework_2.services;

public interface ProfanityFilterService {
    String URL = "https://www.purgomalum.com/service/containsprofanity?text=";

    boolean containsProfanity(String text);
}