package com.cache.tabela;

import java.io.Serializable;

public class Usuario implements Serializable{

	private int id_usuario;
	
	private String nome_usuario;
	
	private String cpf_usuario;
		
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	public String getCpf_usuario() {
		return cpf_usuario;
	}

	public void setCpf_usuario(String cpf_usuario) {
		this.cpf_usuario = cpf_usuario;
	}

	public void criarUsuario(int id, String nome, String cpf) {
		this.id_usuario = id;
		this.nome_usuario = nome;
		this.cpf_usuario = cpf;
	}

	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", nome_usuario=" + nome_usuario + ", cpf_usuario=" + cpf_usuario
				+ "]";
	}
	
}
