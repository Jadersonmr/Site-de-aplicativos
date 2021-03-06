package br.com.jaderson.sistema.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain{
	
	@Column(length=255,nullable=false)
	private String nome;
	
	@Column(length=2,nullable=false)
	private int idade;
	
	@Column(length=45)
	private String endereco;
	
	@Column(length=15,nullable=false)
	private String telefone;
	
	@Column(length=45,nullable=false)
	private String email;
	
	@Column(length=15,nullable=false)
    private String senha;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Cidade cid;
	
	@ManyToMany
	@JoinTable(name = "aplicativos_usuarios",
			joinColumns = @JoinColumn(name = "usuario_codigo"),
			inverseJoinColumns = @JoinColumn(name = "aplicativo_codigo"))
	private List<Aplicativo> aplicativos;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Cidade getCid() {
		return cid;
	}

	public void setCid(Cidade cid) {
		this.cid = cid;
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
		this.senha = senha.trim();
	}

	public List<Aplicativo> getAplicativos() {
		return aplicativos;
	}

	public void setAplicativos(List<Aplicativo> aplicativos) {
		this.aplicativos = aplicativos;
	}

}
