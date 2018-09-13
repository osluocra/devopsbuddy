package com.devopsbuddy.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PayloadController {
    public static final String PAYLOAD_VIEW="payload/payload";

    @RequestMapping(value = "/payload", method = RequestMethod.GET)
    public String payload(){
        return PAYLOAD_VIEW;
    }
}
