package br.edu.paulista.ifpe.gui.dialogos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.edu.paulista.ifpe.core.LimiteCaracteres;
import br.edu.paulista.ifpe.data.ConnectionBD;
import br.edu.paulista.ifpe.gui.componentesCustomizados.CampoTextoRedondo;
import br.edu.paulista.ifpe.gui.componentesCustomizados.PainelDegrade;

@SuppressWarnings("serial")
public class CadastroOperador extends JDialog {

	private final PainelDegrade contentPane = new PainelDegrade();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtNome;
	private JTextField txtSenha;
	private JButton btnCadastro;

	public static void main(String[] args) {
		try {
			CadastroOperador dialog = new CadastroOperador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar um operador");
		}
	}

	public CadastroOperador() {
		setResizable(false);
		setTitle("Cadastro de operador");
		setLocationRelativeTo(null);
		contentPane.setColors(new Color(0, 128, 255), new Color(50, 205, 50));
		setBounds(100, 100, 450, 300);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(48, 25, 89, 28);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(48, 84, 89, 13);
		contentPane.add(lblNewLabel_1);

		txtNome = new CampoTextoRedondo(10);
		txtNome.setBounds(147, 30, 96, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(147, 81, 96, 19);
		contentPane.add(txtSenha);
		txtSenha.setColumns(10);

		LimiteCaracteres limiteCaracteres = new LimiteCaracteres();
		limiteCaracteres.adicionarLimiteCaracteres(txtNome, 100);
		limiteCaracteres.adicionarLimiteCaracteres(txtSenha, 45);

		btnCadastro = new JButton("Cadastrar");
		btnCadastro.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCadastro.setBounds(147, 175, 96, 21);
		contentPane.add(btnCadastro);
		btnCadastro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = txtNome.getText();
				String senha = new String(((JPasswordField) txtSenha).getPassword());

				if (nome.isEmpty() || senha.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}

				ConnectionBD connectionBD = new ConnectionBD();
				Connection connection = connectionBD.abrir();

				if (connection != null) {
					try {
						String sql = "INSERT INTO operador (nome, senha) VALUES (?, ?)";
						try (PreparedStatement statement = connection.prepareStatement(sql)) {
							statement.setString(1, nome);
							statement.setString(2, senha);
							int rowsAffected = statement.executeUpdate();

							if (rowsAffected > 0) {
								JOptionPane.showMessageDialog(null, "Operador cadastrado com sucesso.", "Sucesso",
										JOptionPane.INFORMATION_MESSAGE);
								txtNome.setText("");
								txtSenha.setText("");
							} else {
								JOptionPane.showMessageDialog(null, "Erro ao cadastrar operador.", "Erro",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar operador.", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}

					connectionBD.fechar();
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				dispose();
			}
		});

	}
}
