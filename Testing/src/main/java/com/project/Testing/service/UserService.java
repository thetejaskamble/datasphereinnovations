package com.project.Testing.service;

import com.project.Testing.model.User;

public interface UserService {
    User registerUser(User user) throws Exception;
    User findByEmail(String email);

    String getString();
    void removeSessionMessage();

//    void login(User user) throws Exception;
}