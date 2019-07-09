package br.com.jaderson.sistema.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.jaderson.sistema.dao.CidadeDao;
import br.com.jaderson.sistema.dao.DesenvolvedorDao;
import br.com.jaderson.sistema.domain.Cidade;
import br.com.jaderson.sistema.domain.Desenvolvedor;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DesenvolvedorBean implements Serializable{

	private Desenvolvedor desenvolvedor;
	private List<Desenvolvedor> desenvolvedores;
	private List<Cidade> cidades;
	
	public void excluir(ActionEvent evento) {
		try {
			desenvolvedor = (Desenvolvedor)evento.getComponent().getAttributes().get("desenvolvedorExcluir");
			DesenvolvedorDao dao = new DesenvolvedorDao();
			dao.excluir(desenvolvedor);
			listar();
			Messages.addGlobalError(desenvolvedor.getNome());
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir desenvolvedor");
			e.printStackTrace();
		}
	}
	
	private void carregaCidadess(){
		try {
			CidadeDao dao = new CidadeDao();
			cidades = dao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao carregar estados");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
		desenvolvedor = (Desenvolvedor)evento.getComponent().getAttributes().get("desenvolvedorAlterar");
		carregaCidadess();
	}

	@PostConstruct
	public void listar(){
		try {
			DesenvolvedorDao dao = new DesenvolvedorDao();
			desenvolvedores = dao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar desenvolvedores");
			e.printStackTrace();
		}
	}

	public void novo() {
		desenvolvedor = new Desenvolvedor();
		carregaCidadess();
	}
	
	public void salvar() {
		try {
			DesenvolvedorDao dao = new DesenvolvedorDao();
			dao.merge(desenvolvedor);
			Messages.addGlobalInfo("Nome: "+getDesenvolvedor().getNome());
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar desenvolvedor");
			e.printStackTrace();
		}		
	}
	
	public Desenvolvedor getDesenvolvedor() {
		return desenvolvedor;
	}
	public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}
	public List<Desenvolvedor> getDesenvolvedores() {
		return desenvolvedores;
	}
	public void setDesenvolvedores(List<Desenvolvedor> desenvolvedores) {
		this.desenvolvedores = desenvolvedores;
	}
	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

}
