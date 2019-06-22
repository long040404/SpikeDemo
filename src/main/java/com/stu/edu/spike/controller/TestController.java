/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.stu.edu.spike.controller;

import com.stu.edu.spike.domain.User;
import com.stu.edu.spike.result.Result;
import com.stu.edu.spike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping("/thymeleaf")
    public String thymeleaft(Model model){
        model.addAttribute("name","yunlong");
        return "Hello";
    }

    @RequestMapping("/testDb")
    @ResponseBody
    public Result<User> getUserByid(){
        return Result.success(userService.getUser(0));
    }
}
