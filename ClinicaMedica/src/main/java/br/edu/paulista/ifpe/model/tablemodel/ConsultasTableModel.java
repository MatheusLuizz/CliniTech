package br.edu.paulista.ifpe.model.tablemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.edu.paulista.ifpe.gui.componentesCustomizados.PainelAcao;
import br.edu.paulista.ifpe.model.entidades.Consulta;

@SuppressWarnings("serial")
public class ConsultasTableModel extends AbstractTableModel {

	private Vector<String> colunas;
	private Vector<Vector<Object>> linhas;
	private List<Consulta> consultas;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConsultasTableModel() {
		colunas = new Vector();
		colunas.add("ID");
		colunas.add("Paciente");
		colunas.add("Medico");
		colunas.add("Data");
		colunas.add("<html>Horário</html>");
		colunas.add("Ações");
		linhas = new Vector();
		consultas = new ArrayList<>();
	}

	public int getRowCount() {

		int totalLinhas = linhas.size();
		return totalLinhas;
	}

	public int getColumnCount() {

		int totalColunas = colunas.size();
		return totalColunas;
	}

	public String getColumnName(int coluna) {

		String nomeColuna = (String) colunas.get(coluna);
		return nomeColuna;
	}

	public Class<?> getColumnClass(int coluna) {
		if (coluna == 5) {
			return PainelAcao.class;
		}
		return String.class;
	}

	public Object getValueAt(int linha, int coluna) {
		if (coluna == 5) {
			return null;
		}
		return linhas.get(linha).get(coluna);
	}

	public boolean isCellEditable(int row, int column) {
		return column == 5;
	}

	@SuppressWarnings({})
	public void adicionar(List<Consulta> lista) {
		linhas = new Vector<>();
		consultas = new ArrayList<>(lista);
		for (Consulta c : lista) {

			Vector<Object> linha = new Vector<>();
			linha.add(c.getId());
			linha.add(c.getNomePaciente());
			linha.add(c.getNomeMedico());
			linha.add(c.getData());
			linha.add(c.getHorario());

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

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public Consulta getConsulta(int row) {
		if (row >= 0 && row < linhas.size()) {
			Vector<Object> linha = linhas.get(row);
			if (linha != null && linha.size() > 3) {
				String idConsulta = (String) linha.get(0);
				for (Consulta consulta : consultas) {
					if (consulta.getId().equals(idConsulta)) {
						return consulta;
					}
				}
			}
		}
		return null;
	}

	public void removeConsultaAt(int row) {
		if (row >= 0 && row < linhas.size()) {
			linhas.remove(row);
			fireTableRowsDeleted(row, row);
		}
	}
}
