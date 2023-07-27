package br.edu.paulista.ifpe.gui.grafico.graficoBlank;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class GraficoEmBranco extends JComponent {

	public RenderizarGraficoEmBranco getRenderizarGraficoEmBranco() {
		return renderizarGraficoEmBranco;
	}

	public void setRenderizarGraficoEmBranco(RenderizarGraficoEmBranco renderizarGraficoEmBranco) {
		this.renderizarGraficoEmBranco = renderizarGraficoEmBranco;
	}

	public double getValoresMaximos() {
		return valoresMaximos;
	}

	public void setValoresMaximos(double valoresMaximos) {
		this.valoresMaximos = valoresMaximos;
		niceScale.setMax(valoresMaximos);
		repaint();
	}

	public double getMinValores() {
		return minValores;
	}

	public int getContagemLabel() {
		return contagemLabel;
	}

	public void setContagemLabel(int contagemLabel) {
		this.contagemLabel = contagemLabel;
	}

	public String getFormatoValores() {
		return formatoValores;
	}

	public void setFormatoValores(String formatoValores) {
		this.formatoValores = formatoValores;
		format.applyPattern(formatoValores);
	}

	private final DecimalFormat format = new DecimalFormat("#,##0.##");
	private Escala niceScale;
	private double valoresMaximos;
	private double minValores;
	private int contagemLabel;
	private String formatoValores = "#,##0.##";
	private RenderizarGraficoEmBranco renderizarGraficoEmBranco;

	public GraficoEmBranco() {
		setBackground(Color.WHITE);
		setOpaque(false);
		setForeground(Color.BLACK);
		setBorder(new EmptyBorder(35, 10, 10, 10));
		init();
	}

	private void init() {
		initValues(0, 10);
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent me) {
				mouseMove((Graphics2D) getGraphics(), me);
			}
		});
	}

	public void initValues(double minValores, double maxValores) {
		this.minValores = minValores;
		this.valoresMaximos = maxValores;
		niceScale = new Escala(minValores, maxValores);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (niceScale != null) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
			criarLinha(g2);
			criarValores(g2);
			criarTextoLabel(g2);
			serieRenderizacao(g2);
		}
	}

	private void criarLinha(Graphics2D g2) {
		g2.setColor(new Color(105, 105, 105, 105));
		Insets insets = getInsets();
		double alturaTexto = getAlturaTextoLabel(g2);
		double altura = getHeight() - (insets.top + insets.bottom) - alturaTexto;
		double espaco = altura / niceScale.getMaxTicks();
		double localizacaoY = insets.bottom + alturaTexto;
		double larguraTexto = getMaximosValoresLarguraTexto(g2);
		double espacamentoTexto = 5;
		for (int i = 0; i <= niceScale.getMaxTicks(); i++) {
			int y = (int) (getHeight() - localizacaoY);
			g2.drawLine((int) (insets.left + larguraTexto + espacamentoTexto), y, (int) getWidth() - insets.right, y);
			localizacaoY += espaco;
		}

	}

	private void criarValores(Graphics2D g2) {
		g2.setColor(getForeground());
		Insets insets = getInsets();
		double alturaTexto = getAlturaTextoLabel(g2);
		double altura = getHeight() - (insets.top + insets.bottom) - alturaTexto;
		double espaco = altura / niceScale.getMaxTicks();
		double contagemValores = niceScale.getNiceMin();
		double localizacaoY = insets.bottom + alturaTexto;
		FontMetrics ft = g2.getFontMetrics();
		for (int i = 0; i <= niceScale.getMaxTicks(); i++) {
			String texto = format.format(contagemValores);
			Rectangle2D r2 = ft.getStringBounds(texto, g2);
			double stringY = r2.getCenterY() * -1;
			double y = getHeight() - localizacaoY + stringY;
			g2.drawString(texto, insets.left, (int) y);
			localizacaoY += espaco;
			contagemValores += niceScale.getEspacamentoTicks();
		}
	}

	private void criarTextoLabel(Graphics2D g2) {
		if (contagemLabel > 0) {
			Insets insets = getInsets();
			double larguraTexto = getMaximosValoresLarguraTexto(g2);
			double espacamentoTexto = 5;
			double altura = getWidth() - insets.left - insets.right - larguraTexto - espacamentoTexto;
			double espaco = altura / contagemLabel;
			double localizacaoX = insets.left + larguraTexto + espacamentoTexto;
			double localizacaTexto = getHeight() - insets.bottom + 5;
			FontMetrics ft = g2.getFontMetrics();
			for (int i = 0; i < contagemLabel; i++) {
				double centroX = ((localizacaoX + espaco / 2));
				g2.setColor(getForeground());
				String texto = getTextoGrafico(i);
				Rectangle2D r2 = ft.getStringBounds(texto, g2);
				double textoX = centroX - r2.getWidth() / 2;
				g2.drawString(texto, (int) textoX, (int) localizacaTexto);
				localizacaoX += espaco;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void serieRenderizacao(Graphics2D g2) {
		if (renderizarGraficoEmBranco != null) {
			Insets insets = getInsets();
			double larguraTexto = getMaximosValoresLarguraTexto(g2);
			double alturaTexto = getAlturaTextoLabel(g2);
			double espacamentoTexto = 5;
			double largura = getWidth() - insets.left - insets.right - larguraTexto - espacamentoTexto;
			double altura = getHeight() - insets.top - insets.bottom - alturaTexto;
			double espaco = largura / contagemLabel;
			double localizacaoX = insets.left + larguraTexto + espacamentoTexto;
			for (int i = 0; i < contagemLabel; i++) {
				renderizarGraficoEmBranco.serieRenderizacao(this, g2,
						getRetangulo(i, altura, espaco, localizacaoX, insets.top), i);
			}
			List<Path2D.Double> gra = initGra(renderizarGraficoEmBranco.getMaximoLegendas());
			for (int i = 0; i < contagemLabel; i++) {
				renderizarGraficoEmBranco.serieRenderizacao(this, g2,
						getRetangulo(i, altura, espaco, localizacaoX, insets.top), i, gra);
			}
			renderizarGraficoEmBranco.renderizarGraficos(g2, gra);
		}
	}

	@SuppressWarnings("rawtypes")
	private List initGra(int size) {
		List<Path2D.Double> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(new Path2D.Double());
		}
		return list;
	}

	private void mouseMove(Graphics2D g2, MouseEvent evt) {
		if (renderizarGraficoEmBranco != null) {
			Insets insets = getInsets();
			double larguraTexto = getMaximosValoresLarguraTexto(g2);
			double alturaTexto = getAlturaTextoLabel(g2);
			double espacamentoTexto = 5;
			double largura = getWidth() - insets.left - insets.right - larguraTexto - espacamentoTexto;
			double altura = getHeight() - insets.top - insets.bottom - alturaTexto;
			double espaco = largura / contagemLabel;
			double localizacaoX = insets.left + larguraTexto + espacamentoTexto;
			for (int i = 0; i < contagemLabel; i++) {
				boolean parar = renderizarGraficoEmBranco.mouseMoving(this, evt, g2,
						getRetangulo(i, altura, espaco, localizacaoX, insets.top), i);
				if (parar) {
					break;
				}
			}
		}
	}

	private double getMaximosValoresLarguraTexto(Graphics2D g2) {
		double largura = 0;
		FontMetrics ft = g2.getFontMetrics();
		double contagemValores = niceScale.getNiceMin();
		for (int i = 0; i <= niceScale.getMaxTicks(); i++) {
			String texto = format.format(contagemValores);
			Rectangle2D r2 = ft.getStringBounds(texto, g2);
			double w = r2.getWidth();
			if (w > largura) {
				largura = w;
			}
			contagemValores += niceScale.getEspacamentoTicks();
		}
		return largura;
	}

	private int getAlturaTextoLabel(Graphics2D g2) {
		FontMetrics ft = g2.getFontMetrics();
		return ft.getHeight();
	}

	private String getTextoGrafico(int indice) {
		if (renderizarGraficoEmBranco != null) {
			return renderizarGraficoEmBranco.getTextoLabel(indice);
		} else {
			return "Label";
		}
	}

	public SeriesSize getRetangulo(int indice, double altura, double espaco, double startX, double startY) {
		double x = startX + espaco * indice;
		SeriesSize size = new SeriesSize(x, startY + 1, espaco, altura);
		return size;
	}

	public double getValoresDeSerie(double valores, double altura) {
		double max = niceScale.getEspacamentoTicks() * niceScale.getMaxTicks();
		double valoresPercentuais = valores * 100d / max;
		return altura * valoresPercentuais / 100d;
	}

	public Escala getNiceScale() {
		return niceScale;
	}

	public void setNiceScale(Escala niceScale) {
		this.niceScale = niceScale;
	}
}