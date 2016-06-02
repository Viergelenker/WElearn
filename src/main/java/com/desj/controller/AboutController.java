package com.desj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Desi on 5/2/2016.
 */
@Controller
public class AboutController {

    @RequestMapping("/about")
    public String about(){
        return "About";
    }
}
