package com.shopsphere.service;

import com.shopsphere.dao.UserDAO;
import com.shopsphere.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public boolean register(User user) {

        if (user.getName() == null || user.getName().isBlank()) {
            return false;
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            return false;
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            return false;
        }

        if (userDAO.emailExists(user.getEmail())) {
            return false;
        }

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        user.setPassword(hashedPassword);

        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("CUSTOMER");
        }

        return userDAO.register(user);
    }

    public User login(String email, String password) {

        if (email == null || email.isBlank() || password == null || password.isEmpty()) {
            return null;
        }

        User user = userDAO.findByEmail(email.trim());

        if (user == null || user.getPassword() == null) {
            return null;
        }

        boolean passwordMatches;
        try {
            passwordMatches = BCrypt.checkpw(password, user.getPassword());
        } catch (IllegalArgumentException e) {
            return null;
        }

        if (passwordMatches) {
            user.setPassword(null);
            return user;
        }

        return null;
    }
}