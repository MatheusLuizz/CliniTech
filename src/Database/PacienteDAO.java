package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entidades.Paciente;

public class PacienteDAO {
	public List <Paciente> buscar (Paciente p) throws Exception {
		// Definindo o código SQL
		String sql = "SELECT nome, telefone, email, nascimento, sexo " +
	            "FROM paciente " +
	            "ORDER BY nome";
		
		//Abrindo a conexão (Retorno armazenado na variável conn)
		Connection conn = ConnectionBD.abrir();
		
		PreparedStatement comando = conn.prepareStatement(sql);
		ResultSet resultado = comando.executeQuery();
		
		List <Paciente> lista = new ArrayList <Paciente> ();
		
		// Percorrendo o resultado, armazenando os valores em uma lista
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
		
		//Retorna a lista com o resultado da consulta
		return lista;
	}
}