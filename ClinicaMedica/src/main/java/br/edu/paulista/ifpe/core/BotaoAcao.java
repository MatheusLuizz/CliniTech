package br.edu.paulista.ifpe.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class BotaoAcao extends JButton {
	private boolean mousePress;

	public BotaoAcao() {
		setContentAreaFilled(false);
		setBorder(new EmptyBorder(3, 3, 3, 3));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mousePress = true;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mousePress = false;
			}

		});
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int width = getWidth();
		int height = getHeight();
		int tamanho = Math.min(width, height);
		int x = (width - tamanho) / 2;
		int y = (height - tamanho) / 2;
		if (mousePress) {
			g2.setColor(new Color(158, 158, 158));
		} else {
			g2.setColor(new Color(199, 199, 199));
		}
		g2.fill(new Ellipse2D.Double(x, y, tamanho, tamanho));
		g2.dispose();
		super.paintComponent(g);

	}

}
