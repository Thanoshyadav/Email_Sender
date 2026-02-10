package com.SpringProject.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.SpringProject.Model.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private EmailService emailService;

    public User createUser(User user) {
        Context context = new Context();
        context.setVariable("name", user.getName());
        context.setVariable("username", user.getEmail());
        context.setVariable("password", "Temp@123");

        emailService.sendEmail(
                new String[]{user.getEmail()},
                "Welcome to TechCorp - Account Created",
                "user-created",
                context
        );
        return user;
    }

    public User updateUser(User user) {
        Context context = new Context();
        context.setVariable("changes", List.of("Email updated", "Name updated"));
        context.setVariable("timestamp", LocalDateTime.now());

        emailService.sendEmail(
                new String[]{"hr@techcorp.com", user.getEmail()},
                "Account Updated - Profile Changes",
                "user-updated",
                context
        );
        return user;
    }

    public void deleteUser(User user) {
        Context context = new Context();
        context.setVariable("name", user.getName());
        context.setVariable("reason", "Left organization");

        emailService.sendEmail(
                new String[]{"hr@techcorp.com", user.getEmail()},
                "Account Deactivated - " + user.getName(),
                "user-deleted",
                context
        );
    }
}

