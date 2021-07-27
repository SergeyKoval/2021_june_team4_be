package com.exadel.discount.service;

public interface EmailService {

    void sendDiscountInfoEmail(String email, String subject, String text);
}
