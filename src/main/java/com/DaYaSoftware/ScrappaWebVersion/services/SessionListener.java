package com.DaYaSoftware.ScrappaWebVersion.services;

import com.DaYaSoftware.ScrappaWebVersion.models.User;
import com.DaYaSoftware.ScrappaWebVersion.repositories.UserRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RequiredArgsConstructor
public class SessionListener{
    private final UserRepository userRepository;

     User getUserByPrincipal(Principal principal){
        return userRepository.findByEmail(principal.getName());
    }
}
