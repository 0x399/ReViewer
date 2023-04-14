package com.example.reviewer.service;

import com.example.reviewer.model.User;

import java.util.List;

public interface UserServiceInterface {
    public void createUser(User user);
    public List<User> getAll();
    public List<User> deleteUser(User user);
    public User update(User user);
    public User findByEmail(String email);
}
