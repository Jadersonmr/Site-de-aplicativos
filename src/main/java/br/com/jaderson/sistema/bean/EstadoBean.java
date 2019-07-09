package br.com.jaderson.sistema.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.event.ActionEvent;

//import javax.faces.context.FacesContext;
import org.omnifaces.util.Messages;

import br.com.jaderson.sistema.dao.EstadoDao;
import br.com.jaderson.sistema.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable{

	private Estado estado;
	private List<Estado> estados;
	
	public void excluir(ActionEvent evento) {
		try {
			estado = (Estado)evento.getComponent().getAttributes().get("ufExcluir");
			EstadoDao dao = new EstadoDao();
			dao.excluir(estado);
			listar();
			Messages.addGlobalError(estado.getNome()+"-"+estado.getSigla());
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir UF");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
			estado = (Estado)evento.getComponent().getAttributes().get("ufAlterar");
	}
	
	@PostConstruct
	public void listar(){
		try {
			EstadoDao dao = new EstadoDao();
			estados = dao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar UFs");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		estado = new Estado();
	}
	
	public void salvar() {
		try {
			EstadoDao dao = new EstadoDao();
			dao.merge(estado);
			Messages.addGlobalInfo("Nome: "+getEstado().getNome()+"\nUF: "+getEstado().getSigla());
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar UF");
			e.printStackTrace();
		}
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
}
