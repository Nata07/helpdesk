package br.com.adminfo.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adminfo.helpdesk.model.Interacao;

public interface InteracaoRepository extends JpaRepository<Interacao, Long>{

}
