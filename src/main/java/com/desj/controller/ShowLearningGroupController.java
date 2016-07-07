package com.desj.controller;

import com.desj.SopraApplication;
import com.desj.model.*;
import com.desj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    @Autowired
    private MCQuestionService mcQuestionService;
    @Autowired
    private QuestionReposiory questionReposiory;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionCommentService questionCommentService;


    @RequestMapping("/showLearningGroup")
    public String showLearningGroup(@RequestParam(value = "id") Integer learningGroupId, Model model) {

        // Make sure the current user is a member of the actual group
        if (learningGroupRepository.findOne(learningGroupId).getMembers().contains(userService.getCurrentDesjUser())) {
            model.addAttribute("user", userService.getCurrentDesjUser());
            model.addAttribute("username", userService.getCurrentDesjUser().getUsername());
            model.addAttribute("learningGroup", learningGroupRepository.findOne(learningGroupId));
            model.addAttribute("learningGroupMembers", learningGroupService.getAllMemberOfLearningGroup(learningGroupId));
            model.addAttribute("newGroupPost", new GroupPost());
            model.addAttribute("comment", new Comment());
            model.addAttribute("groupPosts", groupPostService.getAllGroupPostsOfLearningGroup(learningGroupRepository
                    .findOne(learningGroupId)));
            model.addAttribute("comments", commentRepository.findAll());
            model.addAttribute("question", new Question());
            model.addAttribute("mcQuestion", new MCQuestion());
            model.addAttribute("questionComment", new QuestionComment());
            model.addAttribute("questions", questionService.getAllQuestionsOfLearningGroup(learningGroupRepository
                    .findOne(learningGroupId)));
            model.addAttribute("questionComments", questionCommentService.getAllQuestionCommentsOfLearningGroup(
                    learningGroupRepository.findOne(learningGroupId)));
            model.addAttribute("fileNames", learningGroupRepository.findOne(learningGroupId).getUploadedFilesList());
            Question question = questionService.getQuestion(learningGroupRepository.findOne(learningGroupId),userService.getCurrentDesjUser());
            model.addAttribute("error",false);
            if(question!=null){
                model.addAttribute("getQuestion",question);
            }else{

                model.addAttribute("error",true);
            }

            if (!userService.getAllQuizOfUser(userService.getCurrentDesjUser()).isEmpty()) {
                model.addAttribute("quizPoints", userService.getTotalOfQuizPointsForUserForLearningGroup(userService
                        .getCurrentDesjUser(), learningGroupRepository.findOne(learningGroupId)));
            }

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
        return "NotAMember";
    }

    @RequestMapping(value = "/newGroupPost", method = RequestMethod.POST)
    public String writeNewGroupPost(@RequestParam(value = "learningGroupId") Integer learningGroupId,
                                    @Valid
                                    @ModelAttribute("groupPost") GroupPost groupPost, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/showLearningGroup?id=" + learningGroupId.toString();
        } else {
            groupPostService.save(groupPost, userService.getCurrentDesjUser(), learningGroupRepository
                    .findOne(learningGroupId));

            return "redirect:/showLearningGroup?id=" + learningGroupId.toString();
        }
    }

    @RequestMapping(value = "/newComment", method = RequestMethod.POST)
    public String writeNewComment(@RequestParam(value = "groupPostId") Integer groupPostId,
                                  @Valid @ModelAttribute("comment") Comment comment, BindingResult bindingResult) {

        String redirectString = groupPostRepository.findOne(groupPostId).getAssociatedLearningGroup().getId().toString();

        if (bindingResult.hasErrors()) {
            return "redirect:/showLearningGroup?id=" + redirectString;
        } else {

            commentService.save(comment, userService.getCurrentDesjUser(), groupPostRepository.findOne(groupPostId));
            groupPostService.addGroupPostComment(groupPostRepository.findOne(groupPostId), comment);

            return "redirect:/showLearningGroup?id=" + redirectString;
        }
    }

    @RequestMapping(value = "/newMCQuestion", method = RequestMethod.POST)
    public String whriteNewMCQuestion(@RequestParam(value = "learningGroupId") Integer learningGroupId,
                                      @ModelAttribute("mcQuestion") MCQuestion mcQuestion) {
        User user = userService.getCurrentDesjUser();
        mcQuestionService.save(mcQuestion, learningGroupRepository.findOne(learningGroupId), user);
        user.createMCQuestion(mcQuestion);

        return "redirect:/showLearningGroup?id=" + learningGroupId.toString();
    }

    @RequestMapping(value = "/newQuestion", method = RequestMethod.POST)
    public String whriteNewQuestion(@RequestParam(value = "learningGroupId") Integer learningGroupId,
                                    @ModelAttribute("question") Question question) {
        User user = userService.getCurrentDesjUser();
        questionService.save(question, learningGroupRepository.findOne(learningGroupId), user);
        user.createQuestion(question);

        return "redirect:/showLearningGroup?id=" + learningGroupId.toString();
    }

    @RequestMapping(value = "/newQuestionComment", method = RequestMethod.POST)
    public String writeNewQuestionComment(@RequestParam(value = "questionId") Integer questionId,
                                          @ModelAttribute("questionComment") QuestionComment questionComment) {
        String redirectString = questionReposiory.getOne(questionId).getCorrespondingLearningGroup().getId().toString();

        questionCommentService.save(questionComment, userService.getCurrentDesjUser(),
                questionReposiory.findOne(questionId));
        questionService.addQuestionComment(questionReposiory.findOne(questionId), questionComment);
        return "redirect:/showLearningGroup?id=" + redirectString;
    }

    @RequestMapping(value = "/deleteGroupPost")
    public String deleteGroupPost(@RequestParam(value = "groupPostId") Integer groupPostId,
                                  @RequestParam(value = "learningGroupId") Integer learningGroupId) {

        for (Comment comment : commentRepository.findAll()) {
            if (comment.getAssociatedGroupPost().getId() == groupPostId) {
                commentRepository.delete(comment);
            }
        }

        groupPostRepository.delete(groupPostRepository.findOne(groupPostId));
        return "redirect:/showLearningGroup?id=" + learningGroupId;
    }

    @RequestMapping(value = "/deleteComment")
    public String deleteComment(@RequestParam(value = "commentId") Integer commentId,
                                  @RequestParam(value = "learningGroupId") Integer learningGroupId) {

        commentRepository.delete(commentId);
        return "redirect:/showLearningGroup?id=" + learningGroupId;
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("name") String name,
                                   @RequestParam("fileExtension") String fileExtension,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam("learningGroupId") Integer learningGroupId,
                                   RedirectAttributes redirectAttributes) {

        fileExtension = fileExtension.replace(",", "");
        String fileName = name + fileExtension;

        // TODO: Add attribute message to html file
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
                        new FileOutputStream(new File(SopraApplication.ROOT + "/" + fileName)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                learningGroupService.addFileToLearningGroup(fileName, learningGroupId);
                GroupPost fileGroupPost = new GroupPost();
                fileGroupPost.setTitle("New file upload!");
                fileGroupPost.setText("<p><a href=\"/public/" + fileName + "\">" + fileName + "</a></p>");
                fileGroupPost.setAssociatedUser(userService.getCurrentDesjUser());
                fileGroupPost.setAssociatedLearningGroup(learningGroupRepository.findOne(learningGroupId));
                groupPostRepository.save(fileGroupPost);
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

        return "redirect:/showLearningGroup?id=" + learningGroupId.toString();
    }
}


