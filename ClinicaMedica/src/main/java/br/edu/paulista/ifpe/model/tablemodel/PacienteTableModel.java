package br.edu.paulista.ifpe.model.tablemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.edu.paulista.ifpe.gui.componentesCustomizados.PainelAcao;
import br.edu.paulista.ifpe.model.entidades.Paciente;

@SuppressWarnings("serial")
public class PacienteTableModel extends AbstractTableModel {
	private Vector<String> colunas;
	private Vector<Vector<Object>> linhas;
	private List<Paciente> pacientes;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PacienteTableModel() {
		colunas = new Vector();
		colunas.add("ID");
		colunas.add("Nome");
		colunas.add("Telefone");
		colunas.add("Email");
		colunas.add("Nascimento");
		colunas.add("Sexo");
		colunas.add("Ações");
		linhas = new Vector<>();
		pacientes = new ArrayList<>();
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void adicionar(List<Paciente> lista) {
		linhas = new Vector<>();
		pacientes = new ArrayList<>(lista);
		for (Paciente p : lista) {

			Vector<Object> linha = new Vector();
			linha.add(p.getId());
			linha.add(p.getNome());
			linha.add(p.getTelefone());
			linha.add(p.getEmail());
			linha.add(p.getNascimento());
			linha.add(p.getSexo());
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

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public Paciente getPaciente(int row) {
		if (row >= 0 && row < linhas.size()) {
			Vector<Object> linha = linhas.get(row);
			if (linha != null && linha.size() > 4) {
				String idPaciente = (String) linha.get(0);
				for (Paciente paciente : pacientes) {
					if (paciente.getId().equals(idPaciente)) {
						return paciente;
					}
				}
			}
		}
		return null;
	}

	public void removePacienteAt(int row) {
		if (row >= 0 && row < linhas.size()) {
			linhas.remove(row);
			fireTableRowsDeleted(row, row);
		}
	}

}
