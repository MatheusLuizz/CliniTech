package br.edu.paulista.ifpe.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JViewport;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaExame;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaMedico;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaPaciente;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaRemedio;

@SuppressWarnings("serial")
public class Home extends JFrame {

	private JPanel contentPane;
	private PainelDegrade painelAtalhos;
	private JButton btnPaciente;
	private JButton btnMedico;
	private JPanel painelBusca;
	private JPanel painelPaciente;
	private final JTextField txtBusca = new JTextField();
	private JLabel lblBuscar;
	private List<JTable> tabelasExibidas;
	private JButton btnRemedio;
	private JButton btnExame;
	private JToggleButton btnTema;
	private boolean modoEscuroAtivado = false;

	private void aplicarFiltroBusca(JTable tabela, TableModel modeloTabela, String textoBusca) {
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloTabela);
		tabela.setRowSorter(sorter);

		if (textoBusca.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo de busca está vazio");
			sorter.setRowFilter(null);
		} else {
			String regex = "(?i)" + Pattern.quote(textoBusca);
			sorter.setRowFilter(RowFilter.regexFilter(regex, 0)); // Assumindo que o nome está na primeira coluna
																	// (índice 0)
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		tabelasExibidas = new ArrayList<JTable>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		painelAtalhos = new PainelDegrade();
		painelAtalhos.setBounds(0, 0, 91, 810);
		painelAtalhos.setBackground(new Color(13, 73, 151));

		btnPaciente = new JButton("");
		btnPaciente.setToolTipText("Exibir os pacientes cadastrados");
		btnPaciente.setForeground(new Color(13, 73, 151));
		btnPaciente.setBackground(new Color(13, 73, 151));
		btnPaciente.setBorderPainted(false);
		btnPaciente
				.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconePaciente.png")));

		btnMedico = new JButton("");
		btnMedico.setForeground(new Color(13, 73, 151));
		btnMedico.setBackground(new Color(13, 73, 151));
		btnMedico.setBorderPainted(false);
		btnMedico.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeMedico.png")));

		btnRemedio = new JButton("");
		btnRemedio.setForeground(new Color(13, 73, 151));
		btnRemedio.setBackground(new Color(13, 73, 151));
		btnRemedio.setBorderPainted(false);
		btnRemedio
				.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeRemedio.png")));

		btnExame = new JButton("");
		btnExame.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeExame.png")));
		btnExame.setForeground(new Color(13, 73, 151));
		btnExame.setBackground(new Color(13, 73, 151));
		btnExame.setBorderPainted(false);
		GroupLayout gl_painelAtalhos = new GroupLayout(painelAtalhos);
		gl_painelAtalhos
				.setHorizontalGroup(
						gl_painelAtalhos.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_painelAtalhos.createSequentialGroup().addContainerGap()
										.addGroup(gl_painelAtalhos.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_painelAtalhos.createSequentialGroup()
														.addComponent(btnExame, GroupLayout.PREFERRED_SIZE, 71,
																Short.MAX_VALUE)
														.addContainerGap())
												.addGroup(gl_painelAtalhos.createSequentialGroup()
														.addComponent(btnPaciente, GroupLayout.PREFERRED_SIZE, 71,
																Short.MAX_VALUE)
														.addContainerGap())
												.addGroup(gl_painelAtalhos.createSequentialGroup()
														.addComponent(btnRemedio, 0, 0, Short.MAX_VALUE)
														.addContainerGap())
												.addGroup(Alignment.TRAILING, gl_painelAtalhos.createSequentialGroup()
														.addComponent(btnMedico, 0, 0, Short.MAX_VALUE).addGap(12)))));
		gl_painelAtalhos.setVerticalGroup(gl_painelAtalhos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelAtalhos.createSequentialGroup().addContainerGap()
						.addComponent(btnPaciente, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnMedico, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnRemedio, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnExame, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(514, Short.MAX_VALUE)));
		painelAtalhos.setLayout(gl_painelAtalhos);
		contentPane.setLayout(null);

		painelBusca = new JPanel();
		painelBusca.setBounds(91, 0, 1473, 65);
		painelBusca.setBackground(new Color(211, 211, 211));
		painelBusca.setLayout(null);
		contentPane.add(painelBusca);
		txtBusca.setToolTipText("Digite o nome e pressione a tecla \"ENTER\"");
		txtBusca.setFont(new Font("Arial", Font.PLAIN, 15));
		txtBusca.setBounds(681, 22, 322, 33);
		painelBusca.add(txtBusca);
		txtBusca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textoBusca = txtBusca.getText();
				for (JTable tabela : tabelasExibidas) {
					TableModel modelo = tabela.getModel();
					aplicarFiltroBusca(tabela, modelo, textoBusca);
				}
			}
		});
		txtBusca.setColumns(10);

		lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		lblBuscar.setBounds(624, 26, 47, 23);
		painelBusca.add(lblBuscar);

		btnTema = new JToggleButton("Dark Mode");
		btnTema.setFont(new Font("Arial", Font.PLAIN, 11));
		btnTema.setBounds(10, 10, 115, 45);
		painelBusca.add(btnTema);
		contentPane.add(painelAtalhos);
		lblBuscar.setVisible(false);
		txtBusca.setVisible(false);

		painelPaciente = new JPanel();
		painelPaciente.setBounds(91, 67, 1459, 743);
		contentPane.add(painelPaciente);
		painelPaciente.setLayout(new CardLayout(0, 0));

		btnPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBusca.setText("");
				TelaPaciente p = new TelaPaciente();
				p.atualizar();
				atualizarEstiloTabelas();
				lblBuscar.setVisible(true);
				txtBusca.setVisible(true);
				painelPaciente.removeAll();
				painelPaciente.setLayout(new CardLayout(0, 0));
				painelPaciente.add(p, BorderLayout.CENTER);
				painelPaciente.revalidate();
				painelPaciente.repaint();

				tabelasExibidas = new ArrayList<JTable>();
				tabelasExibidas.add(p.getTabela());
				atualizarEstiloTabelas();
			}
		});
		btnMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBusca.setText("");
				TelaMedico m = new TelaMedico();
				m.atualizar();
				atualizarEstiloTabelas();
				lblBuscar.setVisible(true);
				txtBusca.setVisible(true);
				painelPaciente.removeAll();
				painelPaciente.setLayout(new CardLayout(0, 0));
				painelPaciente.add(m, BorderLayout.CENTER);
				painelPaciente.revalidate();
				painelPaciente.repaint();

				tabelasExibidas = new ArrayList<JTable>();
				tabelasExibidas.add(m.getTabela());
				atualizarEstiloTabelas();
			}
		});
		btnRemedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBusca.setText("");
				TelaRemedio r = new TelaRemedio();
				r.atualizar();
				atualizarEstiloTabelas();
				lblBuscar.setVisible(true);
				txtBusca.setVisible(true);
				painelPaciente.removeAll();
				painelPaciente.setLayout(new CardLayout(0, 0));
				painelPaciente.add(r, BorderLayout.CENTER);
				painelPaciente.revalidate();
				painelPaciente.repaint();

				tabelasExibidas = new ArrayList<JTable>();
				tabelasExibidas.add(r.getTabela());
				atualizarEstiloTabelas();
			}
		});
		btnExame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBusca.setText("");
				TelaExame ex = new TelaExame();
				ex.atualizar();
				atualizarEstiloTabelas();
				lblBuscar.setVisible(true);
				txtBusca.setVisible(true);
				painelPaciente.removeAll();
				painelPaciente.setLayout(new CardLayout(0, 0));
				painelPaciente.add(ex, BorderLayout.CENTER);
				painelPaciente.revalidate();
				painelPaciente.repaint();

				tabelasExibidas = new ArrayList<JTable>();
				tabelasExibidas.add(ex.getTabela());
				atualizarEstiloTabelas();
			}
		});
		btnTema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnTema.isSelected()) {
					aplicarTemaEscuro();
				} else {
					aplicarTemaPadrao();
				}
				atualizarEstiloTabelas();
			}
		});
		btnPaciente.setOpaque(false);
		btnPaciente.setContentAreaFilled(false);
		btnPaciente.setBorderPainted(false);

		btnMedico.setOpaque(false);
		btnMedico.setContentAreaFilled(false);
		btnMedico.setBorderPainted(false);

		btnRemedio.setOpaque(false);
		btnRemedio.setContentAreaFilled(false);
		btnRemedio.setBorderPainted(false);

		btnExame.setOpaque(false);
		btnExame.setContentAreaFilled(false);
		btnExame.setBorderPainted(false);
	}
	

	private void aplicarTemaEscuro() {
		painelAtalhos.setColors(Color.decode("#16161D"), Color.decode("#211F4D"));
		Color corDeFundo = new Color(40, 42, 54);
		Color corTexto = new Color(248, 248, 242);
		Color corBorda = new Color(68, 71, 90);
		Color corBusca = new Color(59, 61, 76);
		Color corSelecaoTabela = new Color(70, 130, 180);

		contentPane.setBackground(corDeFundo);
		
		painelBusca.setBackground(corBusca);
		painelPaciente.setBackground(corDeFundo);

		lblBuscar.setForeground(corTexto);

		contentPane.setBorder(BorderFactory.createLineBorder(corBorda));
		painelAtalhos.setBorder(BorderFactory.createLineBorder(corBorda));
		painelBusca.setBorder(BorderFactory.createLineBorder(corBorda));
		painelPaciente.setBorder(BorderFactory.createLineBorder(corBorda));

		UIManager.put("TableHeader.background", corBusca);
		UIManager.put("TableHeader.foreground", corTexto);
		UIManager.put("Table.selectionBackground", corSelecaoTabela);
		UIManager.put("Table.selectionForeground", corTexto);
		
		atualizarEstiloTabelas();

		modoEscuroAtivado = true;
	}

	private void aplicarTemaPadrao() {

		contentPane.setBackground(null);
		painelAtalhos.setColors(Color.decode("#1CB5E0"), Color.decode("#000046"));
		painelBusca.setBackground(new Color(211, 211, 211));
		painelPaciente.setBackground(null);

		btnPaciente.setBackground(new Color(13, 73, 151));
		btnMedico.setBackground(new Color(13, 73, 151));
		btnRemedio.setBackground(new Color(13, 73, 151));
		btnExame.setBackground(new Color(13, 73, 151));

		lblBuscar.setForeground(null);

		contentPane.setBorder(null);
		painelAtalhos.setBorder(null);
		painelBusca.setBorder(null);
		painelPaciente.setBorder(null);

		Color corFundoTabela = UIManager.getColor("Table.background");
	    Color corTextoTabela = UIManager.getColor("Table.foreground");
	    Color corSelecaoTabela = UIManager.getColor("Table.selectionBackground");

	    UIManager.put("TableHeader.background", corFundoTabela);
	    UIManager.put("TableHeader.foreground", corTextoTabela);
	    UIManager.put("Table.selectionBackground", corSelecaoTabela);
	    UIManager.put("Table.selectionForeground", corTextoTabela);

	    atualizarEstiloTabelas();

	    modoEscuroAtivado = false;
	}

	private void atualizarEstiloTabelas() {
		Color corFundoTabela;
		Color corTextoTabela;
		Color corSelecaoTabela;

		if (modoEscuroAtivado) {

			corFundoTabela = new Color(40, 42, 54);
			corTextoTabela = new Color(248, 248, 242);
			corSelecaoTabela = new Color(68, 71, 90);
		} else {

			corFundoTabela = Color.WHITE;
			corTextoTabela = Color.BLACK;
			corSelecaoTabela = new Color(135, 206, 235);
		}

		for (JTable tabela : tabelasExibidas) {
			tabela.setBackground(corFundoTabela);
			tabela.setForeground(corTextoTabela);
			tabela.setSelectionBackground(corSelecaoTabela);

			JTableHeader header = tabela.getTableHeader();
			header.setBackground(corFundoTabela);
			header.setForeground(corTextoTabela);

			// Configura o estilo do BorderLayout da tabela
			Container container = tabela.getParent();
			if (container instanceof JViewport) {
				container = container.getParent();
				if (container instanceof JScrollPane) {
					JScrollPane scrollPane = (JScrollPane) container;
					scrollPane.getViewport().setBackground(corFundoTabela);
					scrollPane.getViewport().setForeground(corTextoTabela);
				}
			}
		}
	}
}
