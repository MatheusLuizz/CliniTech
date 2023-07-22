package br.edu.paulista.ifpe.model.tablemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.edu.paulista.ifpe.gui.tabelasDeEntidades.PainelAcao;
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
        if (coluna == 6) { // Coluna de Ações
            return PainelAcao.class;
        }
        return String.class;
    }

	@Override
    public Object getValueAt(int linha, int coluna) {
        if (coluna == 6) { // Coluna de Ações
            return null; // Retorna null para a coluna de ações
        }
        // Retorne os valores das outras colunas da tabela
        return linhas.get(linha).get(coluna);
    }

	@Override
    public boolean isCellEditable(int row, int column) {
        return column == 6; // Permite a edição somente na coluna de ações
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void adicionar(List<Paciente> lista) {
		// Reinicializa os dados da tabela
		linhas = new Vector<>();
        pacientes = new ArrayList<>(lista);
		// Percorre a lista copiando os dados para a tabela
		for (Paciente p : lista) {

			// Cria uma linha da tabela
			Vector<Object> linha = new Vector();
			linha.add(p.getId());
			linha.add(p.getNome());
			linha.add(p.getTelefone());
			linha.add(p.getEmail());
			linha.add(p.getNascimento());
			linha.add(p.getSexo());
			// Adiciona a linha a tabela
			linhas.add(linha);
		}
		// Atualiza a tabela
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
        // Obter o objeto Medico correspondente à linha selecionada (row)
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
        // Remover o objeto Medico da lista de médicos
        if (row >= 0 && row < linhas.size()) {
            linhas.remove(row);
            fireTableRowsDeleted(row, row); // Notifica a tabela da remoção da linha
        }
    }

}
