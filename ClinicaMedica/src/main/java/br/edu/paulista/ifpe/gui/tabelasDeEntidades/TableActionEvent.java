package br.edu.paulista.ifpe.gui.tabelasDeEntidades;

public interface TableActionEvent {
	void onView(int row);

	void onEdit(int row);

	void onDelete(int row);
}
