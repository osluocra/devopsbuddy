package com.devopsbuddy.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private static final String LOGIN_VIEW_NAME="user/login";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return LOGIN_VIEW_NAME;

    }
}
