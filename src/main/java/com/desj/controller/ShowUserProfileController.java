package com.desj.controller;

import com.desj.SopraApplication;
import com.desj.model.User;
import com.desj.model.UserRepository;
import com.desj.service.LearningGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Julien on 27.06.16.
 */
@Controller
public class ShowUserProfileController {

    @Autowired
    private LearningGroupService learningGroupService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/showUserProfile")
    public String showUserSite(@RequestParam("userId") Integer userId, Model model) {

        User selectedUser = userRepository.findOne(userId);

        model.addAttribute("user", selectedUser);

        model.addAttribute("learningGroupsOfUser", learningGroupService.getAllLearningGroupsOfUser(selectedUser));

        model.addAttribute("hasPic", false);

        // File upload stuff
        File rootFolder = new File(SopraApplication.ROOT);
        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
                .map(f -> f.getName())
                .collect(Collectors.toList());

        model.addAttribute("applicationPath", rootFolder.getPath());

        if (fileNames.contains(userId.toString() + ".jpg")) {

            for (String file : fileNames) {
                if (file.equals(userId.toString() + ".jpg")) {
                    model.addAttribute("file", file);
                }
            }
            model.addAttribute("hasPic", true);
        }

        return "ShowUserProfile";
    }
}

