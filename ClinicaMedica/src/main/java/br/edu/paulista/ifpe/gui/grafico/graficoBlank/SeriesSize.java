package br.edu.paulista.ifpe.gui.grafico.graficoBlank;

public class SeriesSize {

	private double x;
	private double y;
	private double largura;
	private double altura;

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return largura;
	}

	public void setWidth(double largura) {
		this.largura = largura;
	}

	public double getHeight() {
		return altura;
	}

	public void setHeight(double altura) {
		this.altura = altura;
	}

	public SeriesSize(double x, double y, double largura, double altura) {
		this.x = x;
		this.y = y;
		this.largura = largura;
		this.altura = altura;
	}

	public SeriesSize() {
	}
}