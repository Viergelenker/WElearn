package com.desj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Julien on 01.06.16.
 */
@Controller
public class SignUpSuccessController {

    @RequestMapping("/signUpSuccess")
    public String signUpSuccess() {
        return "SignUpSuccess";
    }
}
