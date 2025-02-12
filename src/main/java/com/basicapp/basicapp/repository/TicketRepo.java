package com.basicapp.basicapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basicapp.basicapp.models.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Long>{

}
