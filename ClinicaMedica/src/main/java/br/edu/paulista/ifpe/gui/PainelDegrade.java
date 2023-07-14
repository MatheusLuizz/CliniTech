package br.edu.paulista.ifpe.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PainelDegrade extends JPanel {
	private Color colorStart;
	private Color colorEnd;

	public PainelDegrade() {
		colorStart = Color.decode("#1CB5E0");
		colorEnd = Color.decode("#000046");
	}

	public void setColors(Color start, Color end) {
		colorStart = start;
		colorEnd = end;
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

		GradientPaint gradientPaint = new GradientPaint(startX, startY, colorStart, endX, endY, colorEnd);

		g2d.setPaint(gradientPaint);
		g2d.fillRect(startX, startY, endX, endY);
	}
}