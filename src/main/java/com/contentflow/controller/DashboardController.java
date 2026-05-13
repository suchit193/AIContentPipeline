package com.contentflow.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.contentflow.model.User;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboardPage(
            HttpSession session) {

        User user =
                (User) session.getAttribute(
                        "loggedInUser");

        // IF USER NOT LOGGED IN

        if(user == null) {

            return "redirect:/login";
        }

        return "dashboard";
    }
}