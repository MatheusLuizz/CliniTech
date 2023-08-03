package br.edu.paulista.ifpe.gui.tabelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import br.edu.paulista.ifpe.core.cadastros.CadastroRemedioListener;
import br.edu.paulista.ifpe.data.RemediosDAO;
import br.edu.paulista.ifpe.model.entidades.Remedio;
import br.edu.paulista.ifpe.model.tablemodel.RemedioTableModel;

@SuppressWarnings("serial")
public class TelaRemedio extends JPanel implements CadastroRemedioListener {

	private JScrollPane scrollPane;
	private JTable tabela;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRemedio frame = new TelaRemedio();
					frame.atualizar();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Não foi possível exibir os remédios", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public TelaRemedio() {
		setBounds(100, 100, 800, 500);
		setLayout(new BorderLayout());

		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(800, 500));
		add(scrollPane, BorderLayout.CENTER);

		tabela = new JTable();
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabela.setModel(new RemedioTableModel());
		tabela.setFont(new Font("Arial", Font.PLAIN, 12));
		tabela.setRowHeight(40);
		scrollPane.setViewportView(tabela);

	}

	public void remedioCadastrado() {
		atualizar();
	}

	public JTable getTabela() {
		return tabela;
	}

	public TableModel getModeloTabela() {
		return tabela.getModel();
	}

	public void atualizar() {
		try {

			RemediosDAO dao = new RemediosDAO();
			List<Remedio> lista = dao.buscar(new Remedio());

			RemedioTableModel modelo = (RemedioTableModel) tabela.getModel();

			modelo.limpar();

			modelo.adicionar(lista);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar buscar um Exame");
		}
	}

}
