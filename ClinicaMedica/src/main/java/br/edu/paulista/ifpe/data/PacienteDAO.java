package br.edu.paulista.ifpe.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.paulista.ifpe.model.entidades.Medico;
import br.edu.paulista.ifpe.model.entidades.Paciente;

public class PacienteDAO {
	public List<Paciente> buscar(Paciente p) throws Exception {

		String sql = "SELECT id, nome, telefone, email, nascimento, sexo " + "FROM paciente " + "ORDER BY nome";
		ConnectionBD connectionBD = new ConnectionBD();
		Connection conn = connectionBD.abrir();

		PreparedStatement comando = conn.prepareStatement(sql);
		ResultSet resultado = comando.executeQuery();

		List<Paciente> lista = new ArrayList<Paciente>();

		while (resultado.next()) {
			Paciente linha = new Paciente();
			linha.setId(resultado.getString("id"));
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
	public boolean excluir(Paciente paciente) {
	    try {
	        String sql = "DELETE FROM paciente WHERE id = ?";
	        ConnectionBD connectionBD = new ConnectionBD();
	        Connection conn = connectionBD.abrir();

	        PreparedStatement comando = conn.prepareStatement(sql);
	        comando.setString(1, paciente.getId());
	        comando.executeUpdate();

	        comando.close();
	        conn.close();
	        return true;
	    } catch (Exception ex) {
	    	JOptionPane.showMessageDialog(null, "Erro ao excluir o paciente");
	    }
	    return false;
	}
}