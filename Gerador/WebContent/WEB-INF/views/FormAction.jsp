<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ page import ="pt.adentis.gerador.action.FormAction" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import ="java.math.BigDecimal" import = "java.math.RoundingMode"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">

<script type=”text/javascript” src=”bootstrap/js/bootstrap.min.js”></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<link href="css/form.css" rel ="stylesheet"/>
<%-- <style>
     <%@ include file="form.css"%>
</style> --%>
<title>Formulário Proposta</title>
</head>
<body>

<% String username1 = request.getParameter("username"); 
	LocalDate data = LocalDate.now();
	FormAction.username = request.getParameter("username");
	
	int diasTrabalho = 21;
	double valorDiario = 7.23;
	
	double subalimentacao = valorDiario * diasTrabalho;
	
	double salariobruto;
	
	if(request.getParameter("salariobruto")==null){
		salariobruto = 0.0;
	} else{
		salariobruto = Double.parseDouble(request.getParameter("salariobruto"));
	}
	
/* 		double plbeneficios = (0.2 * salariobruto); */

	double plbeneficios = BigDecimal.valueOf(0.2 * salariobruto)
		    .setScale(2, RoundingMode.HALF_UP)
		    .doubleValue();
			
%>



		<div class ="row">
			<div class="col-md-3" style="text-align: center;">
				<h2> Bem-Vindo, <%=username1 %></h2>
			</div>
			<div class="col-md-6" style="text-align: center;">
				<h2> Preencha o seguinte formulário:</h2>
			</div>
			<div class="col-md-3" style="text-align: right; padding-right: 64px;">
		 		<img src ="adlogo1.jpg" alt="adentis">
		 	</div>
		</div>
		
<%-- 		<s:form class ="fields" action ="DocumentAction" method="post"> --%>
		
		<form class = "form-horizontal" action ="DocumentAction" method ="post">
			<div class ="form-group">
			<div class="row">
			<label class ="control-label col-sm-2" for="solicitador">Solicitador de Proposta</label>
				<div class="col-sm-8">
					<input type ="text" class="form-control" id ="solicitador" name="solicitador" value ="${param.username}" readonly="readonly"/>
				</div>
				</div>
			</div>
			<div class ="form-group">
			<div class ="row">
			<label class ="control-label col-sm-2" for="data">Data da Proposta</label>
				<div class="col-sm-8">
					<input type ="text" class="form-control" id ="data" name="data" value ="<%=data %>" readonly="readonly"/>
				</div>
				</div>
			</div>
			<div class ="form-group">
			<div class="row">
			<label class ="control-label col-sm-2" for="data">Localização: </label>
				<div class="col-sm-8">
					<input type ="text" class="form-control" id ="localizacao" placeholder ="Introduza a localização" name="localizacao" />
				</div>
				</div>
			</div>
			<br>
			<div class ="form-group">
				<div class ="row" style="margin-left: 308px;">
					<div class="col-sm-3">
						<s:select 
						headerKey="-1" headerValue="Seleccione o Tipo de proposta"
						list="listTipoPropostas"
						listKey="p_tipo"
						listValue="p_tipo"
						name="tipoproposta"/>
					</div>
		
		
					<div class="col-sm-3">
						<s:select 
						headerKey="-1" headerValue="Seleccione o Tipo de contrato"
						list="listTipoContratos"
						listKey="c_tipo"
						listValue="c_tipo"
						name="tipocontrato"/>
					</div>
		
		
					<div class="col-sm-3">
						<s:select 
						headerKey="-1" headerValue="Categoria profissional"
						list="listCatProf"
						listKey="n_cat_prof"
						listValue="n_cat_prof"
						name="tipocatprof"/>
					</div>
				</div>
			</div>
			<br>
			<div class ="form-group">
			<div class ="row">
			<label class ="control-label col-sm-2" for="destino">Nome do Colaborador</label>
				<div class="col-sm-4">
					<input type ="text" class="form-control" id ="destino" name="destino"/>
				</div>
				</div>
			</div>
			<div class ="form-group">
			<div class="row">
			<label class ="control-label col-sm-2" for="salariobruto">Salário Bruto: </label>
				<div class="col-sm-4">
					<input type ="text" class="form-control" id ="salariobruto" name="salariobruto" /> 
				</div>
				<label class ="control-label col-sm-1">&#8364</label>
				</div>
			</div>
			<div class ="form-group">
			<div class="row">
			<label class ="control-label col-sm-2" for="salarioliquido">Salário Líquido: </label>
				<div class="col-sm-4">
					<input type ="text" class="form-control" id ="salarioliquido" name="salarioliquido" />	
				</div>
				<label class ="control-label col-sm-1">&#8364</label>
				</div>
			</div>
			<div class ="form-group">
			<div class="row">
			<label class ="control-label col-sm-2" for="salarioliqanual">Salário Líquido Anual: </label>
				<div class="col-sm-4">
					<input type ="text" class="form-control" id ="salarioliqanual" name="salarioliqanual" />
				</div>
				<label class ="control-label col-sm-1">&#8364</label>
				</div>
			</div>
			<div class ="form-group">
			<div class="row">
			<label class ="control-label col-sm-2" for="subalimentacao">Subsídio de Alimentação: </label>
				<div class="col-sm-4">
					<input type ="text" class="form-control" id ="subalimentacao" name="subalimentacao" value = <%= subalimentacao %> step ="0.01" readonly="readonly" />
				</div>
				<label class ="control-label col-sm-1">&#8364</label>
				</div>
			</div>
			<div class ="form-group">
			<div class="row">
			<label class ="control-label col-sm-2" for="plbeneficios">Plano de Benefícios </label>
				<div class="col-sm-4">
					<input type ="text" class="form-control" id ="plbeneficios" name="plbeneficios" value = <%= plbeneficios %> step ="0.01" readonly="readonly" />
				</div>
				<label class ="control-label col-sm-1">&#8364</label>
				</div>
			</div>
			
		
		<div class="col-sm-offset-2 col-sm-10">
		<input type ="submit" value ="Gerar Proposta"/>
		</div>
		
		</form>
		
