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

import br.edu.paulista.ifpe.core.util.cadastroMedicos.CadastroRemedioListener;
import br.edu.paulista.ifpe.data.RemediosDAO;
import br.edu.paulista.ifpe.gui.CadastroRemedios;
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

		/*JPanel tabelaAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroRemedios cr = new CadastroRemedios();
				cr.setListener(TelaRemedio.this);
				cr.setLocationRelativeTo(null);
				cr.setVisible(true);
			}
		});
		tabelaAcoes.add(btnAdicionar);

		add(tabelaAcoes, BorderLayout.NORTH); */

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		tabela = new JTable();
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabela.setModel(new RemedioTableModel());
		tabela.setFont(new Font("Arial", Font.PLAIN, 12));
		tabela.setRowHeight(40);
		scrollPane.setViewportView(tabela);
		RemediosDAO dao = new RemediosDAO();
		/*TableActionEvent evento = new TableActionEvent() {
			
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
			        RemedioTableModel model = (RemedioTableModel) tabela.getModel();
			        Remedio remedio = model.getRemedio(selectedRow);

			        try {
			            int i = JOptionPane.showConfirmDialog(null, "Deseja excluir o remédio selecionado?");
			            if (i == JOptionPane.YES_OPTION) {
			                boolean exclusaoBemSucedida = dao.excluir(remedio);
			                if (exclusaoBemSucedida) {
			                	JOptionPane.showMessageDialog(null, "Você excluiu o remédio com sucesso");
			                    model.removeRemedioAt(selectedRow);
			                    // Atualizar a tabela
			                    model.fireTableDataChanged();
			                }
			            } else if (i == JOptionPane.NO_OPTION) {
			                JOptionPane.showMessageDialog(null, "Você cancelou a exclusão com sucesso");
			            }

			        } catch (Exception ex) {
			            JOptionPane.showMessageDialog(null, "Erro ao excluir o remédio", "Erro",
			                    JOptionPane.ERROR_MESSAGE);
			            ex.printStackTrace();
			        }
			    } else {
			        JOptionPane.showMessageDialog(null, "Selecione um remédio antes de excluir.");
			    }
			}
	        
	    }; */
		//tabela.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
		//tabela.getColumnModel().getColumn(3).setCellEditor(new TabelaAcaoCellEditor(tabela, evento));
		
		
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
