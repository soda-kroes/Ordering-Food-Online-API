package com.soda.services;

import com.soda.model.User;

public interface UserService {
    User findUserByJwtToken(String jwtToken);

    User findUserByEmail(String email) throws Exception;
}
