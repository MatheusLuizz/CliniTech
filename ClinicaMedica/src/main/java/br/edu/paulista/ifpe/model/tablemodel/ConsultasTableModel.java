package br.edu.paulista.ifpe.model.tablemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.edu.paulista.ifpe.gui.tabelasDeEntidades.PainelAcao;
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
        if (coluna == 5) { // Coluna de Ações
            return PainelAcao.class;
        }
        return String.class;
    }

	public Object getValueAt(int linha, int coluna) {
        if (coluna == 5) { // Coluna de Ações
            return null; // Retorna null para a coluna de ações
        }
        // Retorne os valores das outras colunas da tabela
        return linhas.get(linha).get(coluna);
    }

	public boolean isCellEditable(int row, int column) {
        return column == 5; // Permite a edição somente na coluna de ações
    }

	@SuppressWarnings({ })
	public void adicionar(List<Consulta> lista) {
		// Reinicializa os dados da tabela
		linhas = new Vector<>();
        consultas = new ArrayList<>(lista);
		// Percorre a lista copiando os dados para a tabela
        for (Consulta c : lista) {

            // Cria uma linha da tabela
            Vector<Object> linha = new Vector<>();
            linha.add(c.getId());
            linha.add(c.getNomePaciente());
            linha.add(c.getNomeMedico());
            linha.add(c.getData());
            linha.add(c.getHorario());
            
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
	public List<Consulta> getConsultas() {
        return consultas;
    }
	public Consulta getConsulta(int row) {
        // Obter o objeto Medico correspondente à linha selecionada (row)
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
        // Remover o objeto Medico da lista de médicos
        if (row >= 0 && row < linhas.size()) {
            linhas.remove(row);
            fireTableRowsDeleted(row, row); // Notifica a tabela da remoção da linha
        }
    }
}
