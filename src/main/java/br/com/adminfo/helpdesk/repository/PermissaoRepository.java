package br.com.adminfo.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adminfo.helpdesk.model.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	Permissao findByNome(String nome);
}
