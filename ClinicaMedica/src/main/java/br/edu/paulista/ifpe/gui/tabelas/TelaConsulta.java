package br.edu.paulista.ifpe.gui.tabelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import br.edu.paulista.ifpe.data.ConsultasDAO;
import br.edu.paulista.ifpe.gui.componentesCustomizados.TabelaAcaoCellEditor;
import br.edu.paulista.ifpe.gui.componentesCustomizados.TableActionCellRender;
import br.edu.paulista.ifpe.gui.componentesCustomizados.TableActionEvent;
import br.edu.paulista.ifpe.gui.dialogos.RemarcarConsulta;
import br.edu.paulista.ifpe.model.entidades.Consulta;
import br.edu.paulista.ifpe.model.tablemodel.ConsultasTableModel;

@SuppressWarnings("serial")
public class TelaConsulta extends JTable {

	private JScrollPane scrollPane;
	private JTable tabela;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsulta frame = new TelaConsulta();
					frame.atualizar();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Não foi possível exibir as consultas", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public TelaConsulta() {
		setBounds(100, 100, 800, 500);
		setLayout(new BorderLayout());

		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(800, 500));
		add(scrollPane, BorderLayout.CENTER);
		JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnAcao = new JButton("Remarcar consulta");
		painelAcoes.add(btnAcao);
		add(painelAcoes, BorderLayout.NORTH);
		btnAcao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RemarcarConsulta rc = new RemarcarConsulta();
				rc.setLocationRelativeTo(null);
				rc.setVisible(true);
			}
		});

		tabela = new JTable();
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabela.setModel(new ConsultasTableModel());
		tabela.setFont(new Font("Arial", Font.PLAIN, 12));
		tabela.setRowHeight(40);
		scrollPane.setViewportView(tabela);
		ConsultasDAO dao = new ConsultasDAO();
		TableActionEvent evento = new TableActionEvent() {

			@Override
			public void onView(int linha) {
				JOptionPane.showMessageDialog(null, "Todos os dados estão na tabela");
			}

			@Override
			public void onEdit(int linha) {
			}

			@Override
			public void onDelete(int linha) {
				int selectedRow = tabela.getSelectedRow();
				if (selectedRow >= 0) {
					ConsultasTableModel model = (ConsultasTableModel) tabela.getModel();
					Consulta consulta = model.getConsulta(selectedRow);

					try {
						int i = JOptionPane.showConfirmDialog(null, "Deseja excluir a consulta selecionada?");
						if (i == JOptionPane.YES_OPTION) {
							boolean exclusaoBemSucedida = dao.excluir(consulta);
							if (exclusaoBemSucedida) {
								JOptionPane.showMessageDialog(null, "Você excluiu a consulta com sucesso");
								model.removeConsultaAt(selectedRow);
								// Atualizar a tabela
								model.fireTableDataChanged();
							}
						} else if (i == JOptionPane.NO_OPTION) {
							JOptionPane.showMessageDialog(null, "Você cancelou com sucesso");
						}

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao cancelar a consulta", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma consulta antes de excluir.");
				}
			}

		};
		tabela.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
		tabela.getColumnModel().getColumn(5).setCellEditor(new TabelaAcaoCellEditor(tabela, evento));

	}

	public JTable getTabela() {
		return tabela;
	}

	public TableModel getModeloTabela() {
		return tabela.getModel();
	}

	public void atualizar() {
		try {
			ConsultasDAO dao = new ConsultasDAO();
			List<Consulta> lista = dao.buscar(new Consulta());

			ConsultasTableModel modelo = (ConsultasTableModel) tabela.getModel();

			modelo.limpar();

			modelo.adicionar(lista);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar buscar as consultas");
		}
	}

	public void consultaCadastrada() {
		atualizar();
	}
}
