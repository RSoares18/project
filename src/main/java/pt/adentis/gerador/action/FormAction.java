package pt.adentis.gerador.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import pt.adentis.gerador.dao.CatProfissionalDAO;
import pt.adentis.gerador.dao.TipoContratoDAO;
import pt.adentis.gerador.dao.TipoPropostaDAO;
import pt.adentis.gerador.model.CatProfissional;
import pt.adentis.gerador.model.TipoContrato;
import pt.adentis.gerador.model.TipoProposta;

public class FormAction extends ActionSupport {
	
	public static String username;
	private CatProfissionalDAO catprofDAO;
	private TipoContratoDAO tipocontratoDAO;
	private TipoPropostaDAO tipopropostaDAO;
	private List <TipoProposta> listTipoPropostas;
	private List <TipoContrato> listTipoContratos;
	private List <CatProfissional> listCatProf;
	
	
	@Override
	public String execute() throws Exception {
		listTipoPropostas = tipopropostaDAO.listaTipoProposta();
		listTipoContratos = tipocontratoDAO.listaTipoContrato();
		listCatProf = catprofDAO.listaCatProfissional();
		
		
		if(checkLogin()) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
		
	}
	
	public CatProfissionalDAO getCatprofDAO() {
		return catprofDAO;
	}
	
	public TipoContratoDAO getTipocontratoDAO() {
		return tipocontratoDAO;
	}
	
	public TipoPropostaDAO getTipopropostaDAO() {
		return tipopropostaDAO;
	}
	
	public void setCatprofDAO(CatProfissionalDAO catprofDAO) {
		this.catprofDAO = catprofDAO;
	}
	
	public void setTipocontratoDAO(TipoContratoDAO tipocontratoDAO) {
		this.tipocontratoDAO = tipocontratoDAO;
	}
	
	public void setTipopropostaDAO(TipoPropostaDAO tipopropostaDAO) {
		this.tipopropostaDAO = tipopropostaDAO;
	}
	
	public List<CatProfissional> getListCatProf() {
		return listCatProf;
	}
	
	public List<TipoContrato> getListTipoContratos() {
		return listTipoContratos;
	}
	
	public List<TipoProposta> getListTipoPropostas() {
		return listTipoPropostas;
	}
	
	public boolean checkLogin() {
		return true;
	}
	
	
	

}
