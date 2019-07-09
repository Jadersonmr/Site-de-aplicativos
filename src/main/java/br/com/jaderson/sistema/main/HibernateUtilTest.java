package br.com.jaderson.sistema.main;

import java.util.ArrayList;

//import org.hibernate.Session;

import br.com.jaderson.sistema.dao.EstadoDao;
//import br.com.jaderson.sistema.dao.GenericDao;
import br.com.jaderson.sistema.domain.Estado;
//import br.com.jaderson.sistema.util.HibernateUtil;

public class HibernateUtilTest {
	
	public static void main(String[] args) {
		//Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		//sessao.close();
		//HibernateUtil.getFabricaDeSessoes().close();
		
		EstadoDao estado = new EstadoDao();
		ArrayList<Estado> lista = (ArrayList<Estado>)estado.listarTodos();
		for (Estado e : lista) {
			System.out.println(e.getNome()+"-"+e.getSigla());
		}
		
		/*Estado e = new Estado();
		e.setNome("Santa Catarina");
		e.setSigla("SC");
		
		EstadoDao estado = new EstadoDao();
		estado.Salvar(e);*/
	}

}
