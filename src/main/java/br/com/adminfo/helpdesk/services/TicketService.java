package br.com.adminfo.helpdesk.services;

import java.util.List;

import org.springframework.ui.Model;

import br.com.adminfo.helpdesk.model.Ticket;

public interface TicketService {
	 
	public List<Ticket> findAll();
	public Model findAllTecnicos(Model model);
	public Ticket create(Ticket ticket);
	public Boolean delete(Long codigo);
	public Boolean editar(Long codigo, Ticket ticket);
	public Ticket show(Long codigo);
	
}
