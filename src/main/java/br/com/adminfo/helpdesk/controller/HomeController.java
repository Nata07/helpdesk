package br.com.adminfo.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping
	public String home(Model model){
		return "home/home"; 
	}
	
	@GetMapping("/acessoNegado")
	public String acessoNegado(Model model){
		return "home/403";
	}
}
