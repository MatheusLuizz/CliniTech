package br.edu.paulista.ifpe.core;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class LimiteCaracteres {
    public void adicionarLimiteCaracteres(JTextField textField, final int maxCharacters) {
        DocumentFilter documentFilter = new DocumentFilter() {
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs)
                    throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length()) <= maxCharacters) {
                    super.insertString(fb, offset, text, attrs);
                }
            }

            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                int newLength = fb.getDocument().getLength() - length + text.length();
                if (newLength <= maxCharacters) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };

        PlainDocument document = new PlainDocument();
        document.setDocumentFilter(documentFilter);

        textField.setDocument(document);
    }
}