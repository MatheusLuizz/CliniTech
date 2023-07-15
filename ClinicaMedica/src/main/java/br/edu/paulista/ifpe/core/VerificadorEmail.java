package br.edu.paulista.ifpe.core;

import java.util.regex.Pattern;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VerificadorEmail extends InputVerifier {
	private Pattern emailPattern;

	public VerificadorEmail() {
		emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	}

	@Override
	public boolean verify(JComponent entrada) {
		JTextField campoTexto = (JTextField) entrada;
		String email = campoTexto.getText().trim();

		if (emailPattern.matcher(email).matches()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Formato de e-mail inválido", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