<%-- 		<form action ="DocumentAction" method ="post">
		<div class ="row">
			<div class="col-sm-2"> Solicitador de Proposta: </div>
			<div class="col-sm-1"><input type = "text" name="solicitador" value= "${param.username}" readonly="readonly" /> </div>
		</div>
		<div class ="row">
			<div class="col-sm-2"> Data da Proposta: </div>
			<div class="col-sm-1"><input type = "text" name="data" value= "<%=data %>" readonly="readonly" /> </div>
		</div>
		<div class ="row">
			<div class="col-sm-2"> Localização: </div>
			<div class="col-sm-1"><input type = "text" name="localizacao"/> </div>
		</div>
		<br>


		
			<div class="col-sm-4"><s:select label = "Seleccione o Tipo de Proposta"
			headerKey="-1" headerValue="Seleccione um tipo de proposta"
			list="listTipoPropostas"
			listKey="p_tipo"
			listValue="p_tipo"
			name="tipoproposta"/></div>


			<div class="col-sm-4"><s:select label = "Seleccione o Tipo de Contrato"
			headerKey="-1" headerValue="Seleccione um tipo de contrato"
			list="listTipoContratos"
			listKey="c_tipo"
			listValue="c_tipo"
			name="tipocontrato"/></div>


			<div class="col-sm-4"><s:select label = "Seleccione a Categoria Profissional"
			headerKey="-1" headerValue="Seleccione uma categoria profissional"
			list="listCatProf"
			listKey="n_cat_prof"
			listValue="n_cat_prof"
			name="tipocatprof"/></div> --%>
		
<%-- 		<div class ="row">
			<div class ="col-sm-2">Nome do Colaborador:</div>
			<div class ="col-sm-1"><input type = "text" name="destino"/></div>
		</div>
		
		<div class ="row">
			<div class ="col-sm-2">Salário Bruto:</div>
			<div class ="col-sm-2"><input type ="text" name="salariobruto" value = <%= salariobruto %> step ="0.01"/> &#8364</div>
		</div>
		
		<div class ="row">
			<div class ="col-sm-2">Salário Líquido:</div>
			<div class ="col-sm-2"><input type = "text" name="salarioliquido" required /> &#8364 </div>
		</div>
		
		<div class ="row">
			<div class ="col-sm-2">Salário Líquido Anual:</div>
			<div class ="col-sm-2"><input type = "text" name="salarioliqanual" required/> &#8364 </div>
		</div>
		
		<div class ="row">
			<div class ="col-sm-2">Subsídio de Alimentação:</div>
			<div class ="col-sm-2"><input type ="text" name="subalimentacao" value = <%= subalimentacao %> step ="0.01" readonly="readonly" required/> &#8364 </div>
		</div>
		
		<div class ="row">
			<div class ="col-sm-2">Plano de Benefícios:</div>
			<div class ="col-sm-2"><input type = "text" name="plbeneficios" value =<%=plbeneficios %> readonly="readonly" step="0.01"  /> &#8364  </div>
		</div> --%>

		
		
		<!-- </form> -->
<%-- 		</s:form> --%>

				
</body>
</html>