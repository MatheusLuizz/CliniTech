package br.edu.paulista.ifpe.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.paulista.ifpe.model.entidades.Remedio;

public class RemediosDAO {
	public List<Remedio> buscar(Remedio r) throws Exception {
		String sql = "SELECT nome_remedio, apresentacao " + "FROM remedio " + "ORDER BY nome_remedio";
		ConnectionBD connectionBD = new ConnectionBD();
		Connection conn = connectionBD.abrir();

		PreparedStatement comando = conn.prepareStatement(sql);
		ResultSet resultado = comando.executeQuery();

		List<Remedio> lista = new ArrayList<Remedio>();

		while (resultado.next()) {
			Remedio linha = new Remedio();
			linha.setNome(resultado.getString("nome_remedio"));
			linha.setApresentacao(resultado.getString("apresentacao"));

			lista.add(linha);
		}
		resultado.close();
		comando.close();
		conn.close();

		return lista;
	}
}