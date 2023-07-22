package br.edu.paulista.ifpe.model.tablemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.edu.paulista.ifpe.gui.tabelasDeEntidades.PainelAcao;
import br.edu.paulista.ifpe.model.entidades.Exame;
import br.edu.paulista.ifpe.model.entidades.Medico;

@SuppressWarnings("serial")
public class ExameTableModel extends AbstractTableModel {

	private Vector<String> colunas;
    private Vector<Vector<Object>> linhas;
    private List<Exame> exames;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ExameTableModel() {
		colunas = new Vector();
		colunas.add("ID");
		colunas.add("Nome");
		colunas.add("Ações");
		linhas = new Vector();
		exames = new ArrayList<>();
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
        if (coluna == 2) { // Coluna de Ações
            return PainelAcao.class;
        }
        return String.class;
    }

	public Object getValueAt(int linha, int coluna) {
        if (coluna == 2) { // Coluna de Ações
            return null; // Retorna null para a coluna de ações
        }
        // Retorne os valores das outras colunas da tabela
        return linhas.get(linha).get(coluna);
    }

	public boolean isCellEditable(int row, int column) {
        return column == 2; // Permite a edição somente na coluna de ações
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void adicionar(List<Exame> lista) {
		// Reinicializa os dados da tabela
		linhas = new Vector<>();
        exames = new ArrayList<>(lista);
		// Percorre a lista copiando os dados para a tabela
        for (Exame e : lista) {

            // Cria uma linha da tabela
            Vector<Object> linha = new Vector<>();
            linha.add(e.getId());
            linha.add(e.getNome());
            
            // A coluna de Ações não receberá dados do banco, por isso não é adicionado nada aqui
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
	public List<Exame> getExames() {
        return exames;
    }
	public Exame getExame(int row) {
        // Obter o objeto Medico correspondente à linha selecionada (row)
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
        // Remover o objeto Medico da lista de médicos
        if (row >= 0 && row < linhas.size()) {
            linhas.remove(row);
            fireTableRowsDeleted(row, row); // Notifica a tabela da remoção da linha
        }
    }
}