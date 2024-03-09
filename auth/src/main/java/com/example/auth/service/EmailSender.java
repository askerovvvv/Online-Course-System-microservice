package com.example.auth.service;

public interface EmailSender {
    void send(String to, String content);
    String buildEmail(String name, String link);
}
