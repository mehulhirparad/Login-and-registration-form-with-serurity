package com.login.registration.dao;
import com.login.registration.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
    void save(User user);
    
}
