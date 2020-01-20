package com.mp.controller;

import com.alibaba.fastjson.JSONObject;
import com.mp.util.JsonResult;
import com.mp.util.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@Controller
public class AuthorizeController {
    @Autowired
    private JwtHelper jwtHelper;
    @PostMapping("/auth/login")
    public String login(String loginName, String password, Model model) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("loginName", loginName);
        JSONObject jsonObject;
        if ("1".equals(password)) {

            jsonObject=JsonResult.success(jwtHelper.generateToken(claims));
        } else {
            jsonObject=JsonResult.fail("登录帐号或者登录密码错误");
        }
        model.addAttribute("jsonObject",jsonObject);
        return "index";
    }
    @GetMapping("/loginPre")
    public String getLoginPre(){
        System.out.println("=========================");
        return "login";
    }
}
