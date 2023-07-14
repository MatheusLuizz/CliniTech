package br.edu.paulista.ifpe.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.paulista.ifpe.model.entidades.Exame;

public class ExamesDAO {
	public List <Exame> buscar (Exame e) throws Exception {
		// Definindo o código SQL
		String sql = "SELECT tipo_exame " +
	            "FROM exame " +
	            "ORDER BY tipo_exame";
		
		//Abrindo a conexão (Retorno armazenado na variável conn)
		ConnectionBD connectionBD = new ConnectionBD();
		Connection conn = connectionBD.abrir();
		
		PreparedStatement comando = conn.prepareStatement(sql);
		ResultSet resultado = comando.executeQuery();
		
		List <Exame> lista = new ArrayList <Exame> ();
		
		// Percorrendo o resultado, armazenando os valores em uma lista
		while (resultado.next()) {
			Exame linha = new Exame();
			linha.setTipoExame(resultado.getString("tipo_exame"));
			
			lista.add(linha);
		}
		resultado.close();
		comando.close();
		conn.close();
		
		//Retorna a lista com o resultado da consulta
		return lista;
	}
}