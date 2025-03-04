package com.basicapp.basicapp.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.basicapp.basicapp.models.ApplicationModle;
import com.basicapp.basicapp.repository.ApplicationPaginationAndSortingRepo;
import com.basicapp.basicapp.repository.ApplicationRepo;

@Service
public class ApplicationService {


    @Autowired
    private ApplicationRepo applicationRepo;

    @Autowired
    private ApplicationPaginationAndSortingRepo applicationPaginationAndSortingRepo;

    public ResponseEntity<?> addNewApplication(ApplicationModle newApplication){
        try{
            ApplicationModle am = applicationRepo.save(newApplication);
            return new ResponseEntity<>(am, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllApplication(){
        try {
            return new ResponseEntity<>(applicationRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getApplicantsByStatus(String status){
        try {
            return new ResponseEntity<>(applicationRepo.findByStatusOrderByNameAsc(status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getApplicantByPartialName(String name){
        try {
            return new ResponseEntity<>(applicationRepo.findByPartialName(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getJpaRepoSortAndPageLimit(int page, int size){
        try {
            return new ResponseEntity<>(applicationRepo.findAll(PageRequest.of(page, size)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getApplicantsWithPageAndSize(int page, int size){
        try {
            return new ResponseEntity<>(applicationPaginationAndSortingRepo.findAll(PageRequest.of(page, size)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // caching with basic caching mechanism
    @Cacheable(value="applicant")
    public List<ApplicationModle> getApplications(){
        System.err.println("Fetching data from DB for applicants: ");
        List<ApplicationModle> applicants = applicationRepo.findAll();
        if(!applicants.isEmpty()){
            return applicants;
        }
        return Collections.emptyList();
    }

    @Cacheable(value="applicant")
    public ResponseEntity<?> getAllApplicantsWithCache(){
        try {
            List<ApplicationModle> applicants = applicationRepo.findAll();
            if(applicants.isEmpty()){
                return new ResponseEntity<>("No applicants found from DB", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(applicants, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
