package br.edu.paulista.ifpe.gui.componentesCustomizados;

public interface TableActionEvent {
	void onView(int row);

	void onEdit(int row);

	void onDelete(int row);
}
