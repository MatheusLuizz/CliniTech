package br.edu.paulista.ifpe.model.tablemodel;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.edu.paulista.ifpe.model.entidades.Remedio;


@SuppressWarnings("serial")
public class RemedioTableModel extends AbstractTableModel {
	
	@SuppressWarnings("rawtypes")
	private Vector colunas;
	@SuppressWarnings("rawtypes")
	private Vector linhas;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RemedioTableModel() {
		colunas = new Vector();
		colunas.add("Nome");
		colunas.add("Apresentação");
		linhas = new Vector();
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

	public Object getValueAt(int linha, int coluna) {

		@SuppressWarnings("rawtypes")
		Vector registro = (Vector) linhas.get(linha);

		Object dado = registro.get(coluna);

		return dado;
	}
	public boolean isCellEditable(int row, int column) {
        return false; 
    }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void adicionar(List<Remedio> lista) {
        // Reinicializa os dados da tabela 
        linhas = new Vector();

        // Percorre a lista copiando os dados para a tabela 
        for (Remedio p : lista) {
            
            // Cria uma linha da tabela 
            Vector<Object> linha = new Vector();
            linha.add(p.getNome());
            linha.add(p.getApresentacao());
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
}
