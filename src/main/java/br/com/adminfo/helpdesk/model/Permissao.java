package br.com.adminfo.helpdesk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name = "permissao") 
public class Permissao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long codigo;
	@Column
	@NotEmpty(message = "Permissão é obrigatória")
	private String nome;
	
	public Permissao(){
		
	}
	
	public Permissao(String nome) {
		this.nome = nome;
	}
	
	public Permissao(Long codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
