package com.desj.controller;

import com.desj.model.MCTest;
import com.desj.model.MCTestRepository;
import com.desj.service.MCTestService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Desi on 6/15/2016.
 */
@Controller
public class CreateMCTestController {
    @Autowired
    private UserService userService;
    @Autowired
    private MCTestRepository mcTestRepository;
    @Autowired
    private MCTestService mcTestService;


    @RequestMapping(value = "/createMCTest{id}", method = RequestMethod.GET)
    public String showCreateMCTest(@RequestParam(value = "id", required = false) Integer id, Model model) {
        model.addAttribute("ussername", userService.getCurrentDesjUser().getId());
        model.addAttribute("newlyCreatedMCTestId", id);
        if (id != null) {
            model.addAttribute("createdMCTest", mcTestRepository.findOne(id));
        }
        model.addAttribute("mcTest", new MCTest());
        return "CreateMCTest";
    }
//warning !!!! null value!!
    @RequestMapping(value = "/createNewMCTest", method = RequestMethod.POST)
    public String createNewMCTest(@ModelAttribute("createdMCTest") MCTest mcTest) {
        mcTestService.save(mcTest, userService.getCurrentDesjUser(), null);
        return "redirect://createMCTest?id=" + mcTest.getId().toString();
    }
}
