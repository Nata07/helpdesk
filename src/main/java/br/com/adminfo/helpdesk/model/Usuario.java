package br.com.adminfo.helpdesk.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long codigo;
	@Column(unique = true)
	@Email(message = "Email invalido")
	private String email;
	@Column
	@NotBlank(message = "Nome é obrigatório!")
	private String nome;
	@Column
	@NotBlank(message = "Senha é obrigatório!")
	@Length(message = "Minimo de 5(cinco) caracteres")
	private String senha;
	@Column
	private boolean ativo = true;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "codigo_usuario"), 
					inverseJoinColumns = @JoinColumn(name = "codigo_permissao"))
	private Set<Permissao> permissao;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="usuario")
	private Set<Ticket> tickets;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="tecnico")
	private Set<Ticket> ticketTecinico;
	
	
	public Usuario() {
		
	}

	public Usuario(Long codigo, String email, String nome, String senha, boolean ativo) {
		this.codigo = codigo;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.ativo = ativo;
	}
	
	public Usuario(String email, String nome, String senha, boolean ativo) {
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.ativo = ativo;
	}
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() { 
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public Set<Permissao> getPermissao() {
		return permissao;
	}

	public void setPermissao(Set<Permissao> permissoes) {
		this.permissao = permissoes;
	}
	
}
