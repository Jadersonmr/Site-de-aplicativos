package br.com.jaderson.sistema.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.jaderson.sistema.dao.CidadeDao;
import br.com.jaderson.sistema.dao.UsuarioDao;
import br.com.jaderson.sistema.domain.Cidade;
import br.com.jaderson.sistema.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Cidade> cidades;
 	
	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario)evento.getComponent().getAttributes().get("usuarioExcluir");
			UsuarioDao dao = new UsuarioDao();
			dao.excluir(usuario);
			listar();
			Messages.addGlobalError(usuario.getNome());
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir usuario");
			e.printStackTrace();
		}
	}
	
	private void carregaCidades(){
		try {
			CidadeDao dao = new CidadeDao();
			cidades = dao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao carregar estados");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
		usuario = (Usuario)evento.getComponent().getAttributes().get("usuarioAlterar");
		carregaCidades();
	}

	@PostConstruct
	public void listar(){
		try {
			UsuarioDao dao = new UsuarioDao();
			usuarios = dao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar usuarios");
			e.printStackTrace();
		}
	}

	public void novo() {
		usuario = new Usuario();
		carregaCidades();
	}
	
	public void salvar() {
		try {
			UsuarioDao dao = new UsuarioDao();
			dao.merge(usuario);
			Messages.addGlobalInfo("Nome: "+getUsuario().getNome());
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar usuario");
			e.printStackTrace();
		}		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

}
