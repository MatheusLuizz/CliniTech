package br.edu.paulista.ifpe.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.paulista.ifpe.model.entidades.Paciente;

public class DetalhesPacienteDAO {
	public List<Paciente> buscar(Paciente p) throws Exception {

		String sql = "SELECT pac.nome, em.data, em.hora, c.data, c.hora, h.observacoes, h.proximos_passos, hr.id_remedio, " +
		             "pac.nascimento, pac.sexo, pac.estado_civil, pac.cidade " +
		             "FROM paciente pac " +
		             "INNER JOIN exame_marcado em ON pac.id = em.id_paciente " +
		             "INNER JOIN consulta c ON pac.id = c.id_paciente " +
		             "INNER JOIN historico h ON pac.id = h.id_paciente " +
		             "INNER JOIN historico_remedio hr ON h.id = hr.id_historico " +
		             "WHERE pac.id = ?";

		ConnectionBD connectionBD = new ConnectionBD();
		Connection conn = connectionBD.abrir();

		PreparedStatement comando = conn.prepareStatement(sql);
		comando.setString(1, p.getId());

		ResultSet resultado = comando.executeQuery();

		List<Paciente> lista = new ArrayList<Paciente>();

		while (resultado.next()) {
			Paciente linha = new Paciente();
			linha.setNome(resultado.getString("nome"));
			linha.setExameMarcadoData(resultado.getString("data"));
			linha.setExameMarcadoHora(resultado.getString("hora"));
			linha.setConsultaData(resultado.getString("data"));
			linha.setConsultaHora(resultado.getString("hora"));
			linha.setHistoricoObservacoes(resultado.getString("observacoes"));
			linha.setHistoricoProximosPassos(resultado.getString("proximos_passos"));
			linha.setHistoricoRemedioId(resultado.getString("id_remedio"));
			linha.setNascimento(resultado.getString("nascimento"));
			linha.setSexo(resultado.getString("sexo"));
			linha.setEstadoCivil(resultado.getString("estado_civil"));
			linha.setCidade(resultado.getString("cidade"));

			lista.add(linha);
		}

		resultado.close();
		comando.close();
		conn.close();

		return lista;
	}
}