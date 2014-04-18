package br.edu.ifms.lp4.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifms.lp4.jpa.dao.FilmeDao;
import br.edu.ifms.lp4.model.Filme;

@WebServlet(name = "cadastro", urlPatterns = { "/cadastro" })
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CadastroServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		String duracaoStr = request.getParameter("duracao");
		String genero = request.getParameter("genero");
		String resposta = "";
		if (!titulo.isEmpty() && !duracaoStr.isEmpty() && !genero.isEmpty()) {
			try {
				int duracao = Integer.parseInt(duracaoStr);
				Filme filme = new Filme();
				filme.setTitulo(titulo);
				filme.setDuracao(duracao);
				filme.setGenero(genero);

				FilmeDao filmeDao = new FilmeDao();
				filmeDao.salva(filme);

				resposta = "Salvo com sucesso!";
			} catch (Exception e) {
				e.printStackTrace();
				resposta = "Ocorreu um erro ao tentar realizar o cadastro.";
			}
		} else {
			resposta = "Dados incompletos!";
		}
		response.sendRedirect("index.jsp?resposta=" + resposta);
	}
}
