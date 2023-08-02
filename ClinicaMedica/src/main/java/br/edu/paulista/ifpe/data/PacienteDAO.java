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
	    } catch (SQLIntegrityConstraintViolationException e) {
	        JOptionPane.showMessageDialog(null, "Você não pode excluir um paciente com consultas marcadas");
	    } catch (Exception ex) {
	    	JOptionPane.showMessageDialog(null, "Erro ao excluir o paciente");
	    	ex.printStackTrace();
	    }
	    return false;
	}
	public Paciente buscarTodos(int idPaciente) throws Exception {

	    String sql = "SELECT " +
	                 "   p.id, " +
	                 "   p.nome, " +
	                 "   p.cpf, " +
	                 "   p.telefone, " +
	                 "   p.nascimento, " +
	                 "   p.sexo, " +
	                 "   p.estado_civil, " +
	                 "   p.nome_mae, " +
	                 "   p.cidade, " +
	                 "   p.bairro, " +
	                 "   h.observacoes, " +
	                 "   h.proximos_passos, " +
	                 "   r.nome AS nome_remedio " +
	                 "FROM " +
	                 "   paciente p " +
	                 "INNER JOIN " +
	                 "   historico h ON p.id = h.id_paciente " +
	                 "INNER JOIN " +
	                 "   historico_remedio hr ON h.id = hr.id_historico " +
	                 "INNER JOIN " +
	                 "   remedio r ON hr.id_remedio = r.id " +
	                 "WHERE p.id = ? " + // Adicionar a cláusula WHERE para filtrar pelo ID do paciente
	                 "ORDER BY " +
	                 "   p.nome";

	    ConnectionBD connectionBD = new ConnectionBD();
	    Connection conn = connectionBD.abrir();

	    PreparedStatement comando = conn.prepareStatement(sql);
	    comando.setInt(1, idPaciente); // Definir o valor do ID do paciente no parâmetro da cláusula WHERE
	    ResultSet resultado = comando.executeQuery();

	    Paciente paciente = null;

	    while (resultado.next()) {
	        paciente = new Paciente();
	        paciente.setId(resultado.getString("id"));
	        paciente.setNome(resultado.getString("nome"));
	        paciente.setCpf(resultado.getString("cpf"));
	        paciente.setTelefone(resultado.getString("telefone"));
	        paciente.setNascimento(resultado.getString("nascimento"));
	        paciente.setSexo(resultado.getString("sexo"));
	        paciente.setEstadoCivil(resultado.getString("estado_civil"));
	        paciente.setNomeMae(resultado.getString("nome_mae"));
	        paciente.setCidade(resultado.getString("cidade"));
	        paciente.setBairro(resultado.getString("bairro"));
	        paciente.setHistoricoObservacoes(resultado.getString("observacoes"));
	        paciente.setHistoricoProximosPassos(resultado.getString("proximos_passos"));
	        paciente.setHistoricoRemedio(resultado.getString("nome_remedio"));
	    }

	    resultado.close();
	    comando.close();
	    conn.close();
	    return paciente;
	}
}