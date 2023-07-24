package br.edu.paulista.ifpe.gui.grafico;

import java.awt.Color;

public class LegendaModelo {

	private String nome;
	private Color cor;
	private Color colorLight;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	public Color getColorLight() {
		return colorLight;
	}

	public void setColorLight(Color colorLight) {
		this.colorLight = colorLight;
	}

	public LegendaModelo(String nome, Color cor, Color colorLight) {
		this.nome = nome;
		this.cor = cor;
		this.colorLight = colorLight;
	}

	public LegendaModelo() {
	}
}