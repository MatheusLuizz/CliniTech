package br.edu.paulista.ifpe.core;

import javax.swing.JTextField;

public class LimparCamposMedico {

	public void limparCampos(JTextField txtCrm, JTextField txtNome, JTextField txtCpf, JTextField txtRg,
			JTextField txtTelefone, JTextField txtEspecialidade) {
		txtCrm.setText("");
		txtNome.setText("");
		txtCpf.setText("");
		txtRg.setText("");
		txtTelefone.setText("");
		txtEspecialidade.setText("");

		txtCrm.requestFocus();
	}
}
