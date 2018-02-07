package pt.adentis.gerador.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import pt.adentis.gerador.model.TipoContrato;

public class TipoContratoDAOImpl implements TipoContratoDAO {
	
	private SessionFactory sessionFactory;
	
	public TipoContratoDAOImpl (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	@Transactional
	public List<TipoContrato> listaTipoContrato() {
		@SuppressWarnings({ "unchecked", "deprecation" })
		List <TipoContrato> listaTipoContratos = (List<TipoContrato>)
				sessionFactory.getCurrentSession().createCriteria(TipoContrato.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		return listaTipoContratos;
	}

}
