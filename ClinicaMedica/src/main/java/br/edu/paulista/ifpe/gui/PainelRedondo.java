package br.edu.paulista.ifpe.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import br.edu.paulista.ifpe.gui.grafico.GraficoCurvas;
import br.edu.paulista.ifpe.gui.grafico.ModeloGrafico;

@SuppressWarnings("serial")
public class PainelRedondo extends JPanel {
	GraficoCurvas graficoCurvo;
	public PainelRedondo() {
		setOpaque(false);
		setLayout(null);
		graficoCurvo = new GraficoCurvas();
		graficoCurvo.setBounds(0, 21, 600, 351);
		add(graficoCurvo);
		init();
	}
	private void init() {
		graficoCurvo.adicionarLegenda("Entradas", new Color(12,84,175), new Color(0,108,247));
		graficoCurvo.adicionarLegenda("Lucro", new Color(54,4,143), new Color(104,49,200));
		graficoCurvo.adicionarLegenda("Despesas", new Color(5,125,0), new Color(95,209,69));
		graficoCurvo.adicionarLegenda("Custos", new Color(186,37,37), new Color(241,100,120));
		graficoCurvo.adicionarDado(new ModeloGrafico("Janeiro", new double[] {500, 200, 80, 89}));
		graficoCurvo.adicionarDado(new ModeloGrafico("Fevereiro", new double[] {1000, 750, 90, 150}));
		graficoCurvo.adicionarDado(new ModeloGrafico("Março", new double[] {200, 350, 460, 900}));
		graficoCurvo.adicionarDado(new ModeloGrafico("Abril", new double[] {480, 150, 750, 700}));
		graficoCurvo.adicionarDado(new ModeloGrafico("Maio", new double[] {350, 540, 3000, 150}));
		graficoCurvo.adicionarDado(new ModeloGrafico("Junho", new double[] {190, 280, 81, 200}));
		graficoCurvo.start();
	}
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int width = getWidth();
		int height = getHeight();
		g2.setColor(new Color(150,150,150));
		g2.fillRoundRect(0, 0, width, height, 15, 15);
		g2.dispose();
		super.paint(g);
	}
	public void updateChartData() {
        graficoCurvo.clear(); // Limpa os dados anteriores
        init(); // Adiciona os novos dados ao gráfico
        repaint(); // Repinta o painel para exibir os dados atualizados
    }
}
