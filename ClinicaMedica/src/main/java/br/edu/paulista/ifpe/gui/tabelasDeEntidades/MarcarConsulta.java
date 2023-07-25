package br.edu.paulista.ifpe.gui.tabelasDeEntidades;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import br.edu.paulista.ifpe.data.ConnectionBD;
import br.edu.paulista.ifpe.gui.PainelDegrade;

@SuppressWarnings("serial")
public class MarcarConsulta extends JDialog {

	private PainelDegrade contentPane;
	private JButton btnCadastro;
	private JLabel lblNewLabel;
	private CampoTextoRedondo txtNomePaciente;
	private JLabel lblNewLabel_1;
	private CampoTextoRedondo txtNomeMedico;
	private JLabel lblNewLabel_2;
	private CampoTextoRedondo txtHorario;
	private JLabel lblNewLabel_3;
	private CampoTextoRedondo txtConsulta;
	private MarcarConsultasListener listener;
	public void setListener(MarcarConsultasListener listener) {
        this.listener = listener;
    }

	private ConnectionBD connectionBD;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the dialog.
	 */
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

		lblNewLabel = new JLabel("Nome do paciente");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 10, 120, 13);
		contentPane.add(lblNewLabel);

		txtNomePaciente = new CampoTextoRedondo(10);
		txtNomePaciente.setBounds(153, 7, 160, 19);
		contentPane.add(txtNomePaciente);
		txtNomePaciente.setColumns(10);

		lblNewLabel_1 = new JLabel("Nome do médico");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 39, 120, 13);
		contentPane.add(lblNewLabel_1);

		txtNomeMedico = new CampoTextoRedondo(10);
		txtNomeMedico.setBounds(153, 36, 160, 19);
		contentPane.add(txtNomeMedico);
		txtNomeMedico.setColumns(10);

		lblNewLabel_2 = new JLabel("Horário da consulta");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 68, 128, 13);
		contentPane.add(lblNewLabel_2);

		txtHorario = new CampoTextoRedondo(10);
		txtHorario.setBounds(153, 65, 160, 19);
		contentPane.add(txtHorario);
		txtHorario.setColumns(10);

		lblNewLabel_3 = new JLabel("Data da consulta");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 97, 120, 13);
		contentPane.add(lblNewLabel_3);

		txtConsulta = new CampoTextoRedondo(10);
		txtConsulta.setBounds(153, 94, 160, 19);
		contentPane.add(txtConsulta);
		txtConsulta.setColumns(10);
		connectionBD = new ConnectionBD();
	}

	private void cadastrarConsulta() {
		String nomePaciente = txtNomePaciente.getText();
		String nomeMedico = txtNomeMedico.getText();
		String horario = txtHorario.getText();
		String dataConsulta = txtConsulta.getText();

		if (nomePaciente.isEmpty() || nomeMedico.isEmpty() || horario.isEmpty() || dataConsulta.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			Connection connection = connectionBD.abrir();

			int idPaciente = obterIdPaciente(connection, nomePaciente);
			int idMedico = obterIdMedico(connection, nomeMedico);

			if (idPaciente == -1) {
				JOptionPane.showMessageDialog(this, "Paciente não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (idMedico == -1) {
				JOptionPane.showMessageDialog(this, "Médico não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}

			inserirConsulta(connection, idPaciente, idMedico, horario, dataConsulta);

			connectionBD.fechar();

			JOptionPane.showMessageDialog(this, "Consulta cadastrada com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			if (listener != null) {
                listener.consultaCadastrada();
            }
			dispose();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Erro ao cadastrar a consulta.", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private int obterIdPaciente(Connection connection, String nomePaciente) throws SQLException {
		String query = "SELECT id FROM paciente WHERE nome = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, nomePaciente);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("id");
				} else {
					return -1;
				}
			}
		}
	}

	private int obterIdMedico(Connection connection, String nomeMedico) throws SQLException {
		String query = "SELECT id FROM medico WHERE nome = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, nomeMedico);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("id");
				} else {
					return -1;
				}
			}
		}
	}

	private void inserirConsulta(Connection connection, int idPaciente, int idMedico, String horario,
			String dataConsulta) throws SQLException {
		String query = "INSERT INTO consulta (id_paciente, id_medico, hora, data) VALUES (?, ?, ?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, idPaciente);
			stmt.setInt(2, idMedico);
			stmt.setString(3, horario);
			stmt.setString(4, dataConsulta);

			stmt.executeUpdate();

		}
	}
}
