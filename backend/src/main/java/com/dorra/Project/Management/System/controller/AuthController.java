package com.dorra.Project.Management.System.controller;

import com.dorra.Project.Management.System.Config.JwtProvider;
import com.dorra.Project.Management.System.Repository.UserRepository;
import com.dorra.Project.Management.System.modal.User;
import com.dorra.Project.Management.System.request.LoginRequest;
import com.dorra.Project.Management.System.response.AuthResponse;
import com.dorra.Project.Management.System.service.CustomeUserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomeUserDetailsImpl customeUserDetailsImpl;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws Exception{
        User isUserExist=userRepository.findByEmail(user.getEmail());
        if(isUserExist!=null){
            throw new Exception("email already exist with another account");
        }
        User createdUser =new User();
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());

        User savedUser =userRepository.save(createdUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtProvider.generateToken(authentication);

        AuthResponse res=new AuthResponse();
        res.setMessage("signup success");
        res.setJwt(jwt);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse>signin(@RequestBody LoginRequest loginRequest ){

        String username=loginRequest.getEmail();
        String password=loginRequest.getPassword();

        Authentication authentication=new UsernamePasswordAuthenticationToken(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt =JwtProvider.generateToken(authentication);

        AuthResponse res =new AuthResponse();
        res.setMessage("signup success");
        res.setJwt(jwt);

        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }
    private Authentication authenticate (String username ,String password){
        UserDetails userDetails= customeUserDetailsImpl.loadUserByUsername(username);
        if(userDetails==null){
            throw new BadCredentialsException(("invalid username"));
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
