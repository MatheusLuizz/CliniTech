package br.edu.paulista.ifpe.gui.dialogos;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.edu.paulista.ifpe.core.LimiteCaracteres;
import br.edu.paulista.ifpe.core.cadastros.MarcarConsultasListener;
import br.edu.paulista.ifpe.data.ConnectionBD;
import br.edu.paulista.ifpe.gui.componentesCustomizados.CampoTextoFormatadoRedondo;
import br.edu.paulista.ifpe.gui.componentesCustomizados.CampoTextoRedondo;
import br.edu.paulista.ifpe.gui.componentesCustomizados.PainelDegrade;

@SuppressWarnings("serial")
public class MarcarConsulta extends JDialog {

	private PainelDegrade contentPane;
	private JButton btnCadastro;
	private JLabel lblNewLabel;
	private CampoTextoRedondo txtIdPaciente;
	private JLabel lblNewLabel_1;
	private CampoTextoRedondo txtIdMedico;
	private JLabel lblNewLabel_2;
	private CampoTextoFormatadoRedondo txtHorario;
	private JLabel lblNewLabel_3;
	private CampoTextoFormatadoRedondo txtData;
	private MarcarConsultasListener listener;

	public void setListener(MarcarConsultasListener listener) {
		this.listener = listener;
	}

	private ConnectionBD connectionBD;

	public static void main(String[] args) {
		try {
			MarcarConsulta dialog = new MarcarConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "<html>Erro ao abrir a pagina de marcação</html>", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public MarcarConsulta() {
		super();
		setModal(true);
		setTitle("Marcar consulta");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new PainelDegrade();
		contentPane.setColors(new Color(0, 128, 255), new Color(50, 205, 50));
		contentPane.repaint();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCadastro = new JButton("Cadastrar");
		btnCadastro.setBounds(169, 199, 120, 30);
		btnCadastro.setToolTipText("Finalizar cadastro");
		contentPane.add(btnCadastro);
		btnCadastro.addActionListener(e -> cadastrarConsulta());

		lblNewLabel = new JLabel("Id do Paciente");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 10, 120, 13);
		contentPane.add(lblNewLabel);

		txtIdPaciente = new CampoTextoRedondo(10);
		txtIdPaciente.setBounds(129, 7, 160, 19);
		contentPane.add(txtIdPaciente);
		txtIdPaciente.setColumns(10);

		lblNewLabel_1 = new JLabel("Id do médico");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 39, 120, 13);
		contentPane.add(lblNewLabel_1);

		txtIdMedico = new CampoTextoRedondo(10);
		txtIdMedico.setBounds(129, 36, 160, 19);
		contentPane.add(txtIdMedico);
		txtIdMedico.setColumns(10);

		lblNewLabel_2 = new JLabel("Horário ");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 68, 128, 13);
		contentPane.add(lblNewLabel_2);

		try {
			MaskFormatter mascaraHora = new MaskFormatter("##:##");
			txtHorario = new CampoTextoFormatadoRedondo(mascaraHora, 10);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação do horário", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		txtHorario.setBounds(128, 65, 160, 19);
		contentPane.add(txtHorario);
		txtHorario.setColumns(10);

		lblNewLabel_3 = new JLabel("Data da consulta");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 97, 120, 13);
		contentPane.add(lblNewLabel_3);

		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
			txtData = new CampoTextoFormatadoRedondo(mascaraData, 10);
			txtData.setFont(new Font("Arial", Font.BOLD, 12));

			txtData.setInputVerifier(new InputVerifier() {
				@Override
				public boolean verify(JComponent input) {
					CampoTextoFormatadoRedondo campoTexto = (CampoTextoFormatadoRedondo) input;
					String text = campoTexto.getText();
					String[] parts = text.split("/");

					if (parts.length != 3) {
						JOptionPane.showMessageDialog(null, "Data inválida. O correto é: DD/MM/AAAA", "Erro",
								JOptionPane.ERROR_MESSAGE);
						return false;
					}

					int day, month;
					try {
						day = Integer.parseInt(parts[0]);
						month = Integer.parseInt(parts[1]);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Data inválida. O correto é: DD/MM/AAAA", "Erro",
								JOptionPane.ERROR_MESSAGE);
						return false;
					}

					if (month < 1 || month > 12 || day < 1 || day > 31) {
						JOptionPane.showMessageDialog(null,
								"Data inválida. Digite um mês de 01 a 12 e um dia de 01 a 31", "Erro",
								JOptionPane.ERROR_MESSAGE);
						return false;
					}

					return true;
				}
			});
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação da data de nascimento. O correto é: DD/MM/AAAA",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}

		txtData.setBounds(128, 94, 160, 19);
		contentPane.add(txtData);
		txtData.setColumns(10);
		connectionBD = new ConnectionBD();

		LimiteCaracteres limiteCaracteres = new LimiteCaracteres();
		limiteCaracteres.adicionarLimiteCaracteres(txtIdMedico, 7);
		limiteCaracteres.adicionarLimiteCaracteres(txtIdPaciente, 7);

	}

	private void cadastrarConsulta() {
		String pacienteIdStr = txtIdPaciente.getText();
		String medicoIdStr = txtIdMedico.getText();
		int idPaciente, idMedico;
		String horario = txtHorario.getText();

		String dataDigitada = txtData.getText();
		String[] partesData = dataDigitada.split("/");
		String dataSQL = partesData[2] + "-" + partesData[1] + "-" + partesData[0];

		try {
			idPaciente = Integer.parseInt(pacienteIdStr);
			idMedico = Integer.parseInt(medicoIdStr);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "IDs de paciente e médico devem ser números válidos.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (txtIdPaciente.getText().isEmpty() || txtIdMedico.getText().isEmpty() || horario.isEmpty()
				|| dataDigitada.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			Connection connection = connectionBD.abrir();

			String medicoExistsQuery = "SELECT id FROM medico WHERE id = ?";
			try (PreparedStatement existsStmt = connection.prepareStatement(medicoExistsQuery)) {
				existsStmt.setInt(1, idMedico);
				try (ResultSet rs = existsStmt.executeQuery()) {
					if (!rs.next()) {
						JOptionPane.showMessageDialog(this, "O médico com o ID informado não existe.", "Erro",
								JOptionPane.ERROR_MESSAGE);
						connectionBD.fechar();
						return;
					}
				}
			}

			String pacienteExistsQuery = "SELECT id FROM paciente WHERE id = ?";
			try (PreparedStatement existsStmt = connection.prepareStatement(pacienteExistsQuery)) {
				existsStmt.setInt(1, idPaciente);
				try (ResultSet rs = existsStmt.executeQuery()) {
					if (!rs.next()) {
						JOptionPane.showMessageDialog(this, "O paciente com o ID informado não existe.", "Erro",
								JOptionPane.ERROR_MESSAGE);
						connectionBD.fechar();
						return;
					}
				}
			}

			String insertQuery = "INSERT INTO consulta (id_paciente, id_medico, hora, data) VALUES (?, ?, ?, ?)";
			try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
				insertStmt.setInt(1, idPaciente);
				insertStmt.setInt(2, idMedico);
				insertStmt.setString(3, horario);
				insertStmt.setString(4, dataSQL);
				insertStmt.executeUpdate();
			}

			connectionBD.fechar();

			JOptionPane.showMessageDialog(this, "Consulta cadastrada com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			if (listener != null) {
				listener.consultaCadastrada();
			}
			dispose();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Erro ao cadastrar a consulta.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
