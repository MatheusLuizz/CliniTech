package br.edu.paulista.ifpe.core.cadastros;

import javax.swing.JTextField;

public class LimparCamposPaciente {
	public void limparCampos(JTextField txtNome, JTextField txtCpf, JTextField txtRg, JTextField txtEmail,
			JTextField txtTelefone, JTextField txtRua, JTextField txtComplemento, JTextField txtNumero,
			JTextField txtBairro, JTextField txtCep, JTextField txtCidade, JTextField txtNascimento,
			JTextField txtEstadoCivil, JTextField txtNomePai, JTextField txtNomeMae) {
		txtNome.setText("");
		txtCpf.setText("");
		txtRg.setText("");
		txtEmail.setText("");
		txtTelefone.setText("");
		txtRua.setText("");
		txtComplemento.setText("");
		txtNumero.setText("");
		txtBairro.setText("");
		txtCep.setText("");
		txtCidade.setText("");
		txtNascimento.setText("");
		txtEstadoCivil.setText("");
		txtNomePai.setText("");
		txtNomeMae.setText("");

		txtNome.requestFocus();
	}
}
