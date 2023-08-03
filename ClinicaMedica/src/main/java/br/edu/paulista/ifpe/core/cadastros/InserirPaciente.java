package br.edu.paulista.ifpe.core.cadastros;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import br.edu.paulista.ifpe.data.ConnectionBD;

public class InserirPaciente {
	public void inserirDados(String nome, String cpf, String rg, String telefone, String email, String rua,
			String numero, String complemento, String bairro, String cep, String cidade, String uf, String nascimento,
			String sexo, String estadoCivil, String nomePai, String nomeMae) {

		ConnectionBD conn = new ConnectionBD();
		PreparedStatement st;
		String query = "INSERT INTO paciente (nome, cpf, rg, telefone, email, rua, numero, complemento, bairro, cep, "
				+ "cidade, uf, nascimento, sexo, estado_civil, nome_pai, nome_mae) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		try {
			st = conn.abrir().prepareStatement(query);
			st.setString(1, nome);
			st.setString(2, cpf);
			st.setString(3, rg);
			st.setString(4, telefone);
			st.setString(5, email);
			st.setString(6, rua);
			st.setString(7, numero);
			st.setString(8, complemento);
			st.setString(9, bairro);
			st.setString(10, cep);
			st.setString(11, cidade);
			st.setString(12, uf);
			st.setString(13, nascimento);
			st.setString(14, sexo);
			st.setString(15, estadoCivil);
			st.setString(16, nomePai);
			st.setString(17, nomeMae);

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
