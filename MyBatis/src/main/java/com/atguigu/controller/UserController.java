package com.atguigu.controller;

import com.atguigu.mybatis.mapper.UserMapper;
import com.atguigu.mybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: UserController
 * Package: com.atguigu.controller
 * Description:
 *
 * @Author AQiu
 * @Create 21/06/2023 22:45
 */
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/getUser")
    public User selectById(){
        return userMapper.selectById(1);
    }
}
