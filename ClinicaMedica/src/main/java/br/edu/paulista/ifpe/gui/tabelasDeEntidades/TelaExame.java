package br.edu.paulista.ifpe.gui.tabelasDeEntidades;

import java.awt.BorderLayout;
import java.awt.Color;
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

import br.edu.paulista.ifpe.core.util.cadastroMedicos.CadastroExameListener;
import br.edu.paulista.ifpe.data.ExamesDAO;
import br.edu.paulista.ifpe.data.MedicoDAO;
import br.edu.paulista.ifpe.model.entidades.Exame;
import br.edu.paulista.ifpe.model.entidades.Medico;
import br.edu.paulista.ifpe.model.tablemodel.ExameTableModel;
import br.edu.paulista.ifpe.model.tablemodel.MedicTableModel;

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

		JPanel tabelaAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroExames ce = new CadastroExames();
				ce.setListener(TelaExame.this);
				ce.setLocationRelativeTo(null);
				ce.setVisible(true);
			}
		});
		tabelaAcoes.add(btnAdicionar);

		add(tabelaAcoes, BorderLayout.NORTH);

		scrollPane = new JScrollPane();
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
				System.out.println("Visualizando linha: " + linha);
				
			}
			
			@Override
			public void onEdit(int linha) {
				System.out.println("Editando linha: " + linha);
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
			            ex.printStackTrace();
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
