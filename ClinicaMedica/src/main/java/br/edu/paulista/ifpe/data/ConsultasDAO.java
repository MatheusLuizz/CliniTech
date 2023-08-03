package br.edu.paulista.ifpe.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.paulista.ifpe.model.entidades.Consulta;

public class ConsultasDAO {
	public List<Consulta> buscar(Consulta c) throws Exception {
		String sql = "SELECT c.id, p.nome as nome_paciente, m.nome as nome_medico, c.data, c.hora " + "FROM consulta c "
				+ "JOIN paciente p ON c.id_paciente = p.id " + "JOIN medico m ON c.id_medico = m.id "
				+ "ORDER BY c.data DESC, c.hora, p.nome, m.nome";
		ConnectionBD connectionBD = new ConnectionBD();
		Connection conn = connectionBD.abrir();

		PreparedStatement comando = conn.prepareStatement(sql);
		ResultSet resultado = comando.executeQuery();

		List<Consulta> lista = new ArrayList<Consulta>();

		while (resultado.next()) {
			Consulta linha = new Consulta();
			linha.setId(resultado.getString("id"));
			linha.setNomePaciente(resultado.getString("nome_paciente"));
			linha.setNomeMedico(resultado.getString("nome_medico"));
			linha.setData(resultado.getString("data"));
			linha.setHorario(resultado.getString("hora"));

			lista.add(linha);
		}
		resultado.close();
		comando.close();
		conn.close();
		return lista;
	}

	public boolean excluir(Consulta consulta) {
		try {
			String sql = "DELETE FROM consulta WHERE id = ?";
			ConnectionBD connectionBD = new ConnectionBD();
			Connection conn = connectionBD.abrir();

			PreparedStatement comando = conn.prepareStatement(sql);
			comando.setString(1, consulta.getId());
			comando.executeUpdate();

			comando.close();
			conn.close();
			return true;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir a consulta");
		}
		return false;
	}
}