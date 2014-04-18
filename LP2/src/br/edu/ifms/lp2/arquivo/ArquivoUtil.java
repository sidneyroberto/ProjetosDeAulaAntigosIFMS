package br.edu.ifms.lp2.arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArquivoUtil {

	public void gravaTextoNoArquivo(File arquivo, String conteudo,
			boolean anexar) {
		if (arquivo != null) {
			try {
				FileWriter gravadorArquivo = new FileWriter(arquivo, anexar);
				gravadorArquivo.write(conteudo);
				gravadorArquivo.close();
			} catch (IOException e) {
				System.out
						.println("Conteudo nao pode ser gravado no arquivo especificado.");
				e.printStackTrace();
			}
		}
	}

	public String leArquivoLinhaALinhaAlternativo(File arquivo) {
		if (arquivo != null && arquivo.exists()) {
			try {
				FileReader leitorArquivo = new FileReader(arquivo);
				BufferedReader leitorBuferizado = new BufferedReader(
						leitorArquivo);
				String textoArquivo = "";
				String linhaArquivo = null;
				while ((linhaArquivo = leitorBuferizado.readLine()) != null) {
					textoArquivo += linhaArquivo + "\n";
				}
				leitorBuferizado.close();
				return textoArquivo;
			} catch (Exception e) {
				System.out.println("Arquivo nao pode ser lido!");
				e.printStackTrace();
			}
		}
		return "";
	}

	public String leArquivoLinhaALinha(File arquivo) {
		if (arquivo != null) {
			try {
				String textoArquivo = "";
				Scanner leitor = new Scanner(arquivo);
				while (leitor.hasNextLine()) {
					textoArquivo += leitor.nextLine() + "\n";
				}
				leitor.close();
				return textoArquivo;
			} catch (FileNotFoundException e) {
				System.out.println("Arquivo nao pode ser lido!");
				e.printStackTrace();
			}
		}
		return "";
	}

	public String leArquivoTokenAToken(File arquivo) {
		if (arquivo != null) {
			try {
				String textoArquivo = "";
				Scanner leitor = new Scanner(arquivo);
				while (leitor.hasNext()) {
					textoArquivo += leitor.next() + "\n";
				}
				leitor.close();
				return textoArquivo;
			} catch (FileNotFoundException e) {
				System.out.println("Arquivo nao pode ser lido!");
				e.printStackTrace();
			}
		}

		return "";
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

	public Double calculaTamanhoArquivoEmMegaBytes(File arquivo) {
		if (arquivo != null && arquivo.exists()) {
			long tamanhoArquivoEmBytes = arquivo.length();
			return tamanhoArquivoEmBytes / 1048576D;
		}
		// Caso o arquivo não exista, retorna um valor de tamanho inválido
		return -1D;
	}

	public void varreDiretorios(String caminhoDiretorio) {
		File arquivo = abreArquivo(caminhoDiretorio);
		if (arquivo != null && arquivo.exists() && arquivo.isDirectory()) {
			varreDiretorios(arquivo, "");
		}
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
						System.out.println(tabulacoes + subDiretorio.getPath());
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		File arquivo = new File(
				"/home/suesid/Ubuntu One/IFMS/2013/SegundoSemestre/LP3_Tecnico/maisNovo.txt");
		String conteudo = "Este é o conteudo do arquivo.\nPulei uma linha.";
		ArquivoUtil arquivoUtil = new ArquivoUtil();
		arquivoUtil.gravaTextoNoArquivo(arquivo, conteudo, true);
		System.out.println("Feito.");
	}
}
