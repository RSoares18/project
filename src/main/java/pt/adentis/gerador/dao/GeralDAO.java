package pt.adentis.gerador.dao;

import java.util.List;

import pt.adentis.gerador.model.Geral;

public interface GeralDAO {
	
	List <Geral> listaPropostas();
	public boolean insereProposta();
	public boolean getProposta();
	

}
