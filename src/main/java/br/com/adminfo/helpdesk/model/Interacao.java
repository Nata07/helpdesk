package br.com.adminfo.helpdesk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "interacao")
public class Interacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column
	@NotBlank(message = "Nao pode ser em branco")
	private String texto;
	@Column
	private Date data_criacao;
	
	@ManyToOne
	@JoinColumn(name = "codigo_usuario")
	@JsonBackReference
	private Usuario usuario_interacao;
	
	@ManyToOne
	@JoinColumn(name = "codigo_ticket")
	@JsonBackReference
	private Ticket ticket;
	
	@PrePersist
	public void prePersist() {
		this.setData_criacao(new Date());
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}

	public Usuario getUsuario_interacao() {
		return usuario_interacao;
	}

	public void setUsuario_interacao(Usuario usuario_interacao) {
		this.usuario_interacao = usuario_interacao;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
}
