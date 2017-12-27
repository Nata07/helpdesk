package br.com.adminfo.helpdesk.services;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import br.com.adminfo.helpdesk.model.Permissao;
import br.com.adminfo.helpdesk.model.Ticket;
import br.com.adminfo.helpdesk.model.Usuario;
import br.com.adminfo.helpdesk.repository.TicketRepository;
import br.com.adminfo.helpdesk.repository.UsuarioRepository;

@Service
public class TicketServiceImpl implements TicketService {
	
	private final Long CODIGO_PERMISSAO = (long) 4;
	private final String PERMISSAO_NOME = "ADMIN";
	
	@Autowired
	private TicketRepository repository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PermissaoService permissaoService;
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public List<Ticket> findAll() {
		
		return this.repository.findAll();
	}
	
	public TicketServiceImpl(TicketRepository repository, UsuarioRepository usuarioRepository) {
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Ticket create(Ticket ticket) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String nomeUsuario = auth.getName();
		Usuario usuarioLogado = this.usuarioRepository.findByEmail(nomeUsuario);
		
		ticket.setUsuario(usuarioLogado);
		return this.repository.save(ticket);
		
	}

	@Override
	public Boolean delete(Long codigo) {
		Ticket ticket = findByCodigo(codigo);
		
		if(ticket != null){
			this.repository.delete(codigo);
			return true;
		}
		return false;
	}

	@Override
	public Boolean editar(Long codigo, Ticket ticket) {
		Ticket ticketExiste = findByCodigo(codigo);
		
		if(ticketExiste != null){
			ticketExiste.setCodigo(ticket.getCodigo());
			ticketExiste.setTitulo(ticket.getTitulo());
			ticketExiste.setDescricao(ticket.getDescricao());
			ticketExiste.setFinalizado(ticket.getFinalizado()); 
			ticketExiste.setTecnico(ticket.getTecnico());
			if(ticket.getFinalizado()){
				ticketExiste.setFim(new Date());
			}
			
			this.repository.save(ticketExiste);
			
		}
		return null;
	}

	@Override
	public Ticket show(Long codigo) {
		return this.repository.findOne(codigo);
	}

	public Model findAllTecnicos(Model model) {
		
		Permissao permissaoADM = this.permissaoService.findBayName(PERMISSAO_NOME);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String nomeUsuario = auth.getName();
		Usuario usuarioLogado = this.usuarioRepository.findByEmail(nomeUsuario);
		model.addAttribute("tecnicos", this.usuarioService.findAllWherePermissaoEquals(permissaoADM.getCodigo(),
																				  usuarioLogado.getCodigo()));
	
		return model;
	}
	
	private Ticket findByCodigo(Long codigo){
		return this.repository.findOne(codigo);
	}

}
