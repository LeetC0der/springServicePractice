package com.basicapp.basicapp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basicapp.basicapp.models.Ticket;
import com.basicapp.basicapp.services.TickerService;





@RestController
@RequestMapping("/ticket")
public class TricketController {
    @Autowired
    private TickerService tickerService;

    @GetMapping("/")
    public ResponseEntity<ArrayList<Ticket>> getMethodName() {
        return tickerService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getMethodName(@PathVariable Long id) {
        return tickerService.getById(id);
    }
    
    @PostMapping("/")
    public ResponseEntity<Ticket> postMethodName(@RequestBody Ticket entity) {
        return tickerService.insertNewTicket(entity);
    }
    
}
