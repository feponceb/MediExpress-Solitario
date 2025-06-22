package com.mediexpress.gestionreclamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionreclamos.model.ticket;
import com.mediexpress.gestionreclamos.repository.ticketRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ticketService {

    @Autowired
    private ticketRepository ticketRepository;

    public List<ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public ticket getTicket(Long id) {
        return ticketRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
    }

    public ticket saveTicket(ticket t) {
        return ticketRepository.save(t);
    }

    public ticket updateTicket(Long id, ticket nuevo) {
        ticket existente = getTicket(id);
        existente.setFechaTicket(nuevo.getFechaTicket());
        return ticketRepository.save(existente);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

}
