package br.edu.paulista.ifpe.model.tablemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.edu.paulista.ifpe.gui.tabelasDeEntidades.PainelAcao;
import br.edu.paulista.ifpe.model.entidades.Medico;
import br.edu.paulista.ifpe.model.entidades.Remedio;


@SuppressWarnings("serial")
public class RemedioTableModel extends AbstractTableModel {
	
	private Vector<String> colunas;
    private Vector<Vector<Object>> linhas;
    private List<Remedio> remedios;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RemedioTableModel() {
		colunas = new Vector();
		colunas.add("id");
		colunas.add("Nome");
		colunas.add("Apresentação");
		colunas.add("Ações");
		linhas = new Vector();
		remedios = new ArrayList<>();
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
        if (coluna == 3) { // Coluna de Ações
            return PainelAcao.class;
        }
        return String.class;
    }

	@Override
    public Object getValueAt(int linha, int coluna) {
        if (coluna == 3) { // Coluna de Ações
            return null; // Retorna null para a coluna de ações
        }
        // Retorne os valores das outras colunas da tabela
        return linhas.get(linha).get(coluna);
    }
	public boolean isCellEditable(int row, int column) {
        return column == 3; // Permite a edição somente na coluna de ações
    }
	
	public void adicionar(List<Remedio> lista) {
        // Reinicializa os dados da tabela 
		linhas = new Vector<>();
        remedios = new ArrayList<>(lista);
        // Percorre a lista copiando os dados para a tabela 
        for (Remedio r : lista) {
            
            // Cria uma linha da tabela 
            Vector<Object> linha = new Vector<>();
            linha.add(r.getId());
            linha.add(r.getNome());
            linha.add(r.getApresentacao());
           
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
	public List<Remedio> getRemedios() {
        return remedios;
    }
    public Remedio getRemedio(int row) {
        
        if (row >= 0 && row < linhas.size()) {
            Vector<Object> linha = linhas.get(row);
            if (linha != null && linha.size() > 1) {
                String idRemedio = (String) linha.get(0);
                for (Remedio remedio : remedios) {
                    if (remedio.getId().equals(idRemedio)) {
                        return remedio;
                    }
                }
            }
        }
        return null;
    }
    public void removeRemedioAt(int row) {
        
        if (row >= 0 && row < linhas.size()) {
            linhas.remove(row);
            fireTableRowsDeleted(row, row); // Notifica a tabela da remoção da linha
        }
    }
}
