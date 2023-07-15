package br.edu.paulista.ifpe.core;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class LimiteCaracteres {
	public void adicionarLimiteCaracteres(JTextField campoTexto, final int caracteresMaximos) {
		DocumentFilter documentFilter = new DocumentFilter() {
			public void insertString(FilterBypass fb, int offset, String texto, AttributeSet attrs)
					throws BadLocationException {
				if ((fb.getDocument().getLength() + texto.length()) <= caracteresMaximos) {
					super.insertString(fb, offset, texto, attrs);
				}
			}

			public void replace(FilterBypass fb, int offset, int length, String texto, AttributeSet attrs)
					throws BadLocationException {
				int newLength = fb.getDocument().getLength() - length + texto.length();
				if (newLength <= caracteresMaximos) {
					super.replace(fb, offset, length, texto, attrs);
				}
			}
		};

		PlainDocument documento = new PlainDocument();
		documento.setDocumentFilter(documentFilter);

		campoTexto.setDocument(documento);
	}
}