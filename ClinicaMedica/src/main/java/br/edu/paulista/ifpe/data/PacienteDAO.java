package br.edu.paulista.ifpe.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.paulista.ifpe.model.entidades.Paciente;

public class PacienteDAO {
	public List<Paciente> buscar(Paciente p) throws Exception {

		String sql = "SELECT nome, telefone, email, nascimento, sexo " + "FROM paciente " + "ORDER BY nome";
		ConnectionBD connectionBD = new ConnectionBD();
		Connection conn = connectionBD.abrir();

		PreparedStatement comando = conn.prepareStatement(sql);
		ResultSet resultado = comando.executeQuery();

		List<Paciente> lista = new ArrayList<Paciente>();

		while (resultado.next()) {
			Paciente linha = new Paciente();
			linha.setNome(resultado.getString("nome"));
			linha.setTelefone(resultado.getString("telefone"));
			linha.setEmail(resultado.getString("email"));
			linha.setNascimento(resultado.getString("nascimento"));
			linha.setSexo(resultado.getString("sexo"));

			lista.add(linha);
		}
		resultado.close();
		comando.close();
		conn.close();
		return lista;
	}
}