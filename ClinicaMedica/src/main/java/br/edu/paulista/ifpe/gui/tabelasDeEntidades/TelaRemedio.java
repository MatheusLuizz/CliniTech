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

import br.edu.paulista.ifpe.data.RemediosDAO;
import br.edu.paulista.ifpe.model.entidades.Remedio;
import br.edu.paulista.ifpe.model.tablemodel.RemedioTableModel;

@SuppressWarnings("serial")
public class TelaRemedio extends JPanel {
	
	private JScrollPane scrollPane;
    private JTable tabela;

	/**
	 * 
	 */
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRemedio frame = new TelaRemedio();
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
	public TelaRemedio() {
		setBounds(100, 100, 800, 500);
        setLayout(new BorderLayout());

        JPanel tabelaAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setFont(new Font("Arial", Font.PLAIN, 11));
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        tabelaAcoes.add(btnAdicionar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Arial", Font.PLAIN, 11));
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        tabelaAcoes.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Arial", Font.PLAIN, 11));
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
        tabela.setModel(new RemedioTableModel());
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
            RemediosDAO dao = new RemediosDAO();
            List<Remedio> lista = dao.buscar(new Remedio());

            /* Captura o modelo da tabela */
            RemedioTableModel modelo = (RemedioTableModel) tabela.getModel();

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
