package br.com.adminfo.helpdesk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.adminfo.helpdesk.model.Permissao;
import br.com.adminfo.helpdesk.services.PermissaoService;

@Controller 
@RequestMapping("/permissao")
@PreAuthorize("hasAuthority('ADMIN')")
public class PermissaoController {
	
	@Autowired
	private PermissaoService service;
	
	public PermissaoController(PermissaoService service) {
		this.service=service;
	}
	
	@GetMapping
	public String index(Model model){
		model.addAttribute("listaPermissao", this.service.findAll());
		return "/permissao/index";
	}
	@GetMapping("/nova")
	public String create(Model model){
		model.addAttribute("permissao", new Permissao());
		return "/permissao/novaPermissao";
	}
	@PostMapping
	public String save(@Valid Permissao permissao, 
			BindingResult result, Model model){
		
		if(result.hasErrors()){
			return "redirect:/permissao/nova";
		}		
		Permissao permissaoCreate = this.service.create(permissao);
		
		return "redirect:/permissao";
	}
	@DeleteMapping("{codigo}")
	public String delete(@PathVariable("codigo") Long codigo, Model model){
		this.service.delete(codigo);
		return "redirect:/permissao";
	}
}
