package com.contentflow.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.contentflow.model.ContentItem;
import com.contentflow.model.User;
import com.contentflow.service.ContentService;

@Controller
public class ContentController {

    ContentService contentService =
            new ContentService();

    // OPEN CONTENT PAGE

    @GetMapping("/content/{workspaceId}")
    public String contentPage(

            @PathVariable int workspaceId,

            HttpSession session,

            Model model) {

        User user =
                (User) session.getAttribute(
                        "loggedInUser");

        if(user == null) {

            return "redirect:/login";
        }

        model.addAttribute(
                "workspaceId",
                workspaceId);

        return "content";
    }

    // GENERATE CONTENT

    @PostMapping("/content/generate")
    @ResponseBody
    public String generateContent(

            @RequestParam int workspaceId,

            @RequestParam String platform,

            @RequestParam String brandName,

            @RequestParam String brandDetails,

            @RequestParam String audience,

            @RequestParam String tone,

            @RequestParam String goal) {

        ContentItem item =
                new ContentItem();

        item.setWorkspaceId(
                workspaceId);

        item.setPlatform(platform);

        item.setBrandName(
                brandName);

        item.setBrandDetails(
                brandDetails);

        item.setTargetAudience(
                audience);

        item.setTone(tone);

        item.setContentGoal(goal);

        String response =
                contentService
                        .generateContent(item);

        return response;
    }
}