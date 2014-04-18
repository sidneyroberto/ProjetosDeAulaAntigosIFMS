package br.edu.ifms.lp3.ui.model;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class TabelaClienteModel extends DefaultTableModel {

	public static final String[] NOMES_COLUNAS = { "CPF", "NOME", "ENDEREÇO",
			"SEXO" };

	public TabelaClienteModel(String[][] dados) {
		super(dados, NOMES_COLUNAS);
	}

	public TabelaClienteModel() {
		super();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
