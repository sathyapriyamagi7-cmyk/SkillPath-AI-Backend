package com.SkillPath.backend.controller;

import com.SkillPath.backend.model.User;
import com.SkillPath.backend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/user-data")
public class UserDataController {

    private final UserService userService;

    public UserDataController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(user -> {
                    User safeUser = new User();
                    safeUser.setId(user.getId());
                    safeUser.setEmail(user.getEmail());
                    safeUser.setFullName(user.getFullName());
                    safeUser.setName(user.getName());
                    return safeUser;
                })
                .toList();
    }
}
