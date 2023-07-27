package br.edu.paulista.ifpe.gui.tabelasDeEntidades;

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

import br.edu.paulista.ifpe.core.util.cadastroMedicos.CadastroPacienteListener;
import br.edu.paulista.ifpe.data.MedicoDAO;
import br.edu.paulista.ifpe.data.PacienteDAO;
import br.edu.paulista.ifpe.gui.CadastroPaciente;
import br.edu.paulista.ifpe.gui.Home;
import br.edu.paulista.ifpe.model.entidades.Medico;
import br.edu.paulista.ifpe.model.entidades.Paciente;
import br.edu.paulista.ifpe.model.tablemodel.MedicTableModel;
import br.edu.paulista.ifpe.model.tablemodel.PacienteTableModel;

@SuppressWarnings("serial")
public class TelaPaciente extends JPanel implements CadastroPacienteListener {
    private JScrollPane scrollPane;
    protected JTable tabela;
    private Home home; // Referência para a classe Home

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPaciente frame = new TelaPaciente();
                    frame.atualizar();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Não foi possível exibir os pacientes", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public TelaPaciente() {
        this.home = null; // Inicializa a referência para a classe Home

        setBounds(100, 100, 800, 500);
        setLayout(new BorderLayout());

        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(800, 500));
        add(scrollPane, BorderLayout.CENTER);

        tabela = new JTable();
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setBorder(new LineBorder(new Color(0, 0, 0)));
        tabela.setModel(new PacienteTableModel());
        tabela.setFont(new Font("Arial", Font.PLAIN, 12));
        tabela.setRowHeight(40);
        scrollPane.setViewportView(tabela);
        PacienteDAO dao = new PacienteDAO();
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
			        PacienteTableModel model = (PacienteTableModel) tabela.getModel();
			        Paciente paciente = model.getPaciente(selectedRow);

			        try {
			            int i = JOptionPane.showConfirmDialog(null, "Deseja excluir o paciente selecionado?");
			            if (i == JOptionPane.YES_OPTION) {
			                boolean exclusaoBemSucedida = dao.excluir(paciente);
			                if (exclusaoBemSucedida) {
			                	JOptionPane.showMessageDialog(null, "Você excluiu o paciente com sucesso");
			                    model.removePacienteAt(selectedRow);
			                    // Atualizar a tabela
			                    model.fireTableDataChanged();
			                }
			            } else if (i == JOptionPane.NO_OPTION) {
			                JOptionPane.showMessageDialog(null, "Você cancelou a exclusão com sucesso");
			            }

			        } catch (Exception ex) {
			            JOptionPane.showMessageDialog(null, "Erro ao excluir o Paciente", "Erro",
			                    JOptionPane.ERROR_MESSAGE);
			            ex.printStackTrace();
			            
			        }
			    } else {
			        JOptionPane.showMessageDialog(null, "Selecione um Paciente antes de excluir.");
			    }
			}
	        
	    };
		tabela.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
		tabela.getColumnModel().getColumn(6).setCellEditor(new TabelaAcaoCellEditor(tabela, evento));
    }

    public JTable getTabela() {
        return tabela;
    }

    public TableModel getModeloTabela() {
        return tabela.getModel();
    }

    public void atualizar() {
        try {
            PacienteDAO dao = new PacienteDAO();
            List<Paciente> lista = dao.buscar(new Paciente());

            PacienteTableModel modelo = (PacienteTableModel) tabela.getModel();

            modelo.limpar();

            modelo.adicionar(lista);
            for (int i = 0; i < modelo.getRowCount(); i++) {
	            PainelAcao painelAcao = new PainelAcao();
	            Paciente paciente = lista.get(i);
	            painelAcao.setIdMedico(paciente.getId());
	            modelo.setValueAt(painelAcao, i, 6); // Defina o valor correto para a coluna de ações
	        }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar um Paciente");
        }
    }
    @Override
    public void pacienteCadastrado() {
        atualizar();
    }
    

    public void setHome(Home home) {
        this.home = home;
    }
}