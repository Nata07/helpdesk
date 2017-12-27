package br.com.adminfo.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.adminfo.helpdesk.model.Permissao;
import br.com.adminfo.helpdesk.model.Ticket;
import br.com.adminfo.helpdesk.services.PermissaoService;
import br.com.adminfo.helpdesk.services.TicketService;
import br.com.adminfo.helpdesk.services.UsuarioService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	
	private final Long CODIGO_PERMISSAO = (long) 4;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PermissaoService permissaoService;
	
	@GetMapping
	public String create(Model model){
		model.addAttribute("tickets", new Ticket());
		Permissao permissaoADM = this.permissaoService.findBayName("ADMIN");
		model.addAttribute("tecnicos", this.usuarioService.findAllWherePermissaoEquals(permissaoADM.getCodigo()));
		
//		model.addAttribute("tecnicos", this.usuarioService.findAllWherePermissaoEquals(CODIGO_PERMISSAO));
		return "ticket/create";
	}
}
