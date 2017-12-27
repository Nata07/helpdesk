package br.com.adminfo.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adminfo.helpdesk.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
