package br.edu.paulista.ifpe.gui.grafico;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CorLabel extends JLabel {

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int width = getWidth();
		int height = getHeight();
		int tamanho = Math.min(width, height) - 4;
		int x = (width - tamanho) / 2;
		int y = (height - tamanho) / 2;
		g2.setPaint(new GradientPaint(0, 0, getBackground(), width, 0, getForeground()));
		g2.fillOval(x, y, tamanho, tamanho);
	}
}
