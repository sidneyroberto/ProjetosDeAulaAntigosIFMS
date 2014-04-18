package br.edu.ifms.lp3.business;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import br.edu.ifms.lp3.dao.jpa.ClienteDao;
import br.edu.ifms.lp3.exception.ClienteInvalidoException;
import br.edu.ifms.lp3.model.Cliente;
import br.edu.ifms.lp3.ui.model.TabelaClienteModel;
import br.edu.ifms.lp3.validator.ClienteValidator;

public class ClienteBO {

	private ClienteDao clienteDao = new ClienteDao();

	public boolean excluiCliente(Cliente cliente) {
		return clienteDao.remove(cliente);
	}

	public JTable preencheTabelaDeClientes(JTable tabela, String nomeBusca) {
		tabela.setVisible(true);
		tabela.setModel(new TabelaClienteModel(new String[][] {}));
		List<Cliente> clientes = new ArrayList<Cliente>();
		if (nomeBusca != null && !nomeBusca.isEmpty()) {
			clientes = clienteDao.recuperaTodosPorNome(nomeBusca);
		} else {
			clientes = clienteDao.recuperaTodos();
		}
		TabelaClienteModel modelo = (TabelaClienteModel) tabela.getModel();
		modelo.setRowCount(0);
		int[] maioresLarguras = { 0, 0, 0, 0 };
		for (Cliente cliente : clientes) {
			int numeroDeCampos = TabelaClienteModel.NOMES_COLUNAS.length;
			String[] dados = new String[numeroDeCampos];
			dados[0] = cliente.getCpf();
			dados[1] = cliente.getNome();
			dados[2] = cliente.getEndereco();
			dados[3] = cliente.getSexo();

			for (int i = 0; i < numeroDeCampos; i++) {
				if (dados[i].length() > (maioresLarguras[i] / 6)) {
					maioresLarguras[i] = dados[i].length() * 6;
				}
			}

			for (int i = 0; i < numeroDeCampos; i++) {
				tabela.getColumnModel().getColumn(i)
						.setPreferredWidth(maioresLarguras[i]);
			}

			modelo.addRow(dados);
		}
		tabela.setModel(modelo);
		return tabela;
	}

	public JTable preencheTabelaDeClientes(JTable tabela) {
		return preencheTabelaDeClientes(tabela, null);
	}

	public void salvaCliente(Cliente cliente) throws ClienteInvalidoException {
		validaCliente(cliente);
		clienteDao.salva(cliente);
	}

	private void validaCliente(Cliente cliente) throws ClienteInvalidoException {
		if (cliente == null) {
			throw new ClienteInvalidoException("Objeto nulo!");
		}

		if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
			throw new ClienteInvalidoException("CPF não preenchido!");
		}

		if (!ClienteValidator.validaCPF(cliente.getCpf())) {
			throw new ClienteInvalidoException("CPF inválido!");
		}

		if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
			throw new ClienteInvalidoException("NOME não preenchido!");
		}

		if (cliente.getEndereco() == null || cliente.getEndereco().isEmpty()) {
			throw new ClienteInvalidoException("ENDEREÇO não preenchido!");
		}

		if (cliente.getSexo() == null || cliente.getSexo().isEmpty()) {
			throw new ClienteInvalidoException("SEXO não preenchido!");
		}
	}
}
