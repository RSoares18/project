package pt.adentis.gerador.model;

import java.time.LocalDate;

public class TipoContrato {
	
	private int id;
	private String c_tipo;
	
	public int getId() {
		return id;
	}

	
	public String getC_tipo() {
		return c_tipo;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public void setC_tipo(String c_tipo) {
		this.c_tipo = c_tipo;
	}

}
