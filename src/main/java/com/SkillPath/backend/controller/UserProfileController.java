package com.SkillPath.backend.controller;

import com.SkillPath.backend.model.UserProfile;
import com.SkillPath.backend.repository.UserProfileRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "http://localhost:5173")
public class UserProfileController {

    private final UserProfileRepository repository;

    public UserProfileController(UserProfileRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public UserProfile getProfile() {
        return repository.findById(1L).orElseGet(() -> {
            UserProfile profile = new UserProfile();
            profile.setFullName("Sathya Priya");
            profile.setCareerGoal("AI Engineer");
            return repository.save(profile);
        });
    }

    @PutMapping
    public UserProfile saveProfile(@RequestBody UserProfile updatedProfile) {
        UserProfile profile = repository.findById(1L)
                .orElseGet(UserProfile::new);

        profile.setFullName(updatedProfile.getFullName());
        profile.setEmail(updatedProfile.getEmail());
        profile.setCareerGoal(updatedProfile.getCareerGoal());

        return repository.save(profile);
    }
}