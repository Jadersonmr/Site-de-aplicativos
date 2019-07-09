package br.com.jaderson.sistema.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.jaderson.sistema.domain.Cidade;
import br.com.jaderson.sistema.domain.Estado;
import br.com.jaderson.sistema.util.HibernateUtil;

public class CidadeDao extends GenericDao<Cidade>{

	@SuppressWarnings("unchecked")
	public List<Cidade> listarPorUf(Estado uf){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("uf.codigo", uf.getCodigo()));
			consulta.addOrder(Order.asc("nome"));
			List<Cidade> resultado = consulta.list();
			return resultado;
		} catch (Exception e) {
			throw(e);
		}finally{
			sessao.close();
		}
	}

}
