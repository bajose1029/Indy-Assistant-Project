package com.techelevator.dao;

import com.techelevator.model.User;
import java.util.List;

public interface UserDao {

    List<User> getUsers();
    List<User> getEnabledUsers(boolean enabled);
    User turnOnOrOffAccount(User user, boolean enabled);

    User getUserById(int userId);

    User getUserByUsername(String username);

    User createUser(User newUser);
}
