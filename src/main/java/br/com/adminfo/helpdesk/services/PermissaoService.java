package br.com.adminfo.helpdesk.services;

import java.util.List;

import br.com.adminfo.helpdesk.model.Permissao;

public interface PermissaoService {
	
	public List<Permissao> findAll();
	public Permissao create(Permissao permissao);
	public Boolean delete(Long codigo);
	public Permissao findBayName(String name);

}
