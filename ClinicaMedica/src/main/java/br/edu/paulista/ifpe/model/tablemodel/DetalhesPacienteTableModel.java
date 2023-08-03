package br.edu.paulista.ifpe.model.tablemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.edu.paulista.ifpe.model.entidades.Paciente;

@SuppressWarnings("serial")
public class DetalhesPacienteTableModel extends AbstractTableModel {
	private Vector<String> colunas;
	private Vector<Vector<Object>> linhas;
	private List<Paciente> pacientes;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DetalhesPacienteTableModel() {
		colunas = new Vector();
		colunas.add("ID");
		colunas.add("Nome");
		colunas.add("Cpf");
		colunas.add("Telefone");
		colunas.add("Nascimento");
		colunas.add("Sexo");
		colunas.add("Estado civil");
		colunas.add("Nome da mãe");
		colunas.add("Cidade");
		colunas.add("Bairro");
		colunas.add("Observações");
		colunas.add("Próximos passos");
		colunas.add("Histórico de remédios");
		linhas = new Vector<>();
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
		if (coluna == 11) {
			return String.class;
		}
		return String.class;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {

		if (coluna == 11) { // Coluna "Próximos Passos"
			Object valor = linhas.get(linha).get(coluna);
			if (valor != null) {
				return "<html>" + valor.toString().replaceAll("\\n", "<br/>") + "</html>";
			}
			return null;
		}

		return linhas.get(linha).get(coluna);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void adicionar(List<Paciente> lista) {
		linhas = new Vector<>();
		pacientes = new ArrayList<>(lista);
		for (Paciente p : lista) {

			Vector<Object> linha = new Vector();
			linha.add(p.getId());
			linha.add(p.getNome());
			linha.add(p.getCpf());
			linha.add(p.getTelefone());
			linha.add(p.getNascimento());
			linha.add(p.getSexo());
			linha.add(p.getEstadoCivil());
			linha.add(p.getNomeMae());
			linha.add(p.getCidade());
			linha.add(p.getBairro());
			linha.add(p.getHistoricoObservacoes());
			linha.add(p.getHistoricoProximosPassos());
			linha.add(p.getHistoricoRemedio());
			linhas.add(linha);
		}
		fireTableDataChanged();
	}

	public void adicionar1(Paciente paciente) {
		if (paciente != null) {
			int index = -1;
			for (int i = 0; i < linhas.size(); i++) {
				Vector<Object> linha = linhas.get(i);
				if (linha != null && linha.size() > 0) {
					String idPacienteTabela = (String) linha.get(0);
					if (idPacienteTabela.equals(paciente.getId())) {
						index = i;
						break;
					}
				}
			}

			if (index >= 0) {
				Vector<Object> linha = linhas.get(index);
				linha.set(11, paciente.getHistoricoProximosPassos());
			} else {
				Vector<Object> novaLinha = new Vector<>();
				novaLinha.add(paciente.getId());
				novaLinha.add(paciente.getNome());
				novaLinha.add(paciente.getCpf());
				novaLinha.add(paciente.getTelefone());
				novaLinha.add(paciente.getNascimento());
				novaLinha.add(paciente.getSexo());
				novaLinha.add(paciente.getEstadoCivil());
				novaLinha.add(paciente.getNomeMae());
				novaLinha.add(paciente.getCidade());
				novaLinha.add(paciente.getBairro());
				novaLinha.add(paciente.getHistoricoObservacoes());
				novaLinha.add(paciente.getHistoricoProximosPassos());
				novaLinha.add(paciente.getHistoricoRemedio());
				linhas.add(novaLinha);
			}
			fireTableDataChanged();

		}
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

	public String getIdPaciente() {
		return (String) getValueAt(0, 0);
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
