package br.edu.paulista.ifpe.model.tablemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.edu.paulista.ifpe.gui.componentesCustomizados.PainelAcao;
import br.edu.paulista.ifpe.model.entidades.Medico;

@SuppressWarnings("serial")
public class MedicTableModel extends AbstractTableModel {

	private Vector<String> colunas;
	private Vector<Vector<Object>> linhas;
	private List<Medico> medicos;

	public MedicTableModel() {
		colunas = new Vector<>();
		colunas.add("ID");
		colunas.add("Nome");
		colunas.add("CRM");
		colunas.add("CPF");
		colunas.add("Telefone");
		colunas.add("Especialidade");
		colunas.add("Ações");
		linhas = new Vector<>();
		medicos = new ArrayList<>();
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
	public Class<?> getColumnClass(int coluna) {
		if (coluna == 6) {
			return PainelAcao.class;
		}
		return String.class;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		if (coluna == 6) {
			return null;
		}
		return linhas.get(linha).get(coluna);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return column == 6;
	}

	public void adicionar(List<Medico> lista) {
		linhas = new Vector<>();
		medicos = new ArrayList<>(lista);
		for (Medico m : lista) {

			Vector<Object> linha = new Vector<>();
			linha.add(m.getId());
			linha.add(m.getNome());
			linha.add(m.getCrm());
			linha.add(m.getCpf());
			linha.add(m.getTelefone());
			linha.add(m.getEspecialidade());
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

	public List<Medico> getMedicos() {
		return medicos;
	}

	public Medico getMedico(int row) {
		if (row >= 0 && row < linhas.size()) {
			Vector<Object> linha = linhas.get(row);
			if (linha != null && linha.size() > 4) {
				String idMedico = (String) linha.get(0);
				for (Medico medico : medicos) {
					if (medico.getId().equals(idMedico)) {
						return medico;
					}
				}
			}
		}
		return null;
	}

	public void removeMedicoAt(int row) {
		if (row >= 0 && row < linhas.size()) {
			linhas.remove(row);
			fireTableRowsDeleted(row, row);
		}
	}
}
