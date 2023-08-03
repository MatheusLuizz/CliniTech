package br.edu.paulista.ifpe.gui.componentesCustomizados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import br.edu.paulista.ifpe.gui.grafico.GraficoCurvas;

@SuppressWarnings("serial")
public class PainelGraficoMedico extends JPanel {
	GraficoCurvas graficoCurvo;
	@SuppressWarnings("unused")
	private int id;

	public PainelGraficoMedico(int id) {
		this.id = id;
		setOpaque(false);
		setLayout(null);
		graficoCurvo = new GraficoCurvas();
		graficoCurvo.setBounds(0, 0, 1149, 382);
		add(graficoCurvo);
		graficoCurvo.adicionarLegenda("Atendimentos", new Color(0, 108, 247), new Color(12, 84, 175));

		graficoCurvo.adicionarMedico(id);
		graficoCurvo.start();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int width = getWidth();
		int height = getHeight();
		g2.setColor(new Color(150, 150, 150));
		g2.fillRoundRect(0, 0, width, height, 15, 15);
		g2.dispose();
		super.paint(g);
	}

	private boolean atualizacaoEmAndamento = false;

	public void updateChartData() {
		if (!atualizacaoEmAndamento) {
			atualizacaoEmAndamento = true;
			graficoCurvo.clear();
			graficoCurvo.adicionarDados();
			graficoCurvo.start();
			atualizacaoEmAndamento = false;
			repaint();
		}
	}
}