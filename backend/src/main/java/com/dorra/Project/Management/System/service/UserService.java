package com.dorra.Project.Management.System.service;

import com.dorra.Project.Management.System.modal.User;

public interface UserService {
    User findUserProfileByJwt(String jwt)throws Exception;
    User findUSerByEmail(String email)throws Exception;
    User findUserById(Long userId)throws Exception;

    User updateUserByProjectSize(User user , int number)throws Exception;
}

