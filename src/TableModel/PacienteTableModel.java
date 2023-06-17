package TableModel;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import Entidades.Paciente;

@SuppressWarnings("serial")
public class PacienteTableModel extends AbstractTableModel {
	@SuppressWarnings("rawtypes")
	private Vector colunas;
	@SuppressWarnings("rawtypes")
	private Vector linhas;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PacienteTableModel() {
		colunas = new Vector();
		colunas.add("Nome");
		colunas.add("Telefone");
		colunas.add("Email");
		colunas.add("Nascimento");
		colunas.add("Sexo");
		linhas = new Vector();
	}

	public int getRowCount() {
		// captura o total de linhas da tabela
		int totalLinhas = linhas.size();
		return totalLinhas;
	}

	public int getColumnCount() {
		// captura o total de colunas da tabela
		int totalColunas = colunas.size();
		return totalColunas;
	}

	public String getColumnName(int coluna) {
		// Captura o nome da coluna
		String nomeColuna = (String) colunas.get(coluna);
		return nomeColuna;
	}

	public Object getValueAt(int linha, int coluna) {
		// Captura o registro informado
		@SuppressWarnings("rawtypes")
		Vector registro = (Vector) linhas.get(linha);

		// Dentro do registro captura a coluna selecionada
		Object dado = registro.get(coluna);

		// Retorna o valor capturado
		return dado;
	}
	@SuppressWarnings("unchecked")
	public void adicionar(List<Paciente> lista) {
        /* Reinicializa os dados da tabela */
        linhas = new Vector();

        /* Percorre a lista copiando os dados para a tabela */
        for (Paciente p : lista) {
            
            /* Cria uma linha da tabela */
            @SuppressWarnings("rawtypes")
			Vector<Object> linha = new Vector();
            linha.add(p.getNome());
            linha.add(p.getTelefone());
            linha.add(p.getEmail());
            linha.add(p.getNascimento());
            linha.add(p.getSexo());
            /* Adiciona a linha a tabela */
            linhas.add(linha);
        }
        /* Atualiza a tabela */
        fireTableDataChanged();
    }
}
