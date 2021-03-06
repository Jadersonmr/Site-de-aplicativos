package br.com.jaderson.sistema.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.jaderson.sistema.domain.Desenvolvedor;
import br.com.jaderson.sistema.util.HibernateUtil;

public class DesenvolvedorDao extends GenericDao<Desenvolvedor>{

	public Desenvolvedor buscarLoginSenha(String login, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Desenvolvedor.class);
			consulta.add(Restrictions.eq("nome", login));
			consulta.add(Restrictions.eq("senha", senha));
			Desenvolvedor resultado = (Desenvolvedor)consulta.uniqueResult();
			return resultado;
		} catch (Exception e) {
			throw(e);
		}finally{
			sessao.close();
		}
	}

}
