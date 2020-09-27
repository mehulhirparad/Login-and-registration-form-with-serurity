package com.login.registration.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.login.registration.entity.User;
import com.login.registration.user.RegisterUser;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(RegisterUser registerUser);
}
