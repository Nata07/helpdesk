package br.com.adminfo.helpdesk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.adminfo.helpdesk.model.Interacao;
import br.com.adminfo.helpdesk.model.Ticket;
import br.com.adminfo.helpdesk.model.Usuario;
import br.com.adminfo.helpdesk.repository.InteracaoRepository;
import br.com.adminfo.helpdesk.repository.TicketRepository;
import br.com.adminfo.helpdesk.repository.UsuarioRepository;

@Service
public class InteracaoServiceImpl implements InteracaoService {

	@Autowired
	private InteracaoRepository repository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public InteracaoServiceImpl(InteracaoRepository repository, TicketRepository ticketRepository, 
								UsuarioRepository usuarioRepository) {
		this.repository = repository;
		this.ticketRepository = ticketRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Interacao create(Interacao interacao, Long codigo_ticket) {
		Ticket ticket = this.ticketRepository.findOne(codigo_ticket);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Usuario usuarioLogado =	this.usuarioRepository.findByEmail(username);
		
		interacao.setTicket(ticket);
		interacao.setUsuario_interacao(usuarioLogado);
		return this.repository.save(interacao);
	}

	@Override
	public Boolean delete(Long codigo, Long codigo_ticket) {
		Interacao interacao = this.repository.findOne(codigo);
		if(interacao != null){
			this.repository.delete(interacao);
			return true;
		}
		return false;
	}

}
