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

import br.edu.paulista.ifpe.data.PacienteDAO;
import br.edu.paulista.ifpe.gui.CadastroPaciente;
import br.edu.paulista.ifpe.gui.Home;
import br.edu.paulista.ifpe.model.entidades.Paciente;
import br.edu.paulista.ifpe.model.tablemodel.PacienteTableModel;

@SuppressWarnings("serial")
public class TelaPaciente extends JPanel {
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

        JPanel tabelaAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setFont(new Font("Arial", Font.PLAIN, 11));
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CadastroPaciente cp = new CadastroPaciente();
                cp.setLocationRelativeTo(null);
                cp.setVisible(true);
            }
        });
        tabelaAcoes.add(btnAdicionar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Arial", Font.PLAIN, 11));
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Editar um paciente existente
            }
        });
        tabelaAcoes.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Arial", Font.PLAIN, 11));
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Excluir um paciente
            }
        });
        tabelaAcoes.add(btnExcluir);

        JButton btnDetalhes = new JButton("Detalhes");
        btnDetalhes.setFont(new Font("Arial", Font.PLAIN, 11));
        tabelaAcoes.add(btnDetalhes);
        btnDetalhes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (home != null) {
                    home.exibirDetalhesPaciente();
                }
            }
        });
        add(tabelaAcoes, BorderLayout.NORTH);

        scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        tabela = new JTable();
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setBorder(new LineBorder(new Color(0, 0, 0)));
        tabela.setModel(new PacienteTableModel());
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
            PacienteDAO dao = new PacienteDAO();
            List<Paciente> lista = dao.buscar(new Paciente());

            PacienteTableModel modelo = (PacienteTableModel) tabela.getModel();

            modelo.limpar();

            modelo.adicionar(lista);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar um Cliente");
        }
    }

    public void setHome(Home home) {
        this.home = home;
    }
}