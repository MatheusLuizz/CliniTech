package br.edu.paulista.ifpe.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.paulista.ifpe.data.ConnectionBD;
import br.edu.paulista.ifpe.gui.componentesCustomizados.PainelDegrade;
import br.edu.paulista.ifpe.gui.componentesCustomizados.TelaInicioMedico;
import br.edu.paulista.ifpe.gui.dialogos.RequisicaoDeExames;
import br.edu.paulista.ifpe.gui.dialogos.TelaReceita;
import br.edu.paulista.ifpe.gui.tabelas.TelaPaciente;
import br.edu.paulista.ifpe.model.temas.Temas;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class HomeMedico extends JFrame {

	private JPanel contentPane;
	private JLabel lblNome;
	private PainelDegrade painelAtalhos;
	private JButton btnPaciente;
	private JButton btnReceita;
	private JButton btnInicio;
	private PainelDegrade painelBusca;
	private JPanel painelPrincipal;
	private final JTextField txtBusca = new JTextField();
	private JLabel lblBuscar;
	private List<JTable> tabelasExibidas;
	private JButton btnRequisicaoExame;
	private JToggleButton btnTema;
	private String nomeAutenticado;
	private JButton btnLogout;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeMedico frame = new HomeMedico("Sei lá");
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao abrir a tela do médico");
				}
			}
		});
	}

	public int getIdMedico() {
		int idMedico = -1; // Valor padrão caso não seja encontrado

		ConnectionBD connectionBD = new ConnectionBD();
		Connection connection = connectionBD.abrir();

		if (connection != null) {
			try {
				String query = "SELECT id FROM medico WHERE nome = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, nomeAutenticado);

				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					idMedico = resultSet.getInt("id");
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro  ao consultar o banco de dados");
			} finally {
				connectionBD.fechar();
			}
		}

		return idMedico;
	}

	public HomeMedico(String nomeAutenticado) {
		this.nomeAutenticado = nomeAutenticado;
		int idMedico = getIdMedico();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível abrir o programa em seu sistema!");
		}
		setTitle("Tela Home");
		setResizable(false);
		tabelasExibidas = new ArrayList<JTable>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane = new JPanel(new MigLayout("fill", "[15%][grow]", "[65!][grow]"));
		setContentPane(contentPane);

		painelAtalhos = new PainelDegrade();
		painelAtalhos.setBounds(0, 0, 91, 865);

		btnPaciente = new JButton();
		btnPaciente.setToolTipText("Pacientes");
		btnPaciente.setForeground(new Color(13, 73, 151));
		btnPaciente.setBorderPainted(false);
		btnPaciente
				.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconePaciente.png")));

		btnReceita = new JButton();
		btnReceita.setToolTipText("<html>Gerar Receita</html>");
		btnReceita.setForeground(new Color(13, 73, 151));
		btnReceita.setBorderPainted(false);
		btnReceita
				.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeReceita.png")));

		btnRequisicaoExame = new JButton();
		btnRequisicaoExame.setToolTipText("<html>Requisição de exames</html>");
		btnRequisicaoExame.setForeground(new Color(13, 73, 151));
		btnRequisicaoExame.setBorderPainted(false);
		btnRequisicaoExame
				.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeReqExame.png")));

		btnInicio = new JButton();
		btnInicio.setToolTipText("Inicio");
		btnInicio.setIcon(
				new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeClinitech.png")));
		btnInicio.setForeground(new Color(13, 73, 151));
		btnInicio.setBackground(getBackground());
		btnInicio.setBorderPainted(false);

		btnLogout = new JButton();
		btnLogout.setToolTipText("Sair");
		btnLogout.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeLogout.png")));
		btnLogout.setForeground(new Color(13, 73, 151));
		btnLogout.setBackground(getForeground());
		btnLogout.setBorderPainted(false);

		contentPane.setLayout(null);
		GroupLayout gl_painelAtalhos = new GroupLayout(painelAtalhos);
		gl_painelAtalhos.setHorizontalGroup(gl_painelAtalhos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelAtalhos.createSequentialGroup()
						.addGroup(gl_painelAtalhos.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnRequisicaoExame, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnReceita, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnPaciente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnInicio, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnLogout, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(20, Short.MAX_VALUE)));
		gl_painelAtalhos.setVerticalGroup(gl_painelAtalhos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelAtalhos.createSequentialGroup().addContainerGap()
						.addComponent(btnInicio, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnPaciente, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnReceita, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnRequisicaoExame, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addContainerGap(569, Short.MAX_VALUE)));
		painelAtalhos.setLayout(gl_painelAtalhos);

		painelBusca = new PainelDegrade();
		painelBusca.setBounds(91, 0, 1473, 65);
		painelBusca.setColors(Color.GRAY, new Color(220, 220, 220));
		painelBusca.setLayout(null);
		contentPane.add(painelBusca);
		txtBusca.setToolTipText("Filtre pelo nome");
		txtBusca.setFont(new Font("Arial", Font.PLAIN, 15));
		txtBusca.setBounds(681, 22, 322, 33);
		painelBusca.add(txtBusca);
		txtBusca.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				realizarBusca();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				realizarBusca();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				realizarBusca();
			}
		});
		txtBusca.setColumns(10);

		lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		lblBuscar.setBounds(624, 26, 47, 23);
		painelBusca.add(lblBuscar);

		lblNome = new JLabel(nomeAutenticado);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNome.setBounds(1280, 26, 120, 23);
		painelBusca.add(lblNome);

		btnTema = new JToggleButton("");
		btnTema.setToolTipText("Tema escuro");
		btnTema.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeSol.png")));
		btnTema.setBounds(10, 10, 32, 33);
		painelBusca.add(btnTema);
		contentPane.add(painelAtalhos);
		lblBuscar.setVisible(false);
		txtBusca.setVisible(false);

		painelPrincipal = new JPanel();
		painelPrincipal.setBackground(new Color(245, 245, 245));
		painelPrincipal.setBounds(91, 67, 1459, 865);
		contentPane.add(painelPrincipal);
		painelPrincipal.setLayout(new CardLayout(0, 0));

		txtBusca.setVisible(false);
		lblBuscar.setVisible(false);
		TelaInicioMedico i = new TelaInicioMedico(idMedico);
		painelPrincipal.removeAll();
		painelPrincipal.setLayout(new CardLayout(0, 0));
		painelPrincipal.add(i, BorderLayout.CENTER);
		painelPrincipal.revalidate();
		painelPrincipal.repaint();

		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBusca.setVisible(false);
				lblBuscar.setVisible(false);
				TelaInicioMedico i = new TelaInicioMedico(idMedico);
				painelPrincipal.removeAll();
				painelPrincipal.setLayout(new CardLayout(0, 0));
				painelPrincipal.add(i, BorderLayout.CENTER);
				painelPrincipal.revalidate();
				painelPrincipal.repaint();
			}
		});

		btnPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBusca.setText("");
				TelaPaciente telaPaciente = new TelaPaciente();
				telaPaciente.atualizar();

				lblBuscar.setVisible(true);
				txtBusca.setVisible(true);
				painelPrincipal.removeAll();
				painelPrincipal.setLayout(new CardLayout(0, 0));
				painelPrincipal.add(telaPaciente);
				painelPrincipal.revalidate();
				painelPrincipal.repaint();

				tabelasExibidas = new ArrayList<JTable>();
				tabelasExibidas.add(telaPaciente.getTabela());
				Temas.atualizarEstiloTabelas(tabelasExibidas);
			}
		});
		btnReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaReceita tr = new TelaReceita(idMedico);
				tr.setLocationRelativeTo(null);
				tr.setVisible(true);
			}
		});
		btnRequisicaoExame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequisicaoDeExames re = new RequisicaoDeExames(idMedico);
				re.setLocationRelativeTo(null);
				re.setVisible(true);
			}
		});

		btnTema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnTema.isSelected()) {
					Temas.aplicarTemaEscuro(painelAtalhos, painelBusca, btnTema, tabelasExibidas);
					lblBuscar.setForeground(new Color(255, 105, 180));
					lblNome.setForeground(new Color(255, 105, 180));
					btnTema.setToolTipText("Tema claro");
				} else {
					Temas.aplicarTemaPadrao(painelAtalhos, painelBusca, painelPrincipal, btnTema, tabelasExibidas);
					lblBuscar.setForeground(Color.BLACK);
					lblNome.setForeground(Color.BLACK);
					btnTema.setToolTipText("Tema escuro");
				}
				Temas.atualizarEstiloTabelas(tabelasExibidas);
				btnTema.setFocusPainted(false);

			}
		});
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Tem certeza que você deseja sair?",
						"Confirmar log out", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					dispose();
					TelaLogin tl = new TelaLogin();
					tl.setLocationRelativeTo(null);
					tl.setVisible(true);
				}
			}
		});

		btnInicio.setOpaque(false);
		btnInicio.setContentAreaFilled(false);
		btnInicio.setFocusPainted(false);

		btnPaciente.setOpaque(false);
		btnPaciente.setContentAreaFilled(false);
		btnPaciente.setFocusPainted(false);

		btnReceita.setOpaque(false);
		btnReceita.setContentAreaFilled(false);
		btnReceita.setFocusPainted(false);

		btnRequisicaoExame.setOpaque(false);
		btnRequisicaoExame.setContentAreaFilled(false);
		btnRequisicaoExame.setFocusPainted(false);

		btnLogout.setOpaque(false);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setFocusPainted(false);

		btnTema.setOpaque(false);
		btnTema.setContentAreaFilled(false);
		btnTema.setFocusPainted(false);
		btnTema.setFocusable(false);

		contentPane.add(painelAtalhos, "width 15%, height 100%");
		contentPane.add(painelBusca, "width 85%, height 65%, growx");
		contentPane.add(painelPrincipal, "grow");
	}

	private void realizarBusca() {
		String textoBusca = txtBusca.getText();
		for (JTable tabela : tabelasExibidas) {
			TableModel modelo = tabela.getModel();
			aplicarFiltroBusca(tabela, modelo, textoBusca);
		}
	}

	private void aplicarFiltroBusca(JTable tabela, TableModel modeloTabela, String textoBusca) {
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(modeloTabela);
		tabela.setRowSorter(sorter);

		if (textoBusca.isEmpty()) {
			sorter.setRowFilter(null);
		} else {
			String regex = "(?i)" + Pattern.quote(textoBusca);
			sorter.setRowFilter(RowFilter.regexFilter(regex, 1)); // Assumindo que o nome está na primeira coluna
		}
	}

}
