package com.DaYaSoftware.ScrappaWebVersion.repositories;

import com.DaYaSoftware.ScrappaWebVersion.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
