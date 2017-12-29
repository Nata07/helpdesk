package br.com.adminfo.helpdesk.services;

import java.util.List;

import br.com.adminfo.helpdesk.model.Usuario;

public interface UsuarioService {

	public List<Usuario> findAll();
	public Usuario create(Usuario usuario);
	public Boolean delete(Long codigo);
	public Boolean editar(Long codigo, Usuario usuario);
	public Usuario show(Long codigo);
	public List<Usuario> findAllWherePermissaoEquals(Long codigo_permissao, Long codigo_usuario);
	public Usuario findCurrentUser();
}
