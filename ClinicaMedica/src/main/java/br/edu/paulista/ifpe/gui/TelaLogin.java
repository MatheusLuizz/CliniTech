package br.edu.paulista.ifpe.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import br.edu.paulista.ifpe.data.ConnectionBD;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.CadastroOperador;

@SuppressWarnings("serial")
public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel_2;
	private JPasswordField txtSenha;
	private JButton btnLogin;
	@SuppressWarnings("rawtypes")
	private JComboBox boxLogin;
	private JLabel lblNewLabel_1;
	private JButton btnCadastro;

	@SuppressWarnings({ "unused" })
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Um erro crítico ocorreu, f no chat", "Erro",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TelaLogin() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon imagemFundo = new ImageIcon(
				getClass().getResource("/br/edu/paulista/ifpe/model/images/imagemLogin.png"));

		btnLogin = new JButton("Entrar");
		btnLogin.setToolTipText("Realizar login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipoUsuario = (String) boxLogin.getSelectedItem();
				String senha = new String(txtSenha.getPassword());
				String nome = null;
				ConnectionBD connectionBD = new ConnectionBD();
				Connection connection = connectionBD.abrir();

				if (connection != null) {
					boolean autenticado = false;

					try {
						String tabela;

						if ("Operador".equals(tipoUsuario)) {
							tabela = "operador";
							nome = "nome";
						} else if ("Médico".equals(tipoUsuario)) {
							tabela = "medico";
							nome = "nome";
						} else {
							JOptionPane.showMessageDialog(null, "Tipo de usuário inválido.", "Erro",
									JOptionPane.ERROR_MESSAGE);
							connectionBD.fechar();
							return;
						}

						String sql = "SELECT COUNT(*), " + nome + " FROM " + tabela + " WHERE senha = ?";
						try (PreparedStatement statement = connection.prepareStatement(sql)) {
							statement.setString(1, senha);
							try (ResultSet resultSet = statement.executeQuery()) {
								resultSet.next();
								int count = resultSet.getInt(1);
								autenticado = count == 1;
								if (autenticado) {
									nome = resultSet.getString(nome);
								}
							}
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}

					connectionBD.fechar();

					if (autenticado && tipoUsuario == "Médico") {
						HomeMedico h = new HomeMedico(nome);
						TelaLogin.this.dispose();
						h.setVisible(true);
					} else if (autenticado && tipoUsuario == "Operador") {
						Home h = new Home(nome);
						TelaLogin.this.dispose();
						h.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Credenciais inválidas. Tente novamente.",
								"Erro de autenticação", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnCadastro = new JButton("Novo operador");
		btnCadastro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastro.setBounds(299, 384, 145, 21);
		contentPane.add(btnCadastro);
		btnCadastro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CadastroOperador co = new CadastroOperador();
				co.setLocationRelativeTo(null);
				co.setVisible(true);
			}
		});

		txtSenha = new JPasswordField();
		txtSenha.setBounds(274, 224, 189, 25);
		contentPane.add(txtSenha);

		lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(178, 150, 66, 13);
		contentPane.add(lblNewLabel_1);

		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(299, 308, 145, 21);
		contentPane.add(btnLogin);

		lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_2.setBounds(178, 225, 51, 21);
		contentPane.add(lblNewLabel_2);

		boxLogin = new ComboBoxCustomizada();
		boxLogin.setFont(new Font("Arial", Font.BOLD, 12));
		boxLogin.setModel(new DefaultComboBoxModel(new String[] { "Operador", "Médico" }));
		boxLogin.setBounds(274, 147, 189, 21);
		contentPane.add(boxLogin);
		JLabel labelImagem = new JLabel(imagemFundo);
		labelImagem.setBounds(-12, -11, 812, 500);
		contentPane.add(labelImagem);

	}
}