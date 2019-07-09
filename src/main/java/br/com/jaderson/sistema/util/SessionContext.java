package br.com.jaderson.sistema.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.jaderson.sistema.domain.Desenvolvedor;
import br.com.jaderson.sistema.domain.Usuario;

public class SessionContext {
	
	private static SessionContext instance;
	
	public static SessionContext getInstance(){
        if (instance == null){
            instance = new SessionContext();
        }
        return instance;
	}
	
	private ExternalContext currentExternalContext(){
        if (FacesContext.getCurrentInstance() == null){
            throw new RuntimeException("Falta uma requisicão HTTP");
        }else{
            return FacesContext.getCurrentInstance().getExternalContext();
        }
	}
	
	 public Usuario getUsuarioLogado(){
         return (Usuario) getAttribute("usuarioLogado");
	 }
	 
	 public void setUsuarioLogado(Usuario usuario){
         setAttribute("usuarioLogado", usuario);
	 }
	 
	 public Desenvolvedor getDesenvolvedorLogado(){
         return (Desenvolvedor) getAttribute("desenvolvedorLogado");
	 }
	 
	 public void setDesenvolvedorLogado(Desenvolvedor desenvolvedor){
         setAttribute("desenvolvedorLogado", desenvolvedor);
	 }
	 
	 public void encerrarSessao(){   
         currentExternalContext().invalidateSession();
	 }
	 
	 public Object getAttribute(String nome){
         return currentExternalContext().getSessionMap().get(nome);
	 }
	 
	 public void setAttribute(String nome, Object valor){
         currentExternalContext().getSessionMap().put(nome, valor);
	 }
	
	

}
