package com.mediexpress.gestionreclamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediexpress.gestionreclamos.model.ticket;
import com.mediexpress.gestionreclamos.service.ticketService;

@RestController
@RequestMapping("/ticket")
public class ticketController {

    @Autowired
    private ticketService ticketService;

    @GetMapping
    public List<ticket> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ticket> getTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicket(id));
    }

    @PostMapping
    public ResponseEntity<ticket> saveTicket(@RequestBody ticket t) {
        ticket nuevo = ticketService.saveTicket(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ticket> updateTicket(@PathVariable Long id, @RequestBody ticket t) {
        ticket actualizado = ticketService.updateTicket(id, t);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

}
