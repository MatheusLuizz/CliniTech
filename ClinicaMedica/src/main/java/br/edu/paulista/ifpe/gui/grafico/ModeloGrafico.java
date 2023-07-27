package br.edu.paulista.ifpe.gui.grafico;

public class ModeloGrafico {

	private String label;
	private double valores[];

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double[] getValores() {
		return valores;
	}

	public void setValores(double[] valores) {
		this.valores = valores;
	}

	public ModeloGrafico(String label, double[] valores) {
		this.label = label;
		this.valores = valores;
	}

	public ModeloGrafico() {
	}

	public double getValoresMaximos() {
		double max = 0;
		for (double v : valores) {
			if (v > max) {
				max = v;
			}
		}
		return max;
	}
}