package com.palle.service;

import com.palle.model.model;
import com.palle.repository.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class service {

    private final repository repo;

    @Autowired
    public service(repository repo) {
        this.repo = repo;
    }

    public model analyzeAndSaveResume(String name, String resumeContent) {
        model resumeData = new model();
        resumeData.setCandidateName(name);
        resumeData.setResumeText(resumeContent);
        
        // Mock AI Logic (Replace with actual LLM API call like Gemini/OpenAI)
        resumeData.setMatchScore(85);
        resumeData.setAiFeedback("Strong background in Java and Spring Boot. Consider adding more details about cloud deployments.");

        // Save the analyzed data to the database
        return repo.save(resumeData);
    }
}