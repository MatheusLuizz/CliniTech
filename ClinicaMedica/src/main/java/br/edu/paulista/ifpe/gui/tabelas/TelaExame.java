package br.edu.paulista.ifpe.gui.tabelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import br.edu.paulista.ifpe.core.cadastros.CadastroExameListener;
import br.edu.paulista.ifpe.data.ExamesDAO;
import br.edu.paulista.ifpe.gui.componentesCustomizados.TabelaAcaoCellEditor;
import br.edu.paulista.ifpe.gui.componentesCustomizados.TableActionCellRender;
import br.edu.paulista.ifpe.gui.componentesCustomizados.TableActionEvent;
import br.edu.paulista.ifpe.model.entidades.Exame;
import br.edu.paulista.ifpe.model.tablemodel.ExameTableModel;

@SuppressWarnings("serial")
public class TelaExame extends JTable implements CadastroExameListener {

	private JScrollPane scrollPane;
	private JTable tabela;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaExame frame = new TelaExame();
					frame.atualizar();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Não foi possível exibir os exames", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public TelaExame() {
		setBounds(100, 100, 800, 500);
		setLayout(new BorderLayout());

		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(800, 500));
		add(scrollPane, BorderLayout.CENTER);

		tabela = new JTable();
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabela.setModel(new ExameTableModel());
		tabela.setFont(new Font("Arial", Font.PLAIN, 12));
		tabela.setRowHeight(40);
		scrollPane.setViewportView(tabela);
		ExamesDAO dao = new ExamesDAO();
		TableActionEvent evento = new TableActionEvent() {
			
			@Override
			public void onView(int linha) {
				JOptionPane.showMessageDialog(null, "Todos os dados já estão na tabela");
				
			}
			
			@Override
			public void onEdit(int linha) {
			}
			
			@Override
			public void onDelete(int linha) {
			    int selectedRow = tabela.getSelectedRow();
			    if (selectedRow >= 0) {
			        ExameTableModel model = (ExameTableModel) tabela.getModel();
			        Exame exame = model.getExame(selectedRow);

			        try {
			            int i = JOptionPane.showConfirmDialog(null, "Deseja excluir o exame selecionado?");
			            if (i == JOptionPane.YES_OPTION) {
			                boolean exclusaoBemSucedida = dao.excluir(exame);
			                if (exclusaoBemSucedida) {
			                	JOptionPane.showMessageDialog(null, "Você excluiu o exame com sucesso");
			                    model.removeExameAt(selectedRow);
			                    // Atualizar a tabela
			                    model.fireTableDataChanged();
			                }
			            } else if (i == JOptionPane.NO_OPTION) {
			                JOptionPane.showMessageDialog(null, "Você cancelou a exclusão com sucesso");
			            }

			        } catch (Exception ex) {
			            JOptionPane.showMessageDialog(null, "Erro ao excluir o exame", "Erro",
			                    JOptionPane.ERROR_MESSAGE);
			        }
			    } else {
			        JOptionPane.showMessageDialog(null, "Selecione um exame antes de excluir.");
			    }
			}
	        
	    };
		tabela.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRender());
		tabela.getColumnModel().getColumn(2).setCellEditor(new TabelaAcaoCellEditor(tabela, evento));
		
		
	}

	public JTable getTabela() {
		return tabela;
	}

	public TableModel getModeloTabela() {
		return tabela.getModel();
	}

	public void atualizar() {
		try {
			ExamesDAO dao = new ExamesDAO();
			List<Exame> lista = dao.buscar(new Exame());

			ExameTableModel modelo = (ExameTableModel) tabela.getModel();

			modelo.limpar();

			modelo.adicionar(lista);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar buscar um Exame");
		}
	}

	@Override
	public void exameCadastrado() {
		atualizar();		
	}
}
