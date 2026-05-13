package com.contentflow.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contentflow.model.User;
import com.contentflow.service.AuthService;

@Controller
public class AuthController {

    AuthService authService =
            new AuthService();

    // =========================
    // SIGNUP PAGE
    // =========================

    @GetMapping("/signup")
    public String signupPage() {

        return "signup";
    }

    // =========================
    // REGISTER USER
    // =========================

    @PostMapping("/register")
    public String registerUser(

            @RequestParam String name,

            @RequestParam String email,

            @RequestParam String password,

            Model model) {

        User user = new User();

        user.setName(name);

        user.setEmail(email);

        user.setPassword(password);

        boolean status =
                authService.register(user);

        if(status) {

            return "redirect:/login";

        } else {

            model.addAttribute(
                    "error",
                    "Registration Failed!"
            );

            return "signup";
        }
    }

    // =========================
    // LOGIN PAGE
    // =========================

    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    // =========================
    // LOGIN USER
    // =========================

    @PostMapping("/login")
    public String loginUser(

            @RequestParam String email,

            @RequestParam String password,

            HttpSession session,

            Model model) {

        boolean userExists =
                authService.userExists(email);

        // USER NOT REGISTERED

        if(!userExists) {

            model.addAttribute(
                    "error",
                    "User not found. Please signup first."
            );

            return "login";
        }

        // VALIDATE LOGIN

        User user =
                authService.login(email, password);

        // SUCCESS LOGIN

        if(user != null) {

            session.setAttribute(
                    "loggedInUser",
                    user
            );

            return "redirect:/dashboard";
        }

        // WRONG PASSWORD

        model.addAttribute(
                "error",
                "Invalid Password!"
        );

        return "login";
    }

    // =========================
    // LOGOUT
    // =========================

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}