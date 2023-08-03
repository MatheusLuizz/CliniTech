package br.edu.paulista.ifpe.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.paulista.ifpe.model.entidades.Remedio;

public class RemediosDAO {
	public List<Remedio> buscar(Remedio r) throws Exception {
		String sql = "SELECT id, nome, apresentacao " + "FROM remedio " + "ORDER BY nome";
		ConnectionBD connectionBD = new ConnectionBD();
		Connection conn = connectionBD.abrir();

		PreparedStatement comando = conn.prepareStatement(sql);
		ResultSet resultado = comando.executeQuery();

		List<Remedio> lista = new ArrayList<Remedio>();

		while (resultado.next()) {
			Remedio linha = new Remedio();
			linha.setId(resultado.getString("id"));
			linha.setNome(resultado.getString("nome"));
			linha.setApresentacao(resultado.getString("apresentacao"));

			lista.add(linha);
		}
		resultado.close();
		comando.close();
		conn.close();

		return lista;
	}

	public boolean excluir(Remedio remedio) {
		try {
			String sql = "DELETE FROM remedio WHERE id = ?";
			ConnectionBD connectionBD = new ConnectionBD();
			Connection conn = connectionBD.abrir();

			PreparedStatement comando = conn.prepareStatement(sql);
			comando.setString(1, remedio.getId());
			comando.executeUpdate();

			comando.close();
			conn.close();
			return true;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir o remedio");
		}
		return false;
	}
}