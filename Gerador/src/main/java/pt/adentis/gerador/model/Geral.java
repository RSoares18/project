package pt.adentis.gerador.model;

public class Geral {
	
	private int p_id;
	private String id_tipo;
	private String id_tipo_c;
	private String destino;
	private String u_gerador;
	private String localizacao;
	private float sal_base;
	private float sal_liq;
	private float sal_liq_ano;
	private float sub_ali;
	private float pl_ben;
	private String id_cat_pf;
	
	
	public Geral() {
	}
	public Geral(String tipo_p, String tipo_c, String destino, 
			String u_gerador, String localizacao, float sal_base,
			float sal_liq, float sal_liq_ano, float sub_ali,
			float pl_ben, String id_cat_prof) {
	
		this.id_tipo = tipo_p;
		this.id_tipo_c = tipo_c;
		this.destino =destino;
		this.u_gerador = u_gerador;
		this.localizacao = localizacao;
		this.sal_base = sal_base;
		this.sal_liq = sal_liq;
		this.sal_liq_ano = sal_liq_ano;
		this.sub_ali = sub_ali;
		this.pl_ben = pl_ben;
		this.id_cat_pf = id_cat_prof;
		
	}
	
	public int getP_id() {
		return p_id;
	}
	
	public String getId_tipo() {
		return id_tipo;
	}
	
	public String getId_tipo_c() {
		return id_tipo_c;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public String getU_gerador() {
		return u_gerador;
	}
	
	public String getLocalizacao() {
		return localizacao;
	}
	
	public float getSal_base() {
		return sal_base;
	}
	
	public float getSal_liq() {
		return sal_liq;
	}
	
	public float getSal_liq_ano() {
		return sal_liq_ano;
	}
	
	public float getSub_ali() {
		return sub_ali;
	}
	
	public float getPl_ben() {
		return pl_ben;
	}
	
	public String getId_cat_pf() {
		return id_cat_pf;
	}
	
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	
	public void setId_tipo(String id_tipo) {
		this.id_tipo = id_tipo;
	}
	
	public void setId_tipo_c(String id_tipo_c) {
		this.id_tipo_c = id_tipo_c;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public void setU_gerador(String u_gerador) {
		this.u_gerador = u_gerador;
	}
	
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
	public void setSal_base(float sal_base) {
		this.sal_base = sal_base;
	}
	
	public void setSal_liq(float sal_liq) {
		this.sal_liq = sal_liq;
	}
	
	public void setSal_liq_ano(float sal_liq_ano) {
		this.sal_liq_ano = sal_liq_ano;
	}
	
	public void setSub_ali(float sub_ali) {
		this.sub_ali = sub_ali;
	}
	
	public void setPl_ben(float pl_ben) {
		this.pl_ben = pl_ben;
	}
	
	public void setId_cat_pf(String id_cat_pf) {
		this.id_cat_pf = id_cat_pf;
	}
	
	

}
