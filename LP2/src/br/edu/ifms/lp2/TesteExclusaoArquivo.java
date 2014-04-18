package br.edu.ifms.lp2;

import java.io.File;

public class TesteExclusaoArquivo {

	public static void main(String[] args) {
		File arquivo = new File("C:\\Users\\sidsu\\Documents\\teste.txt");
		if (arquivo.delete()) {
			System.out.println("Arquivo excluído com sucesso.");
		} else {
			System.out.println("Ocorreu um erro ao tentar excluir o arquivo.");
		}
	}

}
