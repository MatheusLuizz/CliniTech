package br.edu.paulista.ifpe.model.tablemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.edu.paulista.ifpe.model.entidades.Exame;

@SuppressWarnings({ "serial" })
public class ExameMarcadoTableModel extends AbstractTableModel {
	private Vector<String> colunas;
	private Vector<Vector<Object>> linhas;
	private List<Exame> exames;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ExameMarcadoTableModel() {
		colunas = new Vector();
		colunas.add("id");
		colunas.add("Nome");
		colunas.add("Medico");
		colunas.add("Exame");
		colunas.add("Data");
		colunas.add("Hora");
		linhas = new Vector();
		exames = new ArrayList<>();
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.size();
	}

	@Override
	public String getColumnName(int coluna) {
		return colunas.get(coluna);
	}

	@Override
	public Object getValueAt(int linha, int coluna) {

		return linhas.get(linha).get(coluna);
	}

	public void adicionar(List<Exame> lista) {
		linhas = new Vector<>();
		exames = new ArrayList<>(lista);
		for (Exame e : lista) {

			Vector<Object> linha = new Vector<>();
			linha.add(e.getId());
			linha.add(e.getIdPaciente());
			linha.add(e.getIdMedico());
			linha.add(e.getIdExame());
			linha.add(e.getData());
			linha.add(e.getHora());

			linhas.add(linha);
		}
		fireTableDataChanged();
	}

	public void limpar() {
		int rowCount = linhas.size();
		if (rowCount > 0) {
			linhas.clear();
			fireTableRowsDeleted(0, rowCount - 1);
		}
	}

	public List<Exame> getExames() {
		return exames;
	}

	public Exame getExame(int row) {

		if (row >= 0 && row < linhas.size()) {
			Vector<Object> linha = linhas.get(row);
			if (linha != null && linha.size() > 1) {
				String idExame = (String) linha.get(0);
				for (Exame exame : exames) {
					if (exame.getId().equals(idExame)) {
						return exame;
					}
				}
			}
		}
		return null;
	}

	public void removeExameAt(int row) {

		if (row >= 0 && row < linhas.size()) {
			linhas.remove(row);
			fireTableRowsDeleted(row, row);
		}
	}
}
