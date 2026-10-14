package com.SkillPath.backend.service;

import com.SkillPath.backend.model.Skill;
import com.SkillPath.backend.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill saveSkill(Skill skill) {
        if (skill.getName() == null || skill.getName().isBlank()) {
            throw new IllegalArgumentException("Skill name is required.");
        }

        if (skill.getLevel() == null || skill.getLevel() < 0 || skill.getLevel() > 100) {
            throw new IllegalArgumentException("Skill level must be between 0 and 100.");
        }

        return skillRepository.findByNameIgnoreCase(skill.getName().trim())
                .map(existing -> {
                    existing.setLevel(skill.getLevel());
                    return skillRepository.save(existing);
                })
                .orElseGet(() -> {
                    skill.setName(skill.getName().trim());
                    return skillRepository.save(skill);
                });
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}
