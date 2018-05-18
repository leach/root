package com.anythy.app.controller;

import com.anythy.app.annotation.DataSource;
import com.anythy.app.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/find1")
    public List<Map<String, Object>> findAllWithDefault(){
        return userMapper.findAll();
    }

    @RequestMapping("/find2")
    @DataSource("test1")
    public List<Map<String, Object>> findAllWithAnother(){
        return userMapper.findAll();
    }

    @RequestMapping("/find3")
    public List<Map<String, Object>> findAll3(){
        return userMapper.findAll2();
    }
}
