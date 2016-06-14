package com.desj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Desi on 5/2/2016.
 */
@Controller
public class AboutNotLogController {


    @RequestMapping("/aboutNotLog")
    public String about(){

        return "AboutNotLog";
    }
}
