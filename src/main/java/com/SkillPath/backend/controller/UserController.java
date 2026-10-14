package com.SkillPath.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/api/users/status")
    public String status() {
        return "User API is ready";
    }
}
