package br.com.jaderson.sistema.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.jaderson.sistema.dao.CidadeDao;
import br.com.jaderson.sistema.dao.EstadoDao;
import br.com.jaderson.sistema.domain.Cidade;
import br.com.jaderson.sistema.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable{
	
	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;
	
	public void excluir(ActionEvent evento) {
		try {
			cidade = (Cidade)evento.getComponent().getAttributes().get("cidadeExcluir");
			CidadeDao dao = new CidadeDao();
			dao.excluir(cidade);
			listar();
			Messages.addGlobalError(cidade.getNome());
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir cidade");
			e.printStackTrace();
		}
	}
	
	private void carregaUfs(){
		try {
			EstadoDao dao = new EstadoDao();
			estados = dao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao carregar estados");
			e.printStackTrace();
		}
	}

	public void alterar(ActionEvent evento) {
			cidade = (Cidade)evento.getComponent().getAttributes().get("cidadeAlterar");
			carregaUfs();
	}
	
	@PostConstruct
	public void listar(){
		try {
			CidadeDao dao = new CidadeDao();
			cidades = dao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar cidades");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		cidade = new Cidade();
		carregaUfs();
	}
	
	public void salvar() {
		try {
			CidadeDao dao = new CidadeDao();
			dao.merge(cidade);
			Messages.addGlobalInfo("Nome: "+getCidade().getNome());
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar cidade");
			e.printStackTrace();
		}		
	}
	
	
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}
