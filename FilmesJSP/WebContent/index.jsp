<?xml version="1.0" encoding="ISO-8859-1" ?>
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
			<%
				String resposta = request.getParameter("resposta");
				resposta = resposta == null ? "" : resposta;
			%>
			<%=resposta %>	
			<br />
			<br />
			<form action="cadastro">
				<label for="titulo">T&iacute;tulo:</label>
				<input type="text" name="titulo" id="titulo" /> <br />
				<label for="duracao">Dura&ccedil;&atilde;o:</label>
				<input type="text" name="duracao" id="duracao" />  <br />
				<label for="genero">G&ecirc;nero:</label>
				<select name="genero" id="genero">
					<option value="" selected="selected">-- Escolha um g&ecirc;nero --</option>
					<option value="Drama">Drama</option>
					<option value="Comédia">Com&eacute;dia</option>
					<option value="Terror">Terror</option>
				</select>
				<br />
				<input type="submit" value="Cadastrar" />
			</form>
			
			<br />
			<br />
		</div>
		<div id="rodape"><p>&reg; Todos os direitos reservados | <a href="#">Web Designer</a></p></div>
	</div>
</body>
</html>