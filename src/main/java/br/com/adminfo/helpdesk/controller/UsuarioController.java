package br.com.adminfo.helpdesk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.adminfo.helpdesk.model.Permissao;
import br.com.adminfo.helpdesk.model.Usuario;
import br.com.adminfo.helpdesk.services.PermissaoService;
import br.com.adminfo.helpdesk.services.UsuarioService;

@Controller
@RequestMapping("/usuarios")
@PreAuthorize("hasAuthority('ADMIN')")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private PermissaoService permissaoService;
	
	public UsuarioController(UsuarioService service, PermissaoService permissaoService ) {
		this.service = service;
		this.permissaoService = permissaoService;
	}

	@GetMapping 
	public String index(Model model){
		model.addAttribute("listaUsuarios", this.service.findAll());
		return "usuarios/index";
	}
	
	@GetMapping("/novo")
	public String novo(Model model){
		model.addAttribute("usuario", new Usuario());
		return "usuarios/novo";
	}
	
	@PostMapping
	public String salvar(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model){
		if (result.hasErrors()){
			return "redirect:/usuarios/novo";
		}
		
		this.service.create(usuario);
		
		return "redirect:/usuarios";
	}
	
	@GetMapping("/editar/{codigo}")
	public String editar(@PathVariable("codigo") Long codigo, Model model){
		Usuario usuario = this.service.show(codigo);
		
		List<Permissao> permissoes = this.permissaoService.findAll();
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("permissoes", permissoes);
		return "usuarios/editar";
	}
	
	@PutMapping("{codigo}")
	public String editar(@PathVariable("codigo") Long codigo, 
				@Valid @ModelAttribute("usuario") Usuario usuario, Model model, BindingResult result){
		
		if(result.hasErrors()){
			return "/usuarios/editar";
		}
		
		this.service.editar(codigo, usuario);
		
		return "redirect:/usuarios";
	}
	
	@DeleteMapping("{codigo}")
	public String delete(@PathVariable("codigo") Long codigo){
		this.service.delete(codigo);
		
		return "redirect:/usuarios";
	}

}
