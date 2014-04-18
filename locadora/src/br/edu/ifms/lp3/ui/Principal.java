package br.edu.ifms.lp3.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import br.edu.ifms.lp3.business.ClienteBO;
import br.edu.ifms.lp3.exception.ClienteInvalidoException;
import br.edu.ifms.lp3.model.Cliente;
import br.edu.ifms.lp3.ui.model.TabelaClienteModel;

public class Principal {

	private JFrame frame;
	private JTextField campoCPF;
	private JLabel labelNome;
	private JTextField campoNome;
	private JLabel labelEndereco;
	private JTextField campoEndereco;
	private JLabel labelSexo;
	private JPanel panel;
	private JRadioButton botaoMasculino;
	private JRadioButton botaoFeminino;
	private JButton botaoCadastrar;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel respostaCadastro;
	private JTable tabelaClientes;
	private JButton botaoLimpar;
	private JScrollPane scrollTabela;
	private JPopupMenu menuOpcoesTabela;
	private JMenuItem itemExcluir;

	private ClienteBO clienteBO = new ClienteBO();
	private JPanel painelBusca;
	private JTextField campoBusca;
	private JLabel lblFiltrarClientes;
	private JPanel painelBotoes;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		initialize();
	}

	private void limpaCampos() {
		campoCPF.setText("");
		campoNome.setText("");
		campoEndereco.setText("");
		botaoMasculino.setSelected(true);
		tabelaClientes.clearSelection();
	}

	private void cadastraCliente() {
		String cpf = campoCPF.getText();
		String nome = campoNome.getText();
		String endereco = campoEndereco.getText();
		String sexo = botaoMasculino.isSelected() ? "M" : "F";
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente.setNome(nome);
		cliente.setEndereco(endereco);
		cliente.setSexo(sexo);
		try {
			clienteBO.salvaCliente(cliente);
			respostaCadastro.setText("Cliente cadastrado!");
			respostaCadastro.setForeground(Color.BLUE);
			limpaCampos();
			preencheTabela(null);
		} catch (ClienteInvalidoException e) {
			String mensagemErro = e.getMessage();
			respostaCadastro.setText(mensagemErro);
			respostaCadastro.setForeground(Color.RED);
		}
	}

	private Cliente pegaClienteSelecionado() {
		int indiceCliente = tabelaClientes.getSelectedRow();
		TabelaClienteModel modelo = (TabelaClienteModel) tabelaClientes
				.getModel();
		String cpf = (String) modelo.getValueAt(indiceCliente, 0);
		String nome = (String) modelo.getValueAt(indiceCliente, 1);
		String endereco = (String) modelo.getValueAt(indiceCliente, 2);
		String sexo = (String) modelo.getValueAt(indiceCliente, 3);
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente.setNome(nome);
		cliente.setEndereco(endereco);
		cliente.setSexo(sexo);
		return cliente;
	}

	private void excluiCliente() {
		Cliente cliente = pegaClienteSelecionado();
		if (JOptionPane.showConfirmDialog(frame,
				"Deseja realmente excluir o cliente " + cliente.getNome()) == JOptionPane.YES_OPTION) {
			if (clienteBO.excluiCliente(cliente)) {
				preencheTabela(null);
				JOptionPane.showMessageDialog(frame,
						"Cliente excluído com sucesso!", "Confirmação",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame,
						"Ocorreu um erro ao tentar excluir o cliente.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void preencheTabela(String nomeBusca) {
		tabelaClientes = clienteBO.preencheTabelaDeClientes(tabelaClientes,
				nomeBusca);
		tabelaClientes.repaint();
	}

	private void selecionaCliente() {
		int indiceCliente = tabelaClientes.getSelectedRow();
		TabelaClienteModel modelo = (TabelaClienteModel) tabelaClientes
				.getModel();
		String cpf = (String) modelo.getValueAt(indiceCliente, 0);
		String nome = (String) modelo.getValueAt(indiceCliente, 1);
		String endereco = (String) modelo.getValueAt(indiceCliente, 2);
		String sexo = (String) modelo.getValueAt(indiceCliente, 3);
		campoCPF.setText(cpf);
		campoNome.setText(nome);
		campoEndereco.setText(endereco);
		if (sexo.equals("M")) {
			botaoMasculino.setSelected(true);
		} else {
			botaoFeminino.setSelected(true);
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Cadastro de Clientes");
		frame.setBounds(100, 100, 724, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		layout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		layout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		layout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(layout);

		label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		frame.getContentPane().add(label, gbc_label);

		JLabel labelCPF = new JLabel("CPF:");
		GridBagConstraints gbc_labelCPF = new GridBagConstraints();
		gbc_labelCPF.insets = new Insets(0, 0, 5, 5);
		gbc_labelCPF.anchor = GridBagConstraints.SOUTHEAST;
		gbc_labelCPF.gridx = 1;
		gbc_labelCPF.gridy = 1;
		frame.getContentPane().add(labelCPF, gbc_labelCPF);

		campoCPF = new JTextField();
		GridBagConstraints gbc_campoCPF = new GridBagConstraints();
		gbc_campoCPF.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoCPF.insets = new Insets(0, 0, 5, 5);
		gbc_campoCPF.anchor = GridBagConstraints.SOUTH;
		gbc_campoCPF.gridx = 2;
		gbc_campoCPF.gridy = 1;
		frame.getContentPane().add(campoCPF, gbc_campoCPF);
		campoCPF.setColumns(10);

		labelNome = new JLabel("Nome:");
		GridBagConstraints gbc_labelNome = new GridBagConstraints();
		gbc_labelNome.anchor = GridBagConstraints.EAST;
		gbc_labelNome.insets = new Insets(0, 0, 5, 5);
		gbc_labelNome.gridx = 1;
		gbc_labelNome.gridy = 2;
		frame.getContentPane().add(labelNome, gbc_labelNome);

		campoNome = new JTextField();
		GridBagConstraints gbc_campoNome = new GridBagConstraints();
		gbc_campoNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoNome.gridwidth = 2;
		gbc_campoNome.insets = new Insets(0, 0, 5, 5);
		gbc_campoNome.gridx = 2;
		gbc_campoNome.gridy = 2;
		frame.getContentPane().add(campoNome, gbc_campoNome);
		campoNome.setColumns(10);

		label_2 = new JLabel(" ");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 0);
		gbc_label_2.gridx = 4;
		gbc_label_2.gridy = 2;
		frame.getContentPane().add(label_2, gbc_label_2);

		label_1 = new JLabel(" ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 3;
		frame.getContentPane().add(label_1, gbc_label_1);

		labelEndereco = new JLabel("Endere\u00E7o:");
		GridBagConstraints gbc_labelEndereco = new GridBagConstraints();
		gbc_labelEndereco.anchor = GridBagConstraints.EAST;
		gbc_labelEndereco.insets = new Insets(0, 0, 5, 5);
		gbc_labelEndereco.gridx = 1;
		gbc_labelEndereco.gridy = 3;
		frame.getContentPane().add(labelEndereco, gbc_labelEndereco);

		campoEndereco = new JTextField();
		GridBagConstraints gbc_campoEndereco = new GridBagConstraints();
		gbc_campoEndereco.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoEndereco.gridwidth = 2;
		gbc_campoEndereco.insets = new Insets(0, 0, 5, 5);
		gbc_campoEndereco.gridx = 2;
		gbc_campoEndereco.gridy = 3;
		frame.getContentPane().add(campoEndereco, gbc_campoEndereco);
		campoEndereco.setColumns(10);

		labelSexo = new JLabel("Sexo:");
		GridBagConstraints gbc_labelSexo = new GridBagConstraints();
		gbc_labelSexo.anchor = GridBagConstraints.EAST;
		gbc_labelSexo.insets = new Insets(0, 0, 5, 5);
		gbc_labelSexo.gridx = 1;
		gbc_labelSexo.gridy = 4;
		frame.getContentPane().add(labelSexo, gbc_labelSexo);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 4;
		frame.getContentPane().add(panel, gbc_panel);

		ButtonGroup grupoBotoes = new ButtonGroup();
		botaoMasculino = new JRadioButton("M");
		botaoMasculino.setSelected(true);
		botaoFeminino = new JRadioButton("F");
		grupoBotoes.add(botaoMasculino);
		grupoBotoes.add(botaoFeminino);
		panel.add(botaoMasculino);
		panel.add(botaoFeminino);

		respostaCadastro = new JLabel("");
		GridBagConstraints gbc_respostaErro = new GridBagConstraints();
		gbc_respostaErro.insets = new Insets(0, 0, 5, 5);
		gbc_respostaErro.gridx = 3;
		gbc_respostaErro.gridy = 5;
		frame.getContentPane().add(respostaCadastro, gbc_respostaErro);

		painelBotoes = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) painelBotoes.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_painelBotoes = new GridBagConstraints();
		gbc_painelBotoes.insets = new Insets(0, 0, 5, 5);
		gbc_painelBotoes.fill = GridBagConstraints.BOTH;
		gbc_painelBotoes.gridx = 2;
		gbc_painelBotoes.gridy = 6;
		frame.getContentPane().add(painelBotoes, gbc_painelBotoes);

		botaoCadastrar = new JButton("Cadastrar");
		painelBotoes.add(botaoCadastrar);

		botaoLimpar = new JButton("Limpar");
		painelBotoes.add(botaoLimpar);

		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				respostaCadastro.setText("");
				limpaCampos();
			}
		});

		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastraCliente();
			}
		});

		painelBusca = new JPanel();
		FlowLayout flowLayout = (FlowLayout) painelBusca.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_painelBusca = new GridBagConstraints();
		gbc_painelBusca.fill = GridBagConstraints.HORIZONTAL;
		gbc_painelBusca.anchor = GridBagConstraints.NORTH;
		gbc_painelBusca.gridwidth = 2;
		gbc_painelBusca.insets = new Insets(0, 0, 5, 5);
		gbc_painelBusca.gridx = 2;
		gbc_painelBusca.gridy = 8;
		frame.getContentPane().add(painelBusca, gbc_painelBusca);

		lblFiltrarClientes = new JLabel("Filtrar clientes por nome:");
		painelBusca.add(lblFiltrarClientes);

		campoBusca = new JTextField();
		campoBusca.setHorizontalAlignment(SwingConstants.LEFT);
		painelBusca.add(campoBusca);
		campoBusca.setColumns(40);
		campoBusca.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String nomeBusca = campoBusca.getText().trim();
				preencheTabela(nomeBusca);
			}
		});

		tabelaClientes = new JTable();
		tabelaClientes.setFillsViewportHeight(true);
		GridBagConstraints gbc_tabelaClientes = new GridBagConstraints();
		gbc_tabelaClientes.gridwidth = 2;
		gbc_tabelaClientes.insets = new Insets(0, 0, 5, 5);
		gbc_tabelaClientes.fill = GridBagConstraints.BOTH;
		gbc_tabelaClientes.gridx = 2;
		gbc_tabelaClientes.gridy = 9;
		frame.getContentPane().add(tabelaClientes, gbc_tabelaClientes);
		tabelaClientes.setAutoCreateRowSorter(true);
		preencheTabela(null);

		tabelaClientes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					selecionaCliente();
				}
			}

			public void mousePressed(MouseEvent event) {
				Point point = event.getPoint();
				int indiceSelecionado = tabelaClientes.rowAtPoint(point);
				tabelaClientes.setRowSelectionInterval(indiceSelecionado,
						indiceSelecionado);
			}
		});

		menuOpcoesTabela = new JPopupMenu();
		itemExcluir = new JMenuItem("Excluir");
		itemExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluiCliente();
			}
		});
		menuOpcoesTabela.add(itemExcluir);
		tabelaClientes.setComponentPopupMenu(menuOpcoesTabela);

		scrollTabela = new JScrollPane(tabelaClientes);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 9;
		gbc_scrollPane.gridwidth = 2;
		frame.getContentPane().add(scrollTabela, gbc_scrollPane);
	}
}
