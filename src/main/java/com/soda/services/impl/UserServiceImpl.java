package com.soda.services.impl;

import com.soda.config.jwt.JwtProvider;
import com.soda.model.User;
import com.soda.repository.UserRepository;
import com.soda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwtToken) {
        String email = jwtProvider.getEmailFromJwtToken(jwtToken);
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found.");
        }
        return user;
    }
}
