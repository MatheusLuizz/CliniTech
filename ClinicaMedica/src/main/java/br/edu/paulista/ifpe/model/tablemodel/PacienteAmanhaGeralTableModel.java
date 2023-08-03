package br.edu.paulista.ifpe.model.tablemodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import br.edu.paulista.ifpe.data.ConnectionBD;

@SuppressWarnings("serial")
public class PacienteAmanhaGeralTableModel extends AbstractTableModel {
	private Vector<String> colunas;
	private Vector<Vector<Object>> linhas;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PacienteAmanhaGeralTableModel() {
		colunas = new Vector();
		colunas.add("Nome");
		colunas.add("Atendimento");
		colunas.add("Médico");
		colunas.add("Hora");
		linhas = new Vector<>();
	}

	public int getRowCount() {
		int totalLinhas = linhas.size();
		return totalLinhas;
	}

	public int getColumnCount() {
		int totalColunas = colunas.size();
		return totalColunas;
	}

	public String getColumnName(int coluna) {
		String nomeColuna = colunas.get(coluna);
		return nomeColuna;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		return linhas.get(linha).get(coluna);
	}

	@SuppressWarnings({})
	public void adicionarConsulta(String nomePaciente, String tipoAtendimento, String nomeMedico,
			String horaAtendimento) {
		Vector<Object> novaLinha = new Vector<>();
		novaLinha.add(nomePaciente);
		novaLinha.add(tipoAtendimento);
		novaLinha.add(nomeMedico);
		novaLinha.add(horaAtendimento);
		linhas.add(novaLinha);

		fireTableRowsInserted(linhas.size() - 1, linhas.size() - 1);
	}

	public void limpar() {
		int rowCount = linhas.size();
		if (rowCount > 0) {
			linhas.clear();
			fireTableRowsDeleted(0, rowCount - 1);
		}
	}

	@SuppressWarnings("unused")
	public void adicionarConsultaFromSQL() {
		ConnectionBD connectionBD = new ConnectionBD();
		Connection connection = connectionBD.abrir();

		if (connection != null) {
			try {
				Calendar calendar = new GregorianCalendar();
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				Date dataAmanha = calendar.getTime();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dataAmanhaFormatada = sdf.format(dataAmanha);

				String consultaQuery = "SELECT p.nome AS nome_paciente, 'Consulta' AS atendimento, m.nome AS nome_medico, c.hora "
						+ "FROM consulta c " + "INNER JOIN paciente p ON c.id_paciente = p.id "
						+ "INNER JOIN medico m ON c.id_medico = m.id " + "WHERE c.data = ?";

				PreparedStatement consultaStatement = connection.prepareStatement(consultaQuery);
				consultaStatement.setString(1, dataAmanhaFormatada);
				ResultSet consultaResultSet = consultaStatement.executeQuery();

				while (consultaResultSet.next()) {
					String nomePaciente = consultaResultSet.getString("nome_paciente");
					String tipoAtendimento = consultaResultSet.getString("atendimento");
					String nomeMedico = consultaResultSet.getString("nome_medico");
					String horaAtendimento = consultaResultSet.getString("hora");

					adicionarConsulta(nomePaciente, tipoAtendimento, nomeMedico, horaAtendimento);
				}

				consultaResultSet.close();
				consultaStatement.close();

				String exameQuery = "SELECT p.nome AS nome_paciente, em.id_exame, m.nome AS nome_medico, em.data, em.hora "
						+ "FROM exame_marcado em " + "INNER JOIN paciente p ON em.id_paciente = p.id "
						+ "INNER JOIN medico m ON em.id_medico = m.id " + "WHERE em.data = ?";

				PreparedStatement exameStatement = connection.prepareStatement(exameQuery);
				exameStatement.setString(1, dataAmanhaFormatada);
				ResultSet exameResultSet = exameStatement.executeQuery();

				while (exameResultSet.next()) {
					String nomePaciente = exameResultSet.getString("nome_paciente");
					String idExame = exameResultSet.getString("id_exame");
					String nomeMedico = exameResultSet.getString("nome_medico");
					String dataExame = exameResultSet.getString("data");
					String horaExame = exameResultSet.getString("hora");

					String nomeExame = obterNomeExamePorId(idExame);
					String tipoAtendimento = nomeExame;

					adicionarConsulta(nomePaciente, tipoAtendimento, nomeMedico, horaExame);
				}

				exameResultSet.close();
				exameStatement.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados");
			} finally {
				connectionBD.fechar();
			}
		}
	}

