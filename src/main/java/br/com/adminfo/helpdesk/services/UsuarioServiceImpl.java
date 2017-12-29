package br.com.adminfo.helpdesk.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.adminfo.helpdesk.model.Permissao;
import br.com.adminfo.helpdesk.model.Usuario;
import br.com.adminfo.helpdesk.repository.PermissaoRepository;
import br.com.adminfo.helpdesk.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private PermissaoRepository permissaoRepository;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	public UsuarioServiceImpl(UsuarioRepository repository, BCryptPasswordEncoder bcrypt, 
							PermissaoRepository permissaoRepository) {
		this.repository = repository;
		this.bcrypt = bcrypt;
		this.permissaoRepository = permissaoRepository;
	}

	@Override
	public List<Usuario> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Usuario create(Usuario usuario) {
		// FAZENDO HASHCODE DA SENHA DO USUARIO
		usuario.setSenha(this.bcrypt.encode(usuario.getSenha()));
		Permissao usuarioPermissao = this.permissaoRepository.findByNome("ADMIN");
		usuario.setPermissao(new HashSet<Permissao>(Arrays.asList(usuarioPermissao)));
		return this.repository.save(usuario);
	}

	@Override
	public Boolean delete(Long codigo) {
		Usuario usuario = findById(codigo);
		
		if(usuario != null){
			this.repository.delete(usuario);
			return true;
		}
		
		return false;
	}
	
	@Override
	public List<Usuario> findAllWherePermissaoEquals(Long codigo_permissao, Long codigo_usuario) {
		return this.repository.findAllWherePermissaoEquals(codigo_permissao, codigo_usuario);
	}

	@Override
	public Boolean editar(Long codigo, Usuario usuario) {
		Usuario usuarioExiste = findById(codigo);
		
		if(usuarioExiste != null ){
			usuarioExiste.setCodigo(usuario.getCodigo());
			usuarioExiste.setNome(usuario.getNome());
			usuarioExiste.setEmail(usuario.getEmail());
			usuarioExiste.setSenha(this.bcrypt.encode(usuario.getSenha()));
			usuarioExiste.setAtivo(usuario.getAtivo());
			
//			usuarioExiste.setPermissao(new HashSet<>(Arrays.asList(a)));
			

			Permissao usuarioPermissao = 
					this.permissaoRepository.findByNome(usuario.getPermissao().iterator().next().getNome());
			usuarioExiste.setPermissao(new HashSet<Permissao>(Arrays.asList(usuarioPermissao)));
			
			this.repository.save(usuarioExiste);
			return true;
		}
		return false;
	}
	
	private Usuario findById(Long codigo){
		return this.repository.findOne(codigo);
	}

	@Override
	public Usuario show(Long codigo) {
		return findById(codigo);
	}

	@Override
	public Usuario findCurrentUser() {
		Authentication auth  = SecurityContextHolder.getContext().getAuthentication();
		
		String usename = auth.getName();
		
		Usuario usuarioLogado = this.repository.findByEmail(usename);
		return usuarioLogado;
	}

	

}
