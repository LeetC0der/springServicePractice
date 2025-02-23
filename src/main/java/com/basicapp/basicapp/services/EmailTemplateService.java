package com.basicapp.basicapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.basicapp.basicapp.cache.EmailTemplateCacheService;
import com.basicapp.basicapp.models.EmailTemplateModel;
import com.basicapp.basicapp.repository.EmailTemplateRepo;

@Service
public class EmailTemplateService {
    @Autowired
    private EmailTemplateRepo emailTemplateRepo;

    @Autowired
    private EmailTemplateCacheService emailTemplateCacheService;

    public ResponseEntity<?> getAllTemplates(){
        try {
            List<?> templates = emailTemplateCacheService.getAllTemplate();
            if(templates.isEmpty()){
                return new ResponseEntity<>("No email templates found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(templates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> saveTemplate(EmailTemplateModel entity){
        try {
            emailTemplateCacheService.removeAllTemplates();
            emailTemplateCacheService.removeAllPartialNameTemplate();
            EmailTemplateModel saveTemplate = emailTemplateRepo.save(entity);
            return new ResponseEntity<>(saveTemplate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getTemplateByName(String name){
        try {
            List<EmailTemplateModel> templateByName = emailTemplateCacheService.getTemplateByName(name);
            if(templateByName == null){
                return new ResponseEntity<>("No such template found with the name " + name, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(templateByName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
