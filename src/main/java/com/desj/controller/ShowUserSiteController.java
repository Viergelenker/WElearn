package com.desj.controller;

import com.desj.SopraApplication;
import com.desj.model.MCQuestionRepository;
import com.desj.model.User;
import com.desj.service.LearningGroupService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Julien on 14.06.16.
 */
@Controller
public class ShowUserSiteController {

    @Autowired
    private LearningGroupService learningGroupService;

    @Autowired
    private UserService userService;

    @Autowired
    MCQuestionRepository mcQuestionRepository;

    @RequestMapping("/showUserSite")
    public String showUserSite(Model model) {

        User currentUser = userService.getCurrentDesjUser();

        model.addAttribute("user", currentUser);

        model.addAttribute("learningGroupsUserHasCreated", learningGroupService
                .getAllLearningGroupsUserHasCreated(userService.getCurrentDesjUser()));

        model.addAttribute("hasPic", false);

        // File upload stuff
        File rootFolder = new File(SopraApplication.ROOT);
        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
                .map(f -> f.getName())
                .collect(Collectors.toList());

        model.addAttribute("applicationPath", rootFolder.getPath());

        if (fileNames.contains(currentUser.getId().toString() + ".jpg")) {

            for (String file : fileNames) {
                if (file.equals(currentUser.getId().toString() + ".jpg")) {
                    model.addAttribute("file", file);
                }
            }
            model.addAttribute("hasPic", true);
        }

        return "ShowUserSite";
    }

    @RequestMapping(value = "/deleteLearningGroup")
    @Transactional
    public String deleteLearningGroup(@RequestParam(value = "learningGroupId") Integer learningGroupId) {

        learningGroupService.delete(learningGroupId);
        return "redirect:/showUserSite";
    }

    @RequestMapping(value = "/deleteUserProfile")
    @Transactional
    public String deleteUserProfile() {

        userService.delete(userService.getCurrentDesjUser());

        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/profilePicUpload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("name") String name,
                                   @RequestParam("fileExtension") String fileExtension,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam("userId") Integer userId,
                                   RedirectAttributes redirectAttributes) {

        fileExtension = fileExtension.replace(",", "");
        String fileName = name + fileExtension;

        // TODO: Add attribute message to html file
        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
            return "redirect:/showUserSite";
        }
        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
            return "redirect:/showUserSite";
        }

        if (!file.isEmpty()) {
            try {

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(SopraApplication.ROOT + "/" + fileName)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();

                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + name + "!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("message",
                        "You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "You failed to upload " + name + " because the file was empty");
        }

        return "redirect:/showUserSite";
    }

}
