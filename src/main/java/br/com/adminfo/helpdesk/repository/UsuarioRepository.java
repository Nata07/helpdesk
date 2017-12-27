package br.com.adminfo.helpdesk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.adminfo.helpdesk.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query(value = "select u.* from usuario u inner join usuario_permissao up on u.codigo = up.codigo_usuario where up.codigo_permissao = :codigo_permissao", nativeQuery = true)
	public List<Usuario> findAllWherePermissaoEquals(@Param("codigo_permissao") Long codigo_permissao);
	
}
