package demo.service.impl;

import demo.config.aspect.ReadOnly;
import demo.dao.UserDao;
import demo.entity.User;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @ReadOnly
    @Override
    public List<User> getUserList() {
        List<User> userList = userDao.getUserList();
        return userList;
    }

    @Override
    public int insertUser(User user) {
        int ret = userDao.insertUser(user);
        return ret;
    }
}
