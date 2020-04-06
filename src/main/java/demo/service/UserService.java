package demo.service;

import demo.config.aspect.ReadOnly;
import demo.entity.User;

import java.util.List;

public interface UserService {

    @ReadOnly
    List<User> getUserList();

    int insertUser(User user);
}

