package br.edu.ifms.extensao.testemongodb.transport;

import java.io.File;

public class DocumentoTO {

	private String extensao;

	private String nomeArquivo;

	private Integer numeroPaginas;

	private String hash;

	private String conteudoTextual;

	private File arquivo;

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}

	public String getConteudoTextual() {
		return conteudoTextual;
	}

	public void setConteudoTextual(String conteudoTextual) {
		this.conteudoTextual = conteudoTextual;
	}

}
