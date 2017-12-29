package br.com.adminfo.helpdesk.services;

import br.com.adminfo.helpdesk.model.Interacao;

public interface InteracaoService {
	
	public Interacao create(Interacao interacao, Long codigo_ticket);
	public Boolean delete(Long codigo, Long codigo_ticket);
}
