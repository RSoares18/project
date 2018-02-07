package pt.adentis.gerador.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import pt.adentis.gerador.model.CatProfissional;

public class CatProfissionalDAOImpl implements CatProfissionalDAO {
	
	private SessionFactory sessionFactory;
	
	public CatProfissionalDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<CatProfissional> listaCatProfissional() {
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CatProfissional> listaCatProf =(List <CatProfissional>)sessionFactory.getCurrentSession()
				.createCriteria(CatProfissional.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		// TODO Auto-generated method stub
		return listaCatProf;
	}

}
