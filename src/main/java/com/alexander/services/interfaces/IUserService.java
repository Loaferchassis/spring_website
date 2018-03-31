package com.alexander.services.interfaces;

import com.alexander.models.User;

public interface IUserService {
    void save(User user);

    User findByLogin(String login);
}
