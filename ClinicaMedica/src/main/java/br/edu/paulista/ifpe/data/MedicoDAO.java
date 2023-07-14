package br.edu.paulista.ifpe.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.paulista.ifpe.model.entidades.Medico;

public class MedicoDAO {
	public List <Medico> buscar (Medico m) throws Exception {
		// Definindo o código SQL
		String sql = "SELECT crm, nome, cpf, telefone, especialidade " +
	            "FROM medico " +
	            "ORDER BY nome";
		
		//Abrindo a conexão (Retorno armazenado na variável conn)
		ConnectionBD connectionBD = new ConnectionBD();
		Connection conn = connectionBD.abrir();
		
		PreparedStatement comando = conn.prepareStatement(sql);
		ResultSet resultado = comando.executeQuery();
		
		List <Medico> lista = new ArrayList <Medico> ();
		
		// Percorrendo o resultado, armazenando os valores em uma lista
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
		
		//Retorna a lista com o resultado da consulta
		return lista;
	}
}
