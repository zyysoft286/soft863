package com.soft.bootdemo.controller;

import com.soft.bootdemo.entity.User;
import com.soft.bootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
public class UserController {
//    注入服务层
    @Autowired
    UserService userService;

//    springboot默认模板是thymeleaf html文件，没有html文件所以刚才报错
    @RequestMapping(value = "/queryAllUser",method = RequestMethod.POST)
//    @RequestMapping("/queryAllUser")
//    RequestMapping默认get请求
//    修改了RequestMapping请求为post请求
    @ResponseBody
    public List<User> queryAllUser(){
        List<User> userList = userService.queryAll();
        System.out.println("获取的数据是："+userList);
        return userList;
    }


//    接收前端传递过来的账号和密码

    @RequestMapping("/getUser")
    @ResponseBody
    public List<User> getUser(String userName, String userPwd){
        System.out.println("name = "+userName+";pwd = "+userPwd);
//        通过获取的账号和密码创建对象
        User user = new User(userName,userPwd);
        List<User> users = new ArrayList<>();
        if(user!=null){
            users = userService.queryByUser(user);
        }
        return users;
    }


//    添加的方法

    @PostMapping("/insertUser")
    @ResponseBody
    public String insertUser(@RequestBody User params){
        System.out.println("获取的值是："+params);
        boolean b = false;
        if(params!=null){
             b = userService.insertUser(params);
        }
        if(b){
            return "success";
        }else{
            return "error";
        }
    }

}
