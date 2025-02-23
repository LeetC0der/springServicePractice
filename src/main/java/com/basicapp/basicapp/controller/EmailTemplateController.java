package com.basicapp.basicapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basicapp.basicapp.models.EmailTemplateModel;
import com.basicapp.basicapp.services.EmailTemplateService;




@RestController
@RequestMapping("emailTemplates")
public class EmailTemplateController {

    @Autowired
    private EmailTemplateService emailTemplateService;

    @GetMapping("/")
    public ResponseEntity<?> getAllTemplates() {
        try {
            return emailTemplateService.getAllTemplates();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{templateName}")
    public ResponseEntity<?> getMethodName(@PathVariable String templateName) {
        try {
            return emailTemplateService.getTemplateByName(templateName);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    public ResponseEntity<?> postMethodName(@RequestBody EmailTemplateModel entity) {
        try {
            return emailTemplateService.saveTemplate(entity);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}   
