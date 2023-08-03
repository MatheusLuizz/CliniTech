package br.edu.paulista.ifpe.core.cadastros;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import br.edu.paulista.ifpe.data.ConnectionBD;

public class InserirMedico {
	public void inserirDados(String crm, String nome, String cpf, String rg, String telefone, String especialidade,
			byte[] filedata, String senha) {
		ConnectionBD conn = new ConnectionBD();
		PreparedStatement st;
		String query = "INSERT INTO medico (crm, nome, cpf, rg, telefone, especialidade, assinatura, senha) VALUES (?,?,?,?,?,?,?,?);";
		try {
			st = conn.abrir().prepareStatement(query);
			st.setString(1, crm);
			st.setString(2, nome);
			st.setString(3, cpf);
			st.setString(4, rg);
			st.setString(5, telefone);
			st.setString(6, especialidade);
			st.setBytes(7, filedata);
			st.setString(8, senha);

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
