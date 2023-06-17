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
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nome_paciente, telefone_paciente, email_paciente, nascimento_paciente, sexo_paciente ");
		sql.append("FROM paciente");
		sql.append("ORDER BY nome_paciente");
		
		//Abrindo a conexão (Retorno armazenado na variável conn)
		Connection conn = ConnectionBD.abrir();
		
		PreparedStatement comando = conn.prepareStatement(sql.toString());
		comando.setString(1, "%" + p.getNome() + "%");
		
		ResultSet resultado = comando.executeQuery();
		
		List <Paciente> lista = new ArrayList <Paciente> ();
		
		// Percorrendo o resultado, armazenando os valores em uma lista
		while (resultado.next()) {
			Paciente linha = new Paciente();
			linha.setNome(resultado.getString("nome_paciente"));
			linha.setTelefone(resultado.getString("telefone_paciente"));
			linha.setEmail(resultado.getString("email_paciente"));
			linha.setNascimento(resultado.getString("nascimento_paciente"));
			linha.setSexo(resultado.getString("email_paciente"));
			
			lista.add(linha);
		}
		resultado.close();
		comando.close();
		conn.close();
		
		//Retorna a lista com o resultado da consulta
		return lista;
	}
}
