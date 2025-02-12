package com.basicapp.basicapp.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.basicapp.basicapp.models.Ticket;
import com.basicapp.basicapp.repository.TicketRepo;

@Service
public class TickerService {
    @Autowired
    private TicketRepo ticketRepo;


    public ResponseEntity<ArrayList<Ticket>> getAllTickets(){
        try{
            ArrayList<Ticket> result = new ArrayList<>(); // []
            ticketRepo.findAll().forEach(result::add);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    public ResponseEntity<Ticket> getById(Long id){
        try {
            Optional<Ticket> result = ticketRepo.findById(id);
            if(!result.isEmpty()){
                return new ResponseEntity<>(result.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    public ResponseEntity<Ticket> insertNewTicket(@RequestBody Ticket newTicket){
        try {
            Ticket response = ticketRepo.save(newTicket);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
