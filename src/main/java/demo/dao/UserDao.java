package demo.dao;

import demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserDao {

    List<User> getUserList();

    int insertUser(User user);
}
