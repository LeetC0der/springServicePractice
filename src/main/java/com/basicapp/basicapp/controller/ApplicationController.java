package com.basicapp.basicapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.basicapp.basicapp.models.ApplicationModle;
import com.basicapp.basicapp.services.ApplicationService;




@RestController
@RequestMapping("application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/")
    public ResponseEntity<?> getAllApplicants() {
        return applicationService.getAllApplication();
    }

    @GetMapping("/{status}")
    public ResponseEntity<?> getAllApplicantByStatus(@PathVariable String status) {
        return applicationService.getApplicantsByStatus(status);
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getApplicantWithPartialName(@PathVariable String name) {
        return applicationService.getApplicantByPartialName(name);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getApplicationsWithCurrentPageAndSize(@RequestParam int page, @RequestParam int size) {
        return applicationService.getJpaRepoSortAndPageLimit(page, size);
    }
    
    @PostMapping("/")
    public ResponseEntity<?> addNewApplicant(@RequestBody ApplicationModle entity) {
        return applicationService.addNewApplication(entity);
    }
    
}
