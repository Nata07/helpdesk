package br.com.adminfo.helpdesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adminfo.helpdesk.model.Permissao;
import br.com.adminfo.helpdesk.repository.PermissaoRepository;

@Service
public class PermissaoServiceImpl implements PermissaoService{

	@Autowired
	private PermissaoRepository repository;
	
	public PermissaoServiceImpl(PermissaoRepository repository) {
		this.repository=repository;
	}
	
	@Override
	public List<Permissao> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Permissao create(Permissao permissao) {
		permissao.setNome(permissao.getNome().toUpperCase());
		Permissao novaPermissao = this.repository.save(permissao);
		return novaPermissao;
	}

	@Override
	public Boolean delete(Long codigo) {
		Permissao permissao = findById(codigo);
		if(permissao != null){
			this.repository.delete(permissao);
			return true;
		}
		
		return false;
	}
	
	private Permissao findById(Long codigo){
		return this.repository.findOne(codigo);
	}

	@Override
	public Permissao findBayName(String name) {
		return this.repository.findByNome(name);
	}
}
