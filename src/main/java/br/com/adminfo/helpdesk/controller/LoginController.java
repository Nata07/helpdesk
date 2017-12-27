package br.com.adminfo.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.adminfo.helpdesk.model.Usuario;

@Controller
public class LoginController {
	 
	@GetMapping("/login")
	public String login(Model model){
		return "login/login";
	} 
	
	@GetMapping("/registro")
	public String registro(Model model){
		model.addAttribute("usuario", new Usuario());
		return "login/registro";
	}
}
