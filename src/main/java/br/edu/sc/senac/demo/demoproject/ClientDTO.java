package br.edu.sc.senac.demo.demoproject;

// Classe que simulou o Banco de Dados

public class ClientDTO {
	
	public static final ClientDTO NULL_VALUE = new ClientDTO("", "", "");
	
	private String nome;
	private String dataNascimento;
	private String email;

	public ClientDTO(String nome, String dataNascimento, String email) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public String getEmail() {
		return email;
	}

}
