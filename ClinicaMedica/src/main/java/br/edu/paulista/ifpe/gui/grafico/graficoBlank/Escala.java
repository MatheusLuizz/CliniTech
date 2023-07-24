package br.edu.paulista.ifpe.gui.grafico.graficoBlank;

public class Escala {

	private double min;
	private double max;
	private int maxTicks = 10;
	private double espacamentoTicks;
	private double faixa;
	private double niceMin;
	private double niceMax;

	public Escala(final double MIN, final double MAX) {
		min = MIN;
		max = MAX;
		calcular();
	}

	private void calcular() {
		faixa = niceNum(max - min, false);
		espacamentoTicks = niceNum(faixa / (maxTicks - 1), true);
		niceMin = Math.floor(min / espacamentoTicks) * espacamentoTicks;
		niceMax = Math.ceil(max / espacamentoTicks) * espacamentoTicks;
	}

	private double niceNum(final double RANGE, final boolean ROUND) {
		double expoente;
		double fracao;
		double niceFracao;

		expoente = Math.floor(Math.log10(RANGE));
		fracao = RANGE / Math.pow(10, expoente);

		if (ROUND) {
			if (fracao < 1.5) {
				niceFracao = 1;
			} else if (fracao < 3) {
				niceFracao = 2;
			} else if (fracao < 7) {
				niceFracao = 5;
			} else {
				niceFracao = 10;
			}
		} else {
			if (fracao <= 1) {
				niceFracao = 1;
			} else if (fracao <= 2) {
				niceFracao = 2;
			} else if (fracao <= 5) {
				niceFracao = 5;
			} else {
				niceFracao = 10;
			}
		}
		return niceFracao * Math.pow(10, expoente);
	}

	public void setMinMax(final double MIN, final double MAX) {
		min = MIN;
		max = MAX;
		calcular();
	}

	public void setMaxTicks(final int MAX_TICKS) {
		maxTicks = MAX_TICKS;
		calcular();
	}

	public double getEspacamentoTicks() {
		return espacamentoTicks;
	}

	public double getNiceMin() {
		return niceMin;
	}

	public double getNiceMax() {
		return niceMax;
	}

	public int getMaxTicks() {
		return maxTicks;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
		calcular();
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
		calcular();
	}
}