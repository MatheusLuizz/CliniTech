package br.edu.paulista.ifpe.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.paulista.ifpe.model.entidades.Exame;

public class ExameMarcadoDAO {
	public List<Exame> buscar(Exame e) throws Exception {
		String sql = "SELECT em.id, p.nome AS nome_paciente, m.nome AS nome_medico, e.tipo_exame AS nome_exame, em.data, em.hora \r\n"
				+ "	             FROM exame_marcado em \r\n"
				+ "	             INNER JOIN paciente p ON em.id_paciente = p.id \r\n"
				+ "	             INNER JOIN medico m ON em.id_medico = m.id \r\n"
				+ "	             INNER JOIN exame e ON em.id_exame = e.id \r\n"
				+ "	             ORDER BY em.data desc;";
		ConnectionBD connectionBD = new ConnectionBD();
		Connection conn = connectionBD.abrir();

		PreparedStatement comando = conn.prepareStatement(sql);
		ResultSet resultado = comando.executeQuery();

		List<Exame> lista = new ArrayList<Exame>();

		while (resultado.next()) {
			Exame linha = new Exame();
			linha.setId(resultado.getString("id"));
			linha.setIdPaciente(resultado.getString("nome_paciente"));
			linha.setIdMedico(resultado.getString("nome_medico"));
			linha.setIdExame(resultado.getString("nome_exame"));
			linha.setData(resultado.getString("data"));
			linha.setHora(resultado.getString("hora"));

			lista.add(linha);
		}
		resultado.close();
		comando.close();
		conn.close();
		return lista;
	}

	public boolean excluir(Exame exame) {
		try {
			String sql = "DELETE FROM exame_marcado WHERE id = ?";
			ConnectionBD connectionBD = new ConnectionBD();
			Connection conn = connectionBD.abrir();

			PreparedStatement comando = conn.prepareStatement(sql);
			comando.setString(1, exame.getId());
			comando.executeUpdate();

			comando.close();
			conn.close();
			return true;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir o exame");
		}
		return false;
	}
}
