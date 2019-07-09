package br.com.jaderson.sistema.bean;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.jaderson.sistema.dao.AplicativoDao;
import br.com.jaderson.sistema.dao.DesenvolvedorDao;
import br.com.jaderson.sistema.dao.UsuarioDao;
import br.com.jaderson.sistema.domain.Aplicativo;
import br.com.jaderson.sistema.domain.Desenvolvedor;
import br.com.jaderson.sistema.domain.Usuario;
import br.com.jaderson.sistema.util.HibernateUtil;
import br.com.jaderson.sistema.util.SessionContext;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AplicativoBean implements Serializable{

	private Aplicativo aplicativo;
	private List<Aplicativo> aplicativos;
	private List<Desenvolvedor> desenvolvedores;
	private Usuario usuarioApp;
	
	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile x = evento.getFile();
			Path t = Files.createTempFile(null, null);
			Files.copy(x.getInputstream(), t, StandardCopyOption.REPLACE_EXISTING);
			System.out.println(t.toString());
			aplicativo.setCaminhoImg(t.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			aplicativo = (Aplicativo)evento.getComponent().getAttributes().get("aplicativoExcluir");
			AplicativoDao dao = new AplicativoDao();
			dao.excluir(aplicativo);
			Path caminho = Paths.get("C:/Users/User/workspace/Sistema/uploads/"+aplicativo.getCodigo()+".png");
			Files.deleteIfExists(caminho);
			
			listar();
			Messages.addGlobalError(aplicativo.getNome());
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir aplicativo");
			e.printStackTrace();
		}
	}
	
	private void carregaDevs(){
		try {
			DesenvolvedorDao dao = new DesenvolvedorDao();
			desenvolvedores = dao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao carregar desenvolvedores");
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent evento) {
		aplicativo = (Aplicativo)evento.getComponent().getAttributes().get("aplicativoAlterar");
		aplicativo.setCaminhoImg("C:/Users/User/workspace/Sistema/uploads/"+aplicativo.getCodigo()+".png");
		carregaDevs();
	}
	
	@PostConstruct
	public void listar(){
		try {
			AplicativoDao dao = new AplicativoDao();
			aplicativos = dao.listarTodos();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar aplicativos");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		aplicativo = new Aplicativo();
		carregaDevs();
	}
	
	public void salvar() {
		try {
			AplicativoDao dao = new AplicativoDao();
			Aplicativo aplicativoN = dao.merge(aplicativo);

			Path origem = Paths.get(aplicativo.getCaminhoImg());
			Path destino = Paths.get("C:/Users/User/workspace/Sistema/uploads/"+aplicativoN.getCodigo()+".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			
			Messages.addGlobalInfo("Nome: "+getAplicativo().getNome());
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao cadastrar aplicativo");
			e.printStackTrace();
		}		
	}
	
	public void instalar(ActionEvent evento){
		try {
			Usuario usuario = SessionContext.getInstance().getUsuarioLogado();
			List<Usuario> usuarios = new ArrayList<Usuario>();
			usuarios.add(usuario);
			
			aplicativo.setUsuarios(usuarios);
			AplicativoDao dao = new AplicativoDao();
			dao.merge(aplicativo);
			
			usuarioApp.setAplicativos(aplicativos);
			UsuarioDao daoUser = new UsuarioDao();
			daoUser.merge(usuarioApp);
			
			novo();
			listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao instalar aplicativo");
			e.printStackTrace();
		}
	}
	
	public Aplicativo buscar(String nome){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Aplicativo.class);
			consulta.add(Restrictions.eq("nome", nome));
			Aplicativo resultado = (Aplicativo) consulta;
			return resultado;
		} catch (RuntimeException e) {
			throw(e);
		}finally{
			sessao.close();
		}
	}
	
	public List<Aplicativo> usuarioApps() {
		Usuario usuario = SessionContext.getInstance().getUsuarioLogado();
		aplicativos = usuario.getAplicativos();
		return aplicativos;
	}
	
	/*
	public void instalar(ActionEvent evento) {
		Usuario usuario = SessionContext.getInstance().getUsuarioLogado();
		aplicativo.setUsuario(usuario);
		AplicativoDao dao = new AplicativoDao();
		dao.merge(aplicativo);
		novo();
		listar();
	}*/
	
	public Aplicativo getAplicativo() {
		return aplicativo;
	}

	public void setAplicativo(Aplicativo aplicativo) {
		this.aplicativo = aplicativo;
	}

	public List<Aplicativo> getAplicativos() {
		return aplicativos;
	}

	public void setAplicativos(List<Aplicativo> aplicativos) {
		this.aplicativos = aplicativos;
	}

	public List<Desenvolvedor> getDesenvolvedores() {
		return desenvolvedores;
	}

	public void setDesenvolvedores(List<Desenvolvedor> desenvolvedores) {
		this.desenvolvedores = desenvolvedores;
	}

}
