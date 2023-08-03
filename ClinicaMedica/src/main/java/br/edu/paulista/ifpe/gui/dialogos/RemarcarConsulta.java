package br.edu.paulista.ifpe.gui.dialogos;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.edu.paulista.ifpe.data.ConnectionBD;
import br.edu.paulista.ifpe.gui.componentesCustomizados.CampoTextoRedondo;
import br.edu.paulista.ifpe.gui.componentesCustomizados.PainelDegrade;

@SuppressWarnings("serial")
public class RemarcarConsulta extends JDialog {

	private final JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField txtId;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField txtHora;
	private JTextField txtData;
	private JButton btnRemarcar;

	public static void main(String[] args) {
		try {
			RemarcarConsulta dialog = new RemarcarConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar remarcar consultas");
		}
	}

	public RemarcarConsulta() {
		setModal(true);
		setType(Type.UTILITY);

		contentPane = new PainelDegrade();
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Remarcar");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("Id da consulta");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 10, 97, 13);
		contentPane.add(lblNewLabel);

		txtId = new CampoTextoRedondo(10);
		txtId.setBounds(127, 8, 96, 19);
		contentPane.add(txtId);
		txtId.setColumns(10);

		lblNewLabel_1 = new JLabel("Novo horário");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 54, 97, 13);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Nova data");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 97, 83, 13);
		contentPane.add(lblNewLabel_2);

		txtHora = new CampoTextoRedondo(10);
		txtHora.setBounds(127, 52, 96, 19);
		contentPane.add(txtHora);
		txtHora.setColumns(10);

		txtData = new CampoTextoRedondo(10);
		txtData.setBounds(127, 95, 96, 19);
		contentPane.add(txtData);
		txtData.setColumns(10);

		btnRemarcar = new JButton("Remarcar");
		btnRemarcar.setBounds(164, 189, 85, 21);
		contentPane.add(btnRemarcar);
		btnRemarcar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConnectionBD connectionBD = new ConnectionBD();
				Connection connection = connectionBD.abrir();

				if (connection != null) {
					try {
						int id = Integer.parseInt(txtId.getText());
						String novoHorario = txtHora.getText();
						String novaData = txtData.getText();

						String updateQuery = "UPDATE consulta SET hora = ?, data = ? WHERE id = ?";

						PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
						preparedStatement.setString(1, novoHorario);
						preparedStatement.setString(2, novaData);
						preparedStatement.setInt(3, id);

						int linhasAtualizadas = preparedStatement.executeUpdate();

						if (linhasAtualizadas > 0) {
							JOptionPane.showMessageDialog(null, "Consulta remarcada com sucesso.");
						} else {
							JOptionPane.showMessageDialog(null, "Falha ao remarcar a consulta.");
						}

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "O ID deve ser um número inteiro.");
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, "Erro ao atualizar a consulta: ");
					} finally {
						connectionBD.fechar();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.");
				}
				dispose();
			}
		});
	}
}
