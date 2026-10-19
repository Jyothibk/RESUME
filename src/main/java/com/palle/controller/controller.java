package com.palle.controller;

import com.palle.model.model;
import com.palle.service.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins = "*")
public class controller {

    private final service aiService;

    @Autowired
    public controller(service aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<model> analyzeResume(
            @RequestParam String candidateName, 
            @RequestBody String resumeText) {
        
        if (resumeText == null || resumeText.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        model result = aiService.analyzeAndSaveResume(candidateName, resumeText);
        return ResponseEntity.ok(result);
    }
}