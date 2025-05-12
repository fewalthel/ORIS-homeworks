package ru.itis.homework_3.services;

public interface MailService {
    void sendEmailForConfirm(String email, String code);
}