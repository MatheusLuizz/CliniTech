package br.edu.paulista.ifpe.gui.componentesCustomizados;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TableActionCellRender implements TableCellRenderer {

	@SuppressWarnings("rawtypes")
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value instanceof PainelAcao) {
			PainelAcao painelAcao = (PainelAcao) value;
			if (isSelected) {
				painelAcao.setBackground(table.getSelectionBackground());
			} else {
				painelAcao.setBackground(table.getBackground());
			}
			return painelAcao.getPanel();
		} else {
			return null;
		}
	}
}
