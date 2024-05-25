package com.dorra.Project.Management.System.service;

import com.dorra.Project.Management.System.Config.JwtProvider;
import com.dorra.Project.Management.System.Repository.UserRepository;
import com.dorra.Project.Management.System.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        // Debugging: Log the JWT token
        System.out.println("JWT Token in service: " + jwt);
        String email= JwtProvider.getEmailFromToken(jwt);
        return findUSerByEmail(email);
    }

    @Override
    public User findUSerByEmail(String email) throws Exception {
        User user =userRepository.findByEmail(email);
        if (user==null){
            throw new Exception("user not found");
        }
        return user;
    }


    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User>optionUser=userRepository.findById(userId);
        if(optionUser.isEmpty()) {
            throw new Exception("user not found");
        }
        return optionUser.get();
    }

    @Override
    public User updateUserByProjectSize(User user, int number) throws Exception {
        user.setProjectSize(user.getProjectSize()+number);
        return userRepository.save(user);
    }


}

