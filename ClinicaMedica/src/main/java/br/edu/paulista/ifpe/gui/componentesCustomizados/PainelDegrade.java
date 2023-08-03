package br.edu.paulista.ifpe.gui.componentesCustomizados;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PainelDegrade extends JPanel {
	private Color corInicio;
	private Color corFim;

	public PainelDegrade() {
		corInicio = Color.decode("#1CB5E0");
		corFim = Color.decode("#000046");
	}

	public void setColors(Color inicio, Color fim) {
		corInicio = inicio;
		corFim = fim;
		repaint();
	}

	public Color getBackgroundColor() {
		return getBackground();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		int startX = 0;
		int startY = 0;
		int endX = getWidth();
		int endY = getHeight();

		GradientPaint gradientPaint = new GradientPaint(startX, startY, corInicio, endX, endY, corFim);

		g2d.setPaint(gradientPaint);
		g2d.fillRect(startX, startY, endX, endY);
	}
}