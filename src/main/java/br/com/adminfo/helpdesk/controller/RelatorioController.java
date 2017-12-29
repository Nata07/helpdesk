package br.com.adminfo.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
@RequestMapping("/relatorios")
public class RelatorioController {

	@GetMapping("/tickets")
	public String ticketRlatorios(Model model){
		
		return "relatorios/ticket"; 
	}
} 
