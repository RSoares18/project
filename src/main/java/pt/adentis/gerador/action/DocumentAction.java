package pt.adentis.gerador.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import pt.adentis.gerador.model.Geral;


public class DocumentAction extends HttpServlet{
	
	
	private SessionFactory sessionFactory;
	private String colaborador;
	private String solicitador;
	private String data;
	private String localizacao;
	private float salbruto;
	private float salliquido;
	private float salliqanual;
	private float subalimentacao;
	private float plbeneficios;
	private String tipop;
	private String tipoc;
	private String catp;
	private List <String> fields;
	XWPFDocument doc;
	FileOutputStream out1;
	XWPFParagraph para;
	XWPFRun run;
	TemplateBuilder tb;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		tb = new TemplateBuilder();
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		fields = new ArrayList<String>();
		
		getFields(req);
		
		String suc = "DOCUMENTO CRIADO COM SUCESSO";
		
		buildFields();
		
		writeDoc();
		insertForm();
		
		out.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Document</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<div align =\"center\">\r\n" + 
				"<h1> Documento Gerado</h1>\r\n" + suc +
				"\r\n" + 
				"\r\n" + 
				"</div>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"</body>\r\n" + 
				"</html>");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	public void getFields(HttpServletRequest req) {
		colaborador = req.getParameter("destino");
		solicitador = req.getParameter("solicitador");
		data = req.getParameter("data");
		tipop = req.getParameter("tipoproposta");
		tipoc= req.getParameter("tipocontrato");
		catp = req.getParameter("tipocatprof");
		localizacao = req.getParameter("localizacao");
		salbruto = Float.parseFloat(req.getParameter("salariobruto"));
		salliquido = Float.parseFloat(req.getParameter("salarioliquido"));
		salliqanual = Float.parseFloat(req.getParameter("salarioliqanual"));
		subalimentacao = Float.parseFloat(req.getParameter("subalimentacao"));
		plbeneficios = Float.parseFloat(req.getParameter("plbeneficios"));
		
		
		
	}
	
	public void buildFields() {
		String colab = colaborador; 
		String solic = solicitador; 
		String date = data;  
		String tp = tipop.toUpperCase();
		String tc = tipoc.toUpperCase();
		String cp = catp.toUpperCase();
		String salbr = ""+salbruto + "€";
		String sallq = ""+salliquido + "€";
		String sallqan = "" + salliqanual + "€";
		String subali = "" + subalimentacao + "€"; 
		String plben =	""+ plbeneficios + "€";
		String local = localizacao;
		
		fields.add(colab);
		fields.add(solic);
		fields.add(date);
		fields.add(tp);
		fields.add(tc);
		fields.add(cp);
		fields.add(salbr);
		fields.add(sallq);
		fields.add(sallqan);
		fields.add(subali);
		fields.add(plben);
		fields.add(local);
		
	}
	
	public void writeTemplate() {

		doc = new XWPFDocument();
		try {
			out1 = new FileOutputStream(new File("C:\\Users\\" + System.getProperty("user.name") +"\\Desktop\\proposta_" + colaborador + ".docx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		para = doc.createParagraph();
		run = para.createRun();
		
		for(String s : fields) {
			XWPFRun r = (doc.createParagraph()).createRun();
			r.setText(s);
		}
		
		try {
			doc.write(out1);
			out1.close();
			doc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public XWPFDocument createDoc() {
		try {
			InputStream in = new FileInputStream("C:\\Users\\" + System.getProperty("user.name") +"\\Desktop\\template.docx");
			XWPFDocument docx = new XWPFDocument(in);
			
			List<XWPFParagraph> par = docx.getParagraphs();
			
			XWPFDocument newDoc = new XWPFDocument();
			
			for(XWPFParagraph p : par) {
				if(!p.getParagraphText().isEmpty()) {
					XWPFParagraph newP = newDoc.createParagraph();
					copyText(p,newP);
				}
			}
			
			FileOutputStream out = new FileOutputStream(new File("C:\\Users\\" + System.getProperty("user.name") +"\\Desktop\\exemplo" + " - " + colaborador +".docx"));
			newDoc.write(out);
			out.flush();
			out.close();
			return newDoc;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void copyText(XWPFParagraph oldP, XWPFParagraph newP) {
		final int FONT = 10;
		
		for(XWPFRun r : oldP.getRuns()) {
			String text = r.getText(0);
			if(text == null || text.isEmpty()) {
				continue;
			}
			int fs = r.getFontSize();
			
			XWPFRun newR = newP.createRun();
			
			newR.setText(text);
			
			newR.setFontSize((fs == -1) ? FONT : r.getFontSize());
			newR.setFontFamily(r.getFontFamily());
			newR.setBold(r.isBold());
			newR.setItalic(r.isItalic());
			newR.setColor(r.getColor());
		}
	}
	
	public void writeDoc() {
		XWPFDocument ex = createDoc();
		try {
//			ex = new XWPFDocument(OPCPackage.open("C:\\Users\\" + System.getProperty("user.name") +"\\Desktop\\exemplo.docx"));
//			XWPFDocument t1 = ex;
		System.out.println("FICHEIRO ABERTO: ");
		System.out.println("TAMANHO " + ex.getTables().size());
//	    for (XWPFTable tbl : ex.getTables()) {
//	    	System.out.println("TABELAS CICLO" + "\n");
//	        for (XWPFTableRow row : tbl.getRows()) {
//	        	System.out.println("LINHAS CICLO" + "\n");
//	            for (XWPFTableCell cell : row.getTableCells()) {
//	            	System.out.println("CELULAS CICLO" + "\n");
		
		
	                for (XWPFParagraph p : ex.getParagraphs()) {
	                	System.out.println("Parágrafo: " + p.getText());
	                	System.out.println("PARAGRAFOS CICLO" + "\n");
	                	
	                	if(p.getRuns().isEmpty()) {
                    		XWPFRun run = p.createRun();
                    		run.setText("\n");
                    	}
	                	
	                    for (XWPFRun r : p.getRuns()) {

	                    	System.out.println("RUNS CICLO" + "\n");
	                    	
	                    	subProposta(r);
	                    	subSolicitador(r);
	                    	subColaborador(r);
	                    	subContrato(r);
	                    	subLocalizacao(r);
	                    	subSalLiq(r);
	                    	subSalLiqAnual(r);
	                    	subSalBruto(r);
	                    	subPlBen(r);
	                    	checkPageEnd(r);
	               
	                    }

//	                }
//	            }
//	        }
	    }
	    ex.write(new FileOutputStream("C:\\Users\\" + System.getProperty("user.name") +"\\Documents\\"+ colaborador + ".docx"));
	    ex.close();
		} catch (IOException e) {
			System.out.println("ERROOOOOOO");
			e.printStackTrace();
		}
	
	}
	
	private void checkPageEnd(XWPFRun r) {
		String text = r.getText(0);
        System.out.println("TEXTO: " + text);
        
        if (text != null && text.contains("--end")) {
        	text = text.replace("--end",
                    "");
            r.setText(text, 0);
        	r.addCarriageReturn();                 
        	r.addBreak(BreakType.PAGE);
        }
		
	}

	private void subPlBen(XWPFRun r) {
		String text = r.getText(0);
        System.out.println("TEXTO: " + text);
        
        if (text != null && text.contains("var_pl_ben")) {
            text = text.replace("var_pl_ben",
                    fields.get(10));
            r.setText(text + " ", 0);
            System.out.println("var_pl_ben SUBSTITUIDO POR " + fields.get(10));
        
        }
        
		
	}

	private void subSalBruto(XWPFRun r) {
		String text = r.getText(0);
        System.out.println("TEXTO: " + text);
        
        if (text != null && text.contains("var_sal_bruto")) {
            text = text.replace("var_sal_bruto",
                    fields.get(6));
            r.setText(text + " ", 0);
            System.out.println("var_sal_bruto SUBSTITUIDO POR " + fields.get(6));
        
        }
		
	}

	private void subSalLiqAnual(XWPFRun r) {
		String text = r.getText(0);
        System.out.println("TEXTO: " + text);
        
        if (text != null && text.contains("Var_sal_ano")) {
            text = text.replace("Var_sal_ano",
                    fields.get(8));
            r.setText(text + " ", 0);
            System.out.println("Var_sal_ano SUBSTITUIDO POR " + fields.get(8));
        
        }
		
	}

	private void subSalLiq(XWPFRun r) {
		String text = r.getText(0);
        System.out.println("TEXTO: " + text);
        
        if (text != null && text.contains("var_sal_liq")) {
            text = text.replace("var_sal_liq",
                    fields.get(7));
            r.setText(text + " ", 0);
            System.out.println("var_sal_liq SUBSTITUIDO POR " + fields.get(7));
        
        }
		
	}

	private void subLocalizacao(XWPFRun r) {
		String text = r.getText(0);
        System.out.println("TEXTO: " + text);
        
        if (text != null && text.contains("var_localizacao")) {
            text = text.replace("var_localizacao",
                    fields.get(11));
            r.setText(text + " ", 0);
            System.out.println("var_localizacao SUBSTITUIDO POR " + fields.get(11));
        
        }
		
	}

	private void subContrato(XWPFRun r) {
		String text = r.getText(0);
        System.out.println("TEXTO: " + text);
        
        if (text != null && text.contains("var_tp_contrato")) {
            text = text.replace("var_tp_contrato",
                    fields.get(4));
            r.setText(text + " ", 0);
            System.out.println("var_tp_contrato SUBSTITUIDO POR " + fields.get(4));
        
        }
		
	}

	private void subColaborador(XWPFRun r) {
		String text = r.getText(0);
        System.out.println("TEXTO: " + text);

        if (text != null && text.contains("var_colaborador")) {
            text = text.replace("var_colaborador",
                    fields.get(0));
            r.setText(text + " ", 0);
            System.out.println("var_colaborador SUBSTITUIDO POR " + fields.get(0));
        
        }
		
	}

	private void subSolicitador(XWPFRun r) {
		String text = r.getText(0);
        System.out.println("TEXTO: " + text);
        
        if (text != null && text.contains("var_solicitador")) {
            text = text.replace("var_solicitador",
                    fields.get(1));
            r.setText(text + " ", 0);
            System.out.println("var_solicitador SUBSTITUIDO POR " + fields.get(1));

        }
		
	}

	private void subProposta(XWPFRun r) {
		String text = r.getText(0);
        System.out.println("TEXTO: " + text);
        
        if (text != null
                && text.contains("var_tp_proposta")) {
            text = text.replace("var_tp_proposta",
                   fields.get(3));
            r.setText(text + " ", 0);
            System.out.println("var_tp_proposta SUBSTITUIDO POR " + fields.get(3));

        }
		
	}

	public void insertForm() {
		Configuration cf = new Configuration();
		sessionFactory = cf.configure("hb.cfg.xml").buildSessionFactory();
		Session ss = sessionFactory.openSession();
		Transaction tx = null;
		Integer geralID = null;
		
		try {
			tx = ss.beginTransaction();
			Geral gr = new Geral(tipop, tipoc, colaborador, solicitador, localizacao, 
					salbruto, salliquido, salliqanual, subalimentacao, 
					plbeneficios, catp );
			geralID =(Integer) ss.save(gr);
			tx.commit();
		} catch (Exception e) {
			if(tx != null )tx.rollback();
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			ss.close();
		}
		
	}
	
	public void checkCatProf(XWPFRun run) {
		String txt = run.getText(0);
		
		
	}

}
