package com.example.demo.services.impl;

import com.example.demo.exceptions.UserCanNotBeNullException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
     final UserRepository userRepository;

     @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
@Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public boolean existByEmail(String email){
        return userRepository.existsByEmail(email);

    }
    @Override
    public Optional<User> findUserByEmail(String email)
{
    return userRepository.findByEmail(email);
}
@Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
    @Override
    public  void updateUser(Long id, User user){
        user.setId(id);
        userRepository.save(user);
    }
    @Override
    public void removeUserById(Long id){
        userRepository.deleteById(id);
    }
    @Override
    public void changeUserRole(Long id, Role role) throws UserCanNotBeNullException
    {
        User  user = userRepository.findById(id).orElse(null);
        if(user!= null){
            user.setRole(role);
            userRepository.save(user);
        }else{
            throw new UserCanNotBeNullException();
        }
    }


    @Override
    public void changePassword(String email, String newPassword) throws UserCanNotBeNullException{
        var user= userRepository.findByEmail(email).orElse(null);
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        if(user!=null){
            var encodedPassword= passwordEncoder.encode(newPassword);
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }else{
            throw new UserCanNotBeNullException();
        }
    }

    @Override
    public boolean arePasswordTheSame(String oldPassword, String newPassword){
        return oldPassword.equals(newPassword);

    }
    @Override
    public boolean isNewPasswordCorrect(String newPassword){
        var passwordPattern="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher= pattern.matcher(newPassword);
        return matcher.matches();
    }

}
