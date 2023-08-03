package br.edu.paulista.ifpe.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.paulista.ifpe.model.entidades.Medico;

public class MedicoDAO {
	public List<Medico> buscar(Medico m) throws Exception {
		String sql = "SELECT id, crm, nome, cpf, telefone, especialidade " + "FROM medico " + "ORDER BY nome";
		ConnectionBD connectionBD = new ConnectionBD();
		Connection conn = connectionBD.abrir();

		PreparedStatement comando = conn.prepareStatement(sql);
		ResultSet resultado = comando.executeQuery();

		List<Medico> lista = new ArrayList<Medico>();

		while (resultado.next()) {
			Medico linha = new Medico();
			linha.setId(resultado.getString("id"));
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

	public boolean excluir(Medico medico) {
		try {
			String sql = "DELETE FROM medico WHERE id = ?";
			ConnectionBD connectionBD = new ConnectionBD();
			Connection conn = connectionBD.abrir();

			PreparedStatement comando = conn.prepareStatement(sql);
			comando.setString(1, medico.getId());
			comando.executeUpdate();

			comando.close();
			conn.close();
			return true;
		} catch (SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "Você não pode excluir um médico com consultas marcadas");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir o médico");
		}
		return false;
	}
}
