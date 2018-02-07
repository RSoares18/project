package pt.adentis.gerador.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import pt.adentis.gerador.model.TipoProposta;

public class TipoPropostaDAOImpl implements TipoPropostaDAO {
	
	private SessionFactory sessionFactory;
	
	public TipoPropostaDAOImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<TipoProposta> listaTipoProposta() {
		@SuppressWarnings({ "unchecked", "deprecation" })
		List <TipoProposta> listaTipoPropostas = (List<TipoProposta>)
				sessionFactory.getCurrentSession().createCriteria(TipoProposta.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		return listaTipoPropostas;
	}

}
