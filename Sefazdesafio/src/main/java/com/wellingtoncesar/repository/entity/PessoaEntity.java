package com.wellingtoncesar.repository.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
 
 
@Entity
@Table(name="tb_pessoa")
 
@NamedQueries({
 
	@NamedQuery(name = "PessoaEntity.findAll",query= "SELECT p FROM PessoaEntity p"),
	@NamedQuery(name = "PessoaEntity.findUser",query= "SELECT p FROM PessoaEntity p WHERE p.usuario = :usuario and p.senha = :senha"),
	
 
})
public class PessoaEntity {
 
	@Id
	@GeneratedValue
	@Column(name = "id_pessoa")
	private Integer codigo;
 
	@Column(name = "nm_pessoa")
	private String  nome;
 
	@Column(name = "ds_email")
	private String  email;
 
	@Column(name="ds_login")
	private String usuario;
 
	@Column(name="ds_senha")
	private String senha;
 

	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
 
}