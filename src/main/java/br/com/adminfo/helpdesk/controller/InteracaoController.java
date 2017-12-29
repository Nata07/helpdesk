package br.com.adminfo.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.adminfo.helpdesk.model.Interacao;
import br.com.adminfo.helpdesk.services.InteracaoService;

@Controller
@RequestMapping("/tickets/{codigo_ticket}/interacoes")
public class InteracaoController {
	
	@Autowired
	private InteracaoService interacaoService;
	
	public InteracaoController(InteracaoService interacaoService) {
		this.interacaoService = interacaoService;
	}

	@PostMapping
	public String save(@PathVariable("codigo_ticket") Long codigo_ticket, 
										@ModelAttribute("interacao") Interacao interacao, BindingResult result,
										Model model){
		
		if(result.hasErrors()){
			return "ticket/show";
		}
		
		this.interacaoService.create(interacao, codigo_ticket);
		
		return "redirect:/tickets/" + codigo_ticket;
	}
	
	@DeleteMapping("{codigo}")
	public String delete(@PathVariable("codigo_ticket") Long codigo_ticket, 
							@PathVariable("codigo") Long codigo, Model model){
		
		this.interacaoService.delete(codigo, codigo_ticket);
		
		return "redirect:/tickets/" + codigo_ticket;
	}
}
