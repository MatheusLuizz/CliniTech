package br.edu.paulista.ifpe.gui.dialogos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import br.edu.paulista.ifpe.model.entidades.Paciente;
import br.edu.paulista.ifpe.model.tablemodel.DetalhesPacienteTableModel;

@SuppressWarnings("serial")
public class DetalhesPacienteDialog extends JDialog {

	@SuppressWarnings("unused")
	private Paciente paciente;
	private final JPanel contentPane = new JPanel();
	private JTable tabelaDetalhes;

	public DetalhesPacienteDialog(Paciente paciente) {
		setFont(new Font("Arial", Font.BOLD, 22));
		this.paciente = paciente;
		setModal(true);
		setType(Type.UTILITY);

		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Detalhes do Paciente");
		setBounds(100, 100, 1485, 499);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());

		JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnAcao = new JButton("Modificar próximos passos");
		painelAcoes.add(btnAcao);
		contentPane.add(painelAcoes, BorderLayout.NORTH);
		List<Paciente> listaPacientes = new ArrayList<>();
		listaPacientes.add(paciente);

		tabelaDetalhes = new JTable();
		tabelaDetalhes.getTableHeader().setReorderingAllowed(false);
		tabelaDetalhes.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabelaDetalhes.setModel(new DetalhesPacienteTableModel());
		tabelaDetalhes.setRowHeight(100);

		((DetalhesPacienteTableModel) tabelaDetalhes.getModel()).adicionar1(paciente);
		JScrollPane scrollPane = new JScrollPane(tabelaDetalhes);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		getContentPane().add(contentPane);
		for (int i = 0; i < tabelaDetalhes.getColumnCount(); i++) {
			tabelaDetalhes.getColumnModel().getColumn(i).setCellRenderer(new TopAlignedTableCellRenderer());
		}
		for (int i = 0; i < tabelaDetalhes.getColumnCount(); i++) {
			tabelaDetalhes.getColumnModel().getColumn(i).setPreferredWidth(150);
		}
		Font font = new Font("Arial", Font.PLAIN, 12);
		tabelaDetalhes.setFont(font);

		Color corFundoTabela;
		Color corTextoTabela;
		Color corSelecaoTabela;

		corFundoTabela = Color.WHITE;
		corTextoTabela = Color.BLACK;
		corSelecaoTabela = new Color(135, 206, 235);

		tabelaDetalhes.setBackground(corFundoTabela);
		tabelaDetalhes.setForeground(corTextoTabela);
		tabelaDetalhes.setSelectionBackground(corSelecaoTabela);
		tabelaDetalhes.setSelectionForeground(corTextoTabela);

		btnAcao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DetalhesPacienteTableModel modelo = (DetalhesPacienteTableModel) tabelaDetalhes.getModel();
				String pacienteId = modelo.getIdPaciente();
				AlterarPassos ap = new AlterarPassos(pacienteId);
				ap.setVisible(true);
			}
		});
	}

	// Sobrescrevendo o método getTableCellRenderer para alinhar o conteúdo ao topo
	private class TopAlignedTableCellRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			setVerticalAlignment(javax.swing.SwingConstants.TOP);
			return component;
		}
	}
}
