package com.inno.dao;

import com.inno.pojo.User;

/**
 * @author Timofey Yakimov
 */
public interface UserDao {

    User getUserByLoginAndPassword(String login, String password);
    boolean addUser(User user);
    void createTable();
}
