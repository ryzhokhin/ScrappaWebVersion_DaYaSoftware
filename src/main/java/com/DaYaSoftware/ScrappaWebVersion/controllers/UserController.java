package com.DaYaSoftware.ScrappaWebVersion.controllers;

import com.DaYaSoftware.ScrappaWebVersion.models.User;
import com.DaYaSoftware.ScrappaWebVersion.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

//    @PostMapping("/login")
//    public User userLoggedIn(){
//
//    }

    @GetMapping("/registration")
    public String registration(){
        System.out.println("*********** WE HAVE GOT INTO THE REGISTRATION PAGE **********");
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model){
        System.out.println("*********** CREATE_USER METHOD WAS CALLED ***********");
        if(!userService.createUser(user)){
            model.addAttribute("errorMessage", "User with email: "+ user.getEmail() + " already exists");
            return "registration";
        }
        System.out.println("*********** REDIRECT SHOULD BE COMPLETED ***********");

        return "redirect:/";
    }

    @GetMapping("/hello")
    public String securityUrl(){
        return "hello";
    }


}
