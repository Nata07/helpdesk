package br.com.adminfo.helpdesk.controller;

import java.util.List;

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

import br.com.adminfo.helpdesk.model.Interacao;
import br.com.adminfo.helpdesk.model.Ticket;

import br.com.adminfo.helpdesk.services.TicketService;
import br.com.adminfo.helpdesk.services.UsuarioService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	private TicketService ticketService; 
	
	@Autowired
	private UsuarioService usuarioSevice;
	
	public TicketController(TicketService ticketService, UsuarioService usuarioService) {
		this.ticketService = ticketService;
		this.usuarioSevice = usuarioService;
	}
	
	@GetMapping
	public String index(Model model){
		model.addAttribute("listaTicket", this.ticketService.findAll());
		model.addAttribute("usuarioLogado", this.usuarioSevice.findCurrentUser());
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
		Ticket ticket = this.ticketService.show(codigo);
		List<Interacao> interacoes = ticket.getInteracoes();
		model = this.ticketService.findAllTecnicos(model);
		model.addAttribute("ticket", this.ticketService.show(codigo));
		model.addAttribute("interaction_count", interacoes.size());
		model.addAttribute("usuarioLogado", this.usuarioSevice.findCurrentUser());
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
		Ticket ticket = this.ticketService.show(codigo);
		List<Interacao> interacoes = ticket.getInteracoes();
		
		model.addAttribute("ticket", ticket);
		model.addAttribute("interacao", new Interacao());
		model.addAttribute("interacoes", interacoes);
		model.addAttribute("usuarioLogado", this.usuarioSevice.findCurrentUser());
		return "ticket/show";
	}
	
	@DeleteMapping("{codigo}")
	public String delete(@PathVariable("codigo") Long codigo, Model model){
		this.ticketService.delete(codigo);
		
		return "redirect:/tickets";
	}
	
}
