package com.basicapp.basicapp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basicapp.basicapp.models.MobileModel;
import com.basicapp.basicapp.services.MobileService;



@RestController
@RequestMapping("/mobile")
public class Mobile {
    
    @Autowired
    private MobileService mobileService;

    @GetMapping("/")
    public ResponseEntity<ArrayList<MobileModel>> getAllMobiles() {
        return mobileService.getAllMobiles();
    }
    
    @GetMapping("/test")
    public String getMethodName() {
        return "working";
    }

}
