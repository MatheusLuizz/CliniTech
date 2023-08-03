package br.edu.paulista.ifpe.gui.componentesCustomizados;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFormattedTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public class CampoTextoFormatadoRedondo extends JFormattedTextField {

	private Shape shape;
	private MaskFormatter formatter;

	public CampoTextoFormatadoRedondo(MaskFormatter mascara, int tamanho) {
		super(mascara);
		setOpaque(false);
		this.formatter = mascara;
		AbstractDocument document = (AbstractDocument) getDocument();
		document.setDocumentFilter(new FixedFormatFilter());
	}

	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
		super.paintComponent(g);
	}

	protected void paintBorder(Graphics g) {
		g.setColor(getForeground());
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
	}

	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
		}
		return shape.contains(x, y);
	}

	private class FixedFormatFilter extends DocumentFilter {
		@Override
		public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
			StringBuilder newText = new StringBuilder(currentText);
			newText.replace(offset, offset + length, text);

			if (formatter.getMask().indexOf(newText.toString()) == 0) {
				super.replace(fb, offset, length, text, attrs);
			}
		}

		@Override
		public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
			String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
			StringBuilder newText = new StringBuilder(currentText);
			newText.delete(offset, offset + length);

			if (formatter.getMask().indexOf(newText.toString()) == 0) {
				super.remove(fb, offset, length);
			}
		}
	}
}