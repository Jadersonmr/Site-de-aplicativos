package br.com.jaderson.sistema.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;

import br.com.jaderson.sistema.dao.DesenvolvedorDao;
import br.com.jaderson.sistema.dao.UsuarioDao;
import br.com.jaderson.sistema.domain.Desenvolvedor;
import br.com.jaderson.sistema.domain.Usuario;
import br.com.jaderson.sistema.util.SessionContext;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
	 
	private Usuario user;
	private Desenvolvedor dev;
	private String login,senha;
	
	public String fazLogin(){
		try {
			UsuarioDao dao = new UsuarioDao();
			user = dao.buscarLoginSenha(login, senha);
			
			DesenvolvedorDao devDao = new DesenvolvedorDao();
			dev = devDao.buscarLoginSenha(login, senha);
			
			if(user != null){
				SessionContext.getInstance().setAttribute("usuarioLogado", user);
				return "/templates/inicio.xhtml?faces-redirect=true";
			}if(dev != null){
				SessionContext.getInstance().setAttribute("desenvolvedorLogado", dev);
				return "/templates/inicio.xhtml?faces-redirect=true";
			}else{
				FacesContext.getCurrentInstance().validationFailed();
				Messages.addGlobalError("Usuário ou senha inválidos");
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String encerraSessao(){
		try {
			SessionContext.getInstance().encerrarSessao();
			return "/templates/login.xhtml?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Desenvolvedor getDev() {
		return dev;
	}

	public void setDev(Desenvolvedor dev) {
		this.dev = dev;
	}

}
