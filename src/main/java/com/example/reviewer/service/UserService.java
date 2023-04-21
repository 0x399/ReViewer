package com.example.reviewer.service;

import com.example.reviewer.model.User;
import com.example.reviewer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public List<User> deleteUser(User user){
        userRepository.delete(user);
        return getAll();
    }

    public User update(User user){
     User userFromDB = userRepository.findById(user.getId()).orElseThrow();
     userFromDB.setFirstName(user.getFirstName());
     userFromDB.setLastName(user.getLastName());
     userFromDB.setEmail(user.getEmail());
     userFromDB.setReviews(user.getReviews());
     userRepository.save(userFromDB);
     return userFromDB;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow();
    }


}
