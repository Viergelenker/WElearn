package com.desj.controller;

import com.desj.SopraApplication;
import com.desj.model.*;
import com.desj.service.CommentService;
import com.desj.service.GroupPostService;
import com.desj.service.LearningGroupService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Julien on 24.05.16.
 */
@Controller
public class ShowLearningGroupController {

    @Autowired
    UserService userService;

    @Autowired
    private LearningGroupRepository learningGroupRepository;

    @Autowired
    private LearningGroupService learningGroupService;

    @Autowired
    private GroupPostRepository groupPostRepository;

    @Autowired
    private GroupPostService groupPostService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;



    @RequestMapping("/showLearningGroup{id}")
    public String showLearningGroup(@RequestParam(value = "id") Integer learningGroupId, Model model) {

        model.addAttribute("username", userService.getCurrentDesjUser().getUsername());
        model.addAttribute("learningGroup", learningGroupRepository.findOne(learningGroupId));
        model.addAttribute("learningGroupMembers", learningGroupService.getAllMemberOfLearningGroup(learningGroupId));
        model.addAttribute("newGroupPost", new GroupPost());
        model.addAttribute("comment", new Comment());
        model.addAttribute("groupPosts", groupPostService.getAllGroupPostsOfLearningGroup(learningGroupRepository
                .findOne(learningGroupId)));
        model.addAttribute("comments", commentRepository.findAll());

        // File upload stuff
        File rootFolder = new File(SopraApplication.ROOT);
        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
                .map(f -> f.getName())
                .collect(Collectors.toList());

        model.addAttribute("applicationPath", rootFolder.getPath());

        model.addAttribute("files",
                Arrays.stream(rootFolder.listFiles())
                        .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
                        .map(f -> f.getName())
                        .collect(Collectors.toList())
        );

        return "ShowLearningGroup";
    }

    @RequestMapping(value = "/newGroupPost{learningGroupId}", method = RequestMethod.POST)
    public String writeNewGroupPost(@RequestParam(value = "learningGroupId") Integer learningGroupId,
                                    @ModelAttribute("groupPost") GroupPost groupPost) {

        groupPostService.save(groupPost, userService.getCurrentDesjUser(), learningGroupRepository
                .findOne(learningGroupId));

        return "redirect:/showLearningGroup?id=" + learningGroupId.toString();
    }

    @RequestMapping(value = "/newComment{groupPostId}", method = RequestMethod.POST)
    public String writeNewComment(@RequestParam(value = "groupPostId") Integer groupPostId,
                                    @ModelAttribute("comment") Comment comment) {

        commentService.save(comment, userService.getCurrentDesjUser(), groupPostRepository.findOne(groupPostId));
        groupPostService.addGroupPostComment(groupPostRepository.findOne(groupPostId), comment);
        String redirectString = groupPostRepository.findOne(groupPostId).getAssociatedLearningGroup().getId().toString();
        return "redirect:/showLearningGroup?id=" + redirectString;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/fileUpload")
    public String handleFileUpload(@RequestParam("name") String name,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam("learningGroupId") Integer learningGroupId,
                                   RedirectAttributes redirectAttributes) {
        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
            return "redirect:/showLearningGroup?id=" + learningGroupId.toString();
        }
        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
            return "redirect:/showLearningGroup?id=" + learningGroupId.toString();
        }

        if (!file.isEmpty()) {
            try {

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(SopraApplication.ROOT + "/" + name)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + name + "!");
            }
            catch (Exception e) {
                redirectAttributes.addFlashAttribute("message",
                        "You failed to upload " + name + " => " + e.getMessage());
            }
        }
        else {
            redirectAttributes.addFlashAttribute("message",
                    "You failed to upload " + name + " because the file was empty");
        }

        return "redirect:/showLearningGroup?id=" + learningGroupId.toString();
    }

}
