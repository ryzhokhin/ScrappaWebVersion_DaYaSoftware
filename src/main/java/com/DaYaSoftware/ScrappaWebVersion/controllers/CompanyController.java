package com.DaYaSoftware.ScrappaWebVersion.controllers;

import com.DaYaSoftware.ScrappaWebVersion.services.SessionListener;
import com.DaYaSoftware.ScrappaWebVersion.models.User;
import com.DaYaSoftware.ScrappaWebVersion.repositories.UserRepository;
import com.DaYaSoftware.ScrappaWebVersion.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class CompanyController {
    private final UserRepository userRepository;

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("title", "mainPage");
        return "mainPage";
    }

    @GetMapping("/dashboard")
    public String dash(Principal principal, Model model){
        model.addAttribute("title", "Dashboard");
//        System.out.println(user);
        User sessionUser = getUserByPrincipal(principal);
//        System.out.println("*********** USER IS: " + sessionUser.getName() + " ***********");
//        System.out.println("*********** USER IS: " + sessionUser.getRoles() + " ***********");
//        System.out.println("*********** AT THE TIME: " + LocalDateTime.now() + " ***********");
//        System.out.println("*********** USER SUBTYPE IS: " + sessionUser.getSubscriptionTypes() + " ***********");
        model.addAttribute("userName", sessionUser.getName());

        return "dashboard";
    }

    public User getUserByPrincipal(Principal principal){
        return userRepository.findByEmail(principal.getName());
    }


}








