package com.basicapp.basicapp.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.basicapp.basicapp.models.EmailTemplateModel;
import com.basicapp.basicapp.repository.EmailTemplateRepo;

@Service
public class EmailTemplateCacheService {
    @Autowired
    private EmailTemplateRepo emailTemplateRepo;

    @Cacheable(value = "email_templates", key="'all_templates'")
    public List<EmailTemplateModel> getAllTemplate() {
        try {
            System.err.println("Cache miss: Fetching all templates from DB...");
            return emailTemplateRepo.findAll();
        } catch (Exception e) {
            System.err.println("Error fetching templates: " + e.getMessage());
            return new ArrayList<>(); 
        }
    }

    @Cacheable(value = "email_template_name", key = "#name")
    public List<EmailTemplateModel> getTemplateByName(String name){
        try {
            System.err.println("Cache miss: Fetching template with name: " + name + " from db...");
            List<EmailTemplateModel> template = emailTemplateRepo.findByPartialName(name);
            if(template == null){
                System.err.println("No template found in db for name: " + name);
            }
            return template;
        } catch (Exception e) {
            return null;
        }
    }

    @CacheEvict(value="email_templates", allEntries=true)
    public void removeAllTemplates(){
        System.err.println("Evicting all the templates cache...");
    }

    @CacheEvict(value="email_templates_name", allEntries=true)
    public void removeAllPartialNameTemplate(){
        System.err.println("Eviciting all the templates with partial name cache...");
    }
}
