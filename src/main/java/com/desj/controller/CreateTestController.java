package com.desj.controller;

import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Desi on 6/16/2016.
 */
@Controller
public class CreateTestController {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestService testService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/createTest{id}", method = RequestMethod.GET)
    public String showCreateTest(@RequestParam(value = "id", required = false)Integer id,Model model){
        model.addAttribute("username", userService.getCurrentDesjUser().getId());
        model.addAttribute("newlyCreatedTest", id);
        if(id!=null){
            model.addAttribute("createdTest",testRepository.findOne(id));
        }
        model.addAttribute("newTest", new Test());
        return "/CreateTest";

    }
//warning !!!! null value
    @RequestMapping(value = "/createNewTest", method = RequestMethod.POST)
    public String createNewTest(@ModelAttribute("createdTest"  )Test test){
        testService.save(test, userService.getCurrentDesjUser(),null);
        return "redirect://createTest?id="+test.getId().toString();
    }
}
