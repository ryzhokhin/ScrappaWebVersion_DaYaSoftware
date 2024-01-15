package com.DaYaSoftware.ScrappaWebVersion.services;

import com.DaYaSoftware.ScrappaWebVersion.models.User;
import com.DaYaSoftware.ScrappaWebVersion.models.enums.Role;
import com.DaYaSoftware.ScrappaWebVersion.models.enums.SubscriptionType;
import com.DaYaSoftware.ScrappaWebVersion.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public boolean createUser(User user){
        System.out.println("*********** THE CODE HAS GOT INTO THE USER SERVICE FILE ***********");
        String email = user.getEmail();
        if(userRepository.findByEmail(email) != null) {
            log.info("Error in the USER SERVICE FILE");

            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        user.getSubscriptionTypes().add(SubscriptionType.TYPE_UNSUBSCRIBED);
        log.info("Saving new User with email: {}", email);
        userRepository.save(user);
        return true;
    }

    public boolean subscribeUser(User user, SubscriptionType subscriptionType){
        System.out.println("*********** THE CODE EXECUTED TO THE SUBSCRIPTION METHOD ***********");
        String email = user.getEmail();
        if(userRepository.findByEmail(email) != null) {
            log.info("Error in the USER SERVICE FILE METHOD [SUB]");
            System.out.println("Error in the USER SERVICE FILE METHOD [SUB]");
            return false;
        }
//        SubscriptionType lastUserSubType = user.getSubscriptionTypes().;
        user.getSubscriptionTypes().clear();
        user.getSubscriptionTypes().add(subscriptionType);
        userRepository.save(user);
        return true;
    }

//    private Principal principal;
//
//    public User getActiveUser(Principal principal){
//        User activeUser =
//    }
}
