package br.edu.paulista.ifpe.gui.componentesCustomizados;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

@SuppressWarnings("serial")
public class TabelaAcaoCellEditor extends AbstractCellEditor implements TableCellEditor {
	@SuppressWarnings("rawtypes")
	private PainelAcao painelAcao;
	@SuppressWarnings("unused")
	private JTable table;
	private TableActionEvent evento;

	public TabelaAcaoCellEditor(JTable table, TableActionEvent evento) {
		this.table = table;
		this.evento = evento;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		painelAcao = new PainelAcao();
		painelAcao.setBackground(table.getSelectionBackground());
		painelAcao.initEvent(table, evento, row);
		if (isSelected) {
			painelAcao.getPanel().setBackground(table.getSelectionBackground());
		} else {
			painelAcao.getPanel().setBackground(table.getBackground());
		}
		return painelAcao;
	}

	@Override
	public Object getCellEditorValue() {
		return null;
	}

	@Override
	public boolean isCellEditable(EventObject e) {
		return true;
	}
}