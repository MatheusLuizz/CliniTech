package br.edu.paulista.ifpe.gui.grafico.graficoBlank;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.List;

public abstract class RenderizarGraficoEmBranco {

	public abstract String getTextoLabel(int indice);

	public abstract void serieRenderizacao(GraficoEmBranco grafico, Graphics2D g2, SeriesSize tamanho, int index);

	public abstract void serieRenderizacao(GraficoEmBranco grafico, Graphics2D g2, SeriesSize tamanho, int index,
			List<Path2D.Double> gradiente);

	public abstract boolean mouseMoving(GraficoEmBranco grafico, MouseEvent evt, Graphics2D g2, SeriesSize tamanho,
			int index);

	public abstract void renderizarGraficos(Graphics2D g2, List<Path2D.Double> gradiente);

	public abstract int getMaximoLegendas();
}