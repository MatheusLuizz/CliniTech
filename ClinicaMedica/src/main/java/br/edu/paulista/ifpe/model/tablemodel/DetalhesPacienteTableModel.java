package br.edu.paulista.ifpe.model.tablemodel;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.edu.paulista.ifpe.model.entidades.Paciente;

@SuppressWarnings("serial")
public class DetalhesPacienteTableModel extends AbstractTableModel {
    @SuppressWarnings("rawtypes")
    private Vector colunas;
    @SuppressWarnings("rawtypes")
    private Vector linhas;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public DetalhesPacienteTableModel() {
        colunas = new Vector();
        colunas.add("Nome");
        colunas.add("Data Exame Marcado");
        colunas.add("Hora Exame Marcado");
        colunas.add("Data Consulta");
        colunas.add("Hora Consulta");
        colunas.add("Histórico");
        colunas.add("Histórico de Remédio");
        colunas.add("Data de Nascimento");
        colunas.add("Sexo");
        colunas.add("Estado Civil");
        colunas.add("Cidade");
        linhas = new Vector();
    }

    public int getRowCount() {
        return linhas.size();
    }

    public int getColumnCount() {
        return colunas.size();
    }

    public String getColumnName(int coluna) {
        return (String) colunas.get(coluna);
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
    public void adicionar(List<Paciente> lista) {
        linhas = new Vector();

        for (Paciente p : lista) {
            Vector<Object> linha = new Vector();
            linha.add(p.getNome());
            linha.add(p.getExameMarcadoData());
            linha.add(p.getExameMarcadoHora());
            linha.add(p.getConsultaData());
            linha.add(p.getConsultaHora());
            linha.add(p.getHistoricoObservacoes() + " " + p.getHistoricoProximosPassos());
            linha.add(p.getHistoricoRemedioId());
            linha.add(p.getNascimento());
            linha.add(p.getSexo());
            linha.add(p.getEstadoCivil());
            linha.add(p.getCidade());
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
}
