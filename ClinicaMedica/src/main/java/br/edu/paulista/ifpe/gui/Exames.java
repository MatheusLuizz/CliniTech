package br.edu.paulista.ifpe.gui;

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

import br.edu.paulista.ifpe.data.ExamesDAO;
import br.edu.paulista.ifpe.model.entidades.Exame;
import br.edu.paulista.ifpe.model.tablemodel.ExameTableModel;

@SuppressWarnings("serial")
public class Exames extends JTable {

	private JScrollPane scrollPane;
    private JTable tabela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exames frame = new Exames();
					frame.atualizar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Exames() {
		setBounds(100, 100, 800, 500);
        setLayout(new BorderLayout());

        JPanel tabelaAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        tabelaAcoes.add(btnAdicionar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        tabelaAcoes.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        tabelaAcoes.add(btnExcluir);

        add(tabelaAcoes, BorderLayout.NORTH);

        scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        tabela = new JTable();
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setBorder(new LineBorder(new Color(0, 0, 0)));
        tabela.setModel(new ExameTableModel());
        tabela.setFont(new Font("Arial", Font.PLAIN, 12));
        scrollPane.setViewportView(tabela);
    }
    public JTable getTabela() {
        return tabela;
    }

    public TableModel getModeloTabela() {
        return tabela.getModel();
    }
	
	public void atualizar() {
        try {
            /* Criação do DAO */
            ExamesDAO dao = new ExamesDAO();
            List<Exame> lista = dao.buscar(new Exame());

            /* Captura o modelo da tabela */
            ExameTableModel modelo = (ExameTableModel) tabela.getModel();

            /* Limpa o modelo existente */
            modelo.limpar();

            /* Adiciona os médicos ao modelo */
            modelo.adicionar(lista);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar um Exame");
        }
    }
}
