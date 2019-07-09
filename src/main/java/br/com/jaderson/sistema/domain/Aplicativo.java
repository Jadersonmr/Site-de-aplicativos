package br.com.jaderson.sistema.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Aplicativo extends GenericDomain{

	@Column(length=255,nullable=false)
	private String nome;
	
	@Column(length=255,nullable=false)
	private String categoria;
	
	@Column(length=10)
	private int tamanho;
	
	@Column(length=255,nullable=false)
	private String descricao;
	
	@Column(length=50)
	private int downloads;
	
	@Column(length=10)
	private int avaliacao;
	
	@Column(length=10)
	private int valor;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Desenvolvedor dev;
	
	@ManyToMany(mappedBy = "aplicativos")
	private List<Usuario> usuarios;

	@Transient
	private String caminhoImg;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Desenvolvedor getDesenvolvedor() {
		return dev;
	}

	public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
		this.dev = desenvolvedor;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getDownloads() {
		return downloads;
	}

	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Desenvolvedor getDev() {
		return dev;
	}

	public void setDev(Desenvolvedor dev) {
		this.dev = dev;
	}

	public String getCaminhoImg() {
		return caminhoImg;
	}

	public void setCaminhoImg(String caminhoImg) {
		this.caminhoImg = caminhoImg;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
