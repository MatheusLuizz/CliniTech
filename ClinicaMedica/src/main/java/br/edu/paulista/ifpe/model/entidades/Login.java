package br.edu.paulista.ifpe.model.entidades;

public class Login {
	private String usuario, email, senha;

	public Login(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	public void cadastrar(String usuario, String email, String senha) {
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
