package br.edu.sc.senac.demo.demoproject;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Client")
public class ClientEntity implements Serializable{

	private static final long serialVersionUID = 5552665667774276903L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private String nome;
	
	private String dataNascimento;
	
	private String email;
	
	
	public ClientEntity(String nome, String dataNascimento, String email) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "nome = " + this.nome + "Data de nascimento = " + this.dataNascimento + "Email = " + this.email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome != null) {
			this.nome = nome;
		}
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		if (dataNascimento != null) {
			this.dataNascimento = dataNascimento;
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email != null) {
			this.email = email;
		}
	}
	
	

}
