package br.edu.paulista.ifpe.gui.componentesCustomizados;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.edu.paulista.ifpe.model.tablemodel.PacienteAmanhaGeralTableModel;
import br.edu.paulista.ifpe.model.tablemodel.PacienteDiarioGeralTableModel;

@SuppressWarnings("serial")
public class TelaInicio extends JPanel {
	private JLabel lblNewLabel_1;
	private JPanel painelAmanha;
	private JScrollPane scrollPane1;
	private JTable tabelaAmanha;
	@SuppressWarnings("unused")
	private JScrollPane scrollPane_1;
	private JPanel painelHoje;
	private JLabel lblNewLabel_2;
	private JScrollPane scrollPane;
	private JTable tabelaHoje;
	private JPanel grafico;
	private PacienteDiarioGeralTableModel pacienteTableModel;
	private PacienteAmanhaGeralTableModel paciente1TableModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicio tela = new TelaInicio();
					tela.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Não foi possível exibir a tela inicial", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public TelaInicio() {
		setLayout(null);

		painelAmanha = new JPanel();
		painelAmanha.setBounds(589, 426, 555, 258);
		painelAmanha.setLayout(null);

		lblNewLabel_1 = new JLabel("Atendimentos de amanhã");
		lblNewLabel_1.setBounds(0, 6, 207, 24);
		painelAmanha.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 17));

		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(10, 52, 535, 206);
		painelAmanha.add(scrollPane1);

		tabelaAmanha = new JTable();
		paciente1TableModel = new PacienteAmanhaGeralTableModel();
		tabelaAmanha.setModel(paciente1TableModel);
		tabelaAmanha.getTableHeader().setReorderingAllowed(false);
		paciente1TableModel.adicionarConsultaFromSQL();
		scrollPane1.setViewportView(tabelaAmanha);

		add(painelAmanha);

		painelHoje = new JPanel();
		painelHoje.setBounds(10, 426, 555, 258);
		add(painelHoje);
		painelHoje.setLayout(null);

		lblNewLabel_2 = new JLabel("Atendimentos do dia");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel_2.setBounds(0, 1, 192, 41);
		painelHoje.add(lblNewLabel_2);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 535, 206);
		painelHoje.add(scrollPane);

		tabelaHoje = new JTable();
		pacienteTableModel = new PacienteDiarioGeralTableModel();
		tabelaHoje.setModel(pacienteTableModel);
		tabelaHoje.getTableHeader().setReorderingAllowed(false);
		pacienteTableModel.adicionarConsultaFromSQL();
		scrollPane.setViewportView(tabelaHoje);

		tabelaHoje.setPreferredSize(new Dimension(scrollPane.getWidth(), tabelaHoje.getPreferredSize().height));

		grafico = new PainelGrafico();
		grafico.setBounds(10, 10, 1168, 406);
		add(grafico);
	}
}