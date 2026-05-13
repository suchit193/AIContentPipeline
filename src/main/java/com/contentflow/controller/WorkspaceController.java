package com.contentflow.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.contentflow.model.User;
import com.contentflow.model.Workspace;
import com.contentflow.service.WorkspaceService;

@Controller
public class WorkspaceController {

    WorkspaceService workspaceService =
            new WorkspaceService();

    // OPEN WORKSPACE PAGE

    @GetMapping("/workspace")
    public String workspacePage(

            HttpSession session,

            Model model) {

        User user =
                (User) session.getAttribute(
                        "loggedInUser");

        // NOT LOGGED IN

        if(user == null) {

            return "redirect:/login";
        }

        List<Workspace> workspaceList =
                workspaceService
                        .getAllWorkspaces(
                                user.getId());

        model.addAttribute(
                "workspaceList",
                workspaceList);

        return "workspace";
    }

    // CREATE WORKSPACE

    @PostMapping("/workspace/create")
    public String createWorkspace(

            @RequestParam String name,

            @RequestParam String description,

            HttpSession session) {

        User user =
                (User) session.getAttribute(
                        "loggedInUser");

        Workspace workspace =
                new Workspace();

        workspace.setName(name);

        workspace.setDescription(description);

        workspace.setUserId(user.getId());

        workspaceService
                .createWorkspace(workspace);

        return "redirect:/workspace";
    }
}