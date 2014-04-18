<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="br.edu.ifms.lp4.jpa.dao.FilmeDao"%>
<%@page import="br.edu.ifms.lp4.model.Filme"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" href="styles/principal.css" />
<title>Filmes</title>
</head>
<body>
	<div id="tudo">
		<div id="topo"><h1>Filmes</h1></div>
		<div id="menu">
			<ul>
				<li>MENU</li>
				<li><a href="index.jsp">Cadastrar</a></li>
				<li><a href="lista.jsp">Exibir</a></li>
			</ul>
		</div>
		<div id="conteúdo">
			<h2>Filmes cadastrados</h2>
			
			<br />
			<table border="1">
				<thead>
					<tr>
						<th>T&iacute;tulo</th>
						<th>Dura&ccedil;&atilde;o</th>
						<th>G&ecirc;nero</th>
					</tr>
				</thead>
				<tbody>
				<%
					FilmeDao filmeDao = new FilmeDao();
					List<Filme> filmes = filmeDao.recuperaTodos();
					for(Filme filme : filmes) {
				%>
						<tr>
							<td><%=filme.getTitulo() %></td>
							<td><%=filme.getDuracao() %></td>
							<td><%=filme.getGenero() %></td>
						</tr>
				<%
					}
				%>
				</tbody>
			</table>
		</div>
		<div id="rodape"><p>&reg; Todos os direitos reservados | <a href="#">Web Designer</a></p></div>
	</div>
</body>
</html>