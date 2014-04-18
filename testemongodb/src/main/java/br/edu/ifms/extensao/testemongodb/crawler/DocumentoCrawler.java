package br.edu.ifms.extensao.testemongodb.crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifms.extensao.testemongodb.dao.DocumentoDao;
import br.edu.ifms.extensao.testemongodb.transport.DocumentoTO;
import br.edu.ifms.extensao.testemongodb.util.CompressionUtil;
import br.edu.ifms.extensao.testemongodb.util.PdfUtil;

public class DocumentoCrawler {

	private static final List<String> EXTENSOES;
	static {
		EXTENSOES = new ArrayList<String>();
		EXTENSOES.add("pdf");
	}

	public File abreArquivo(String caminhoArquivo) {
		File arquivo = new File(caminhoArquivo);
		if (arquivo.exists()) {
			return arquivo;
		}
		return null;
	}

	public File criaArquivo(String caminhoArquivo) {
		return abreArquivo(caminhoArquivo) == null ? new File(caminhoArquivo)
				: null;
	}

	public String pegaExtensaoArquivo(File arquivo) {
		if (arquivo != null && arquivo.exists()) {
			String nomeArquivo = arquivo.getName().trim();
			String[] aux = nomeArquivo.split("\\.");
			return aux.length > 0 ? aux[aux.length - 1] : "";
		}
		return "";
	}

	public void varreDiretorios(String caminhoDiretorio) {
		File arquivo = abreArquivo(caminhoDiretorio);
		if (arquivo != null && arquivo.exists() && arquivo.isDirectory()) {
			varreDiretorios(arquivo, "");
		}
	}

	private DocumentoTO preencheDocumento(File arquivo) {
		PdfUtil pdfUtil = new PdfUtil(arquivo);
		DocumentoTO documento = new DocumentoTO();
		documento.setArquivo(arquivo);
		documento.setExtensao("pdf");
		String texto = pdfUtil.extraiTextoDocumentoPdf();
		String hash = CompressionUtil.generateMd5Hash(texto);
		documento.setConteudoTextual(texto);
		documento.setHash(hash);
		documento.setNomeArquivo(arquivo.getName());
		documento.setNumeroPaginas(pdfUtil.numeroPaginas());
		return documento;
	}

	private void varreDiretorios(File diretorio, String tabulacoes) {
		String[] listaDiretorios = diretorio.list();
		if (listaDiretorios != null && listaDiretorios.length > 0) {
			for (String nomeSubDiretorio : diretorio.list()) {
				nomeSubDiretorio = diretorio.getPath() + "/" + nomeSubDiretorio;
				File subDiretorio = abreArquivo(nomeSubDiretorio);
				if (subDiretorio != null && subDiretorio.exists()) {
					if (subDiretorio.isDirectory()) {
						varreDiretorios(subDiretorio, tabulacoes + "\t");
					} else {
						String extensao = pegaExtensaoArquivo(subDiretorio);
						if (contemExtensao(extensao)) {
							try {
								DocumentoTO documento = preencheDocumento(subDiretorio);
								DocumentoDao documentoDao = new DocumentoDao();
								if (documentoDao.recupera(documento.getHash(),
										documento.getExtensao()) == null) {
									System.out.println("Enviando o arquivo "
											+ documento.getNomeArquivo()
											+ " para a indexação...");
									documentoDao.salva(documento);
									System.out.println("Arquivo indexado!");
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	private boolean contemExtensao(String extensaoProcurada) {
		for (String extensao : EXTENSOES) {
			if (extensao.equalsIgnoreCase(extensaoProcurada)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		DocumentoCrawler crawler = new DocumentoCrawler();
		crawler.varreDiretorios("F:\\");
	}
}
