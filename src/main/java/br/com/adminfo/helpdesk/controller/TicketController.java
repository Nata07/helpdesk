package br.com.adminfo.helpdesk.controller;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.adminfo.helpdesk.model.Ticket;

import br.com.adminfo.helpdesk.services.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	private TicketService ticketService; 
	
	
	@GetMapping
	public String index(Model model){
		model.addAttribute("listaTicket", this.ticketService.findAll());
		return "ticket/index";
	}
	
	@GetMapping("/novo")
	public String create(Model model){
		model.addAttribute("ticket", new Ticket());
		model = this.ticketService.findAllTecnicos(model);
		return "ticket/create";

	}
	
	@PostMapping
	public String save(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult result){
		
		if(result.hasErrors()){
			return "tickets/create";
		}
		this.ticketService.create(ticket);
		return "redirect:/tickets";
	}
	
	@GetMapping("/editar/{codigo}")
	public String editar(@PathVariable("codigo") Long codigo, Model model){
		model = this.ticketService.findAllTecnicos(model);
		model.addAttribute("ticket", this.ticketService.show(codigo));
		return "ticket/editar";
	}
	
	@PutMapping("{codigo}")
	public String update(@Valid @PathVariable("codigo") Long codigo, @ModelAttribute("ticket") Ticket ticket, Model model, BindingResult result){
		if(result.hasErrors()){
			return "ticket/editar";
		}
		
		this.ticketService.editar(codigo, ticket);
		
		return "riderect:/tickets";
	}
	
	@GetMapping("{codigo}")
	public String show(@PathVariable("codigo") Long codigo, Model model){
		model.addAttribute("ticket", this.ticketService.show(codigo));
		return "ticket/show";
	}
	
	@DeleteMapping("{codigo}")
	public String delete(@PathVariable("codigo") Long codigo, Model model){
		this.ticketService.delete(codigo);
		
		return "redirect:/tickets";
	}
	
}
