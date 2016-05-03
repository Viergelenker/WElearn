package com.desj.controller;

import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Desi on 5/2/2016.
 */
@Controller
public class AboutController {

    @RequestMapping("/About")
    public String about(){

        return "About";

    }


}
