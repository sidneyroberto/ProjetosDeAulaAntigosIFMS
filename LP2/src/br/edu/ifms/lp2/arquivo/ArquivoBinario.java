package br.edu.ifms.lp2.arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ArquivoBinario {

	public static void main(String[] args) {
		File arquivo = new File("/home/sidsu/aluno.alu");

		ArquivoBinario arquivoBinario = new ArquivoBinario();
		Aluno aluno = arquivoBinario.leRegistro(arquivo);
		System.out.println("Nro. Matrícula: " + aluno.getNumeroDeMatricula());
		System.out.println("Nome: " + aluno.getNome());
		System.out.println("Sexo: " + aluno.getSexo());
		System.out
				.println("Data de nascimento: " + aluno.getDataDeNascimento());
	}

	public void gravaNoArquivo(File arquivo, Aluno aluno) {
		if (arquivo != null) {
			try {
				FileOutputStream fout = new FileOutputStream(arquivo);
				ObjectOutputStream oos = new ObjectOutputStream(fout);
				oos.writeObject(aluno);
				oos.close();
			} catch (IOException e) {
				System.out
						.println("O registro não pode ser gravado no arquivo especificado!");
				e.printStackTrace();
			}
		}
	}

	public Aluno leRegistro(File arquivo) {
		if (arquivo != null && arquivo.exists()) {
			try {
				FileInputStream streamDoArquivo = new FileInputStream(arquivo);
				ObjectInputStream streamDoObjeto = new ObjectInputStream(
						streamDoArquivo);
				Aluno registro = (Aluno) streamDoObjeto.readObject();
				streamDoObjeto.close();
				return registro;
			} catch (Exception e) {
				System.out.println("Arquivo nao pode ser lido!");
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}