package com.citiustech.userportal.service;


import javax.servlet.http.HttpServletRequest;

import com.citiustech.userportal.Model.AppUser;
import com.citiustech.userportal.config.JwtTokenProvider;
import com.citiustech.userportal.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getAppUserRoles());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username/password supplied");
        }
    }

    public String signup(AppUser appUser) {
        if (!userRepository.existsByUsername(appUser.getUsername())) {
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            userRepository.save(appUser);
            return jwtTokenProvider.createToken(appUser.getUsername(), appUser.getAppUserRoles());
        } else {
            throw new RuntimeException("Username is already in use");
        }
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    public AppUser search(String username) {
        AppUser appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            throw new RuntimeException("The user doesn't exist");
        }
        return appUser;
    }

    public AppUser whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getAppUserRoles());
    }

}
