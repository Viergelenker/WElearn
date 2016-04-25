package com.desj.service;

import com.desj.model.User;

/**
 * Created by Julien on 24.04.16.
 */
public interface UserService {

        Iterable<User> listAllUser();
        User getUserById(Integer id);
        User saveUser(User user);
        void deleteUser(Integer id);

}
