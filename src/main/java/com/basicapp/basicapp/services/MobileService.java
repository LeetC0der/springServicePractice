package com.basicapp.basicapp.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.basicapp.basicapp.models.MobileModel;
import com.basicapp.basicapp.repository.MobileRepo;

@Service
public class MobileService {
    @Autowired
    private MobileRepo mobileRepo;

    public ResponseEntity<ArrayList<MobileModel>> getAllMobiles(){
        try {
            ArrayList<MobileModel> result = new ArrayList<>();
            mobileRepo.findAll().forEach(result::add);
            if (result.isEmpty()){
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
            }
            return new  ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
