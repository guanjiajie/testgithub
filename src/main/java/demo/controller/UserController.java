package demo.controller;

import com.alibaba.fastjson.JSON;
import demo.entity.User;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserList")
    @ResponseBody
    public Object getUserList(){
        List<User> userList = userService.getUserList();
        String jsonstr = JSON.toJSON(userList).toString();
        //return JSON.toJSON(userList);
        return userList;
    }

    @PostMapping("/insertUser")
    @ResponseBody
    public String insertUser(@RequestBody User user){
        int ret = userService.insertUser(user);
        return ret+"";
    }
}
