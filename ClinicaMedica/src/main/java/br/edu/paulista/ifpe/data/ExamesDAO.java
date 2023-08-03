package br.edu.paulista.ifpe.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.paulista.ifpe.model.entidades.Exame;

public class ExamesDAO {
	public List<Exame> buscar(Exame e) throws Exception {
		String sql = "SELECT id, tipo_exame " + "FROM exame " + "ORDER BY tipo_exame";
		ConnectionBD connectionBD = new ConnectionBD();
		Connection conn = connectionBD.abrir();

		PreparedStatement comando = conn.prepareStatement(sql);
		ResultSet resultado = comando.executeQuery();

		List<Exame> lista = new ArrayList<Exame>();

		while (resultado.next()) {
			Exame linha = new Exame();
			linha.setId(resultado.getString("id"));
			linha.setNome(resultado.getString("tipo_exame"));

			lista.add(linha);
		}
		resultado.close();
		comando.close();
		conn.close();
		return lista;
	}

	public boolean excluir(Exame exame) {
		try {
			String sql = "DELETE FROM exame WHERE id = ?";
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