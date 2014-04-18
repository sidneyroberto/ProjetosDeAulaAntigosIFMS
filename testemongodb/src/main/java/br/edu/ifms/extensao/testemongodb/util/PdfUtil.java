package br.edu.ifms.extensao.testemongodb.util;

import java.io.File;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;

public class PdfUtil {

	private PdfReader leitorPDF;

	public PdfUtil(File arquivoDocumento) {
		try {
			leitorPDF = new PdfReader(arquivoDocumento.getPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int numeroPaginas() {
		return leitorPDF.getNumberOfPages();
	}

	public String extraiTextoDocumentoPdf() {
		String textoArquivo = "";
		try {
			for (int i = 1; i <= numeroPaginas(); i++) {
				textoArquivo += extraiTextoPaginaPDF(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return textoArquivo;
	}

	private String extraiTextoPaginaPDF(int numeroPagina) {
		try {
			String conteudoPagina = PdfTextExtractor.getTextFromPage(leitorPDF,
					numeroPagina, new SimpleTextExtractionStrategy());
			byte[] bytesTexto = conteudoPagina.getBytes("UTF-8");
			for (int i = 0; i < bytesTexto.length; i++) {
				if (bytesTexto[i] == 0x00) {
					bytesTexto[i] = 95;
				}
			}
			conteudoPagina = new String(bytesTexto);
			return conteudoPagina;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
