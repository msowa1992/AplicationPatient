package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.demo.exceptions.UserCanNotBeNullException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.User;



public interface UserService {
    List<User> findAllUsers();
    Optional<User> findUserById(Long id);
    boolean existByEmail(String email);
    Optional<User> findUserByEmail(String email);
    void saveUser(User user);
    void updateUser(Long id, User user);
    void removeUserById(Long id);
    void changeUserRole(Long id, Role role) throws UserCanNotBeNullException;
    boolean arePasswordTheSame(String oldPassword, String newPassword);
    boolean isNewPasswordCorrect(String newPassword);
    void changePassword(String email, String newPassword) throws UserCanNotBeNullException;

}