	@SuppressWarnings("unused")
	public void adicionarConsultasEExamesPorMedico(int idMedico) {
		ConnectionBD connectionBD = new ConnectionBD();
		Connection connection = connectionBD.abrir();

		if (connection != null) {
			try {

				Calendar calendar = new GregorianCalendar();
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				Date dataAmanha = calendar.getTime();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dataAmanhaFormatada = sdf.format(dataAmanha);

				String consultaQuery = "SELECT p.nome AS nome_paciente, 'Consulta' AS atendimento, m.nome AS nome_medico, c.hora "
						+ "FROM consulta c " + "INNER JOIN paciente p ON c.id_paciente = p.id "
						+ "INNER JOIN medico m ON c.id_medico = m.id " + "WHERE c.data = ? AND m.id = ?";

				PreparedStatement consultaStatement = connection.prepareStatement(consultaQuery);
				consultaStatement.setString(1, dataAmanhaFormatada);
				consultaStatement.setInt(2, idMedico);
				ResultSet consultaResultSet = consultaStatement.executeQuery();

				while (consultaResultSet.next()) {
					String nomePaciente = consultaResultSet.getString("nome_paciente");
					String tipoAtendimento = consultaResultSet.getString("atendimento");
					String nomeMedico = consultaResultSet.getString("nome_medico");
					String horaAtendimento = consultaResultSet.getString("hora");

					adicionarConsulta(nomePaciente, tipoAtendimento, nomeMedico, horaAtendimento);
				}

				consultaResultSet.close();
				consultaStatement.close();

				String exameQuery = "SELECT p.nome AS nome_paciente, em.id_exame, m.nome AS nome_medico, em.data, em.hora "
						+ "FROM exame_marcado em " + "INNER JOIN paciente p ON em.id_paciente = p.id "
						+ "INNER JOIN medico m ON em.id_medico = m.id " + "WHERE em.data = ? AND m.id = ?";

				PreparedStatement exameStatement = connection.prepareStatement(exameQuery);
				exameStatement.setString(1, dataAmanhaFormatada);
				exameStatement.setInt(2, idMedico);
				ResultSet exameResultSet = exameStatement.executeQuery();

				while (exameResultSet.next()) {
					String nomePaciente = exameResultSet.getString("nome_paciente");
					String idExame = exameResultSet.getString("id_exame");
					String nomeMedico = exameResultSet.getString("nome_medico");
					String dataExame = exameResultSet.getString("data");
					String horaExame = exameResultSet.getString("hora");

					String nomeExame = obterNomeExamePorId(idExame);
					String tipoAtendimento = nomeExame;

					adicionarConsulta(nomePaciente, tipoAtendimento, nomeMedico, horaExame);
				}

				exameResultSet.close();
				exameStatement.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados");
			} finally {
				connectionBD.fechar();
			}
		}
	}

	private String obterNomeExamePorId(String idExame) {
		ConnectionBD connectionBD = new ConnectionBD();
		Connection connection = connectionBD.abrir();
		String nomeExame = "";

		if (connection != null) {
			try {
				String query = "SELECT tipo_exame FROM exame WHERE id = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, idExame);
				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					nomeExame = resultSet.getString("tipo_exame");
				}

				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados");
			} finally {
				connectionBD.fechar();
			}
		}

		return nomeExame;
	}

}
