package br.edu.paulista.ifpe.core.cadastros;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import br.edu.paulista.ifpe.data.ConnectionBD;

public class InserirRemedios {
	public void inserirDados(String nome, String apresentacao) {
		ConnectionBD conn = new ConnectionBD();
		PreparedStatement st;
		String query = "INSERT INTO remedio (nome, apresentacao) VALUES (?,?);";
		try {
			st = conn.abrir().prepareStatement(query);
			st.setString(1, nome);
			st.setString(2, apresentacao);

			st.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao inserir os dados no banco de dados. Por favor, tente novamente.");
		} finally {
			conn.fechar();
		}
	}
}
