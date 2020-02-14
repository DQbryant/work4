package com.dq.work4.controller;

import com.dq.work4.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 *
 */
@Controller
public class LoginController {
    @Autowired
    LoginService loginServiceImpl;
    @ResponseBody
    @RequestMapping("/user/login")
    public ResponseEntity login(String username, String password, HttpSession session){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        String simpleHash = new SimpleHash("MD5",password,username,1).toString();
        //封装用户的登录数据
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,simpleHash);
        //执行登录方法
        subject.login(usernamePasswordToken);
        Logger logger = LoggerFactory.getLogger(LoginController.class);
        logger.info(username+"登录了");
        session.setAttribute("user",loginServiceImpl.login(username));
        return new ResponseEntity(HttpStatus.OK);
    }
}
