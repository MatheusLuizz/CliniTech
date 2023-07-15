package br.edu.paulista.ifpe.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.paulista.ifpe.model.entidades.Medico;

public class MedicoDAO {
	public List<Medico> buscar(Medico m) throws Exception {
		String sql = "SELECT crm, nome, cpf, telefone, especialidade " + "FROM medico " + "ORDER BY nome";
		ConnectionBD connectionBD = new ConnectionBD();
		Connection conn = connectionBD.abrir();

		PreparedStatement comando = conn.prepareStatement(sql);
		ResultSet resultado = comando.executeQuery();

		List<Medico> lista = new ArrayList<Medico>();

		while (resultado.next()) {
			Medico linha = new Medico();
			linha.setCrm(resultado.getString("crm"));
			linha.setNome(resultado.getString("nome"));
			linha.setCpf(resultado.getString("cpf"));
			linha.setTelefone(resultado.getString("telefone"));
			linha.setEspecialidade(resultado.getString("especialidade"));

			lista.add(linha);
		}
		resultado.close();
		comando.close();
		conn.close();

		return lista;
	}
}
