package br.edu.paulista.ifpe.model.cardTables;

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
import br.edu.paulista.ifpe.model.tablemodel.PacienteTableModel;
import br.edu.paulista.ifpe.model.user.Paciente;

@SuppressWarnings("serial")
public class TelaPaciente extends JPanel {
    private JScrollPane scrollPane;
    protected JTable tabela;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPaciente frame = new TelaPaciente();
                    frame.atualizar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the panel.
     */
    public TelaPaciente() {

        setBounds(100, 100, 800, 500);
        setLayout(new BorderLayout());

        JPanel tabelaAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CadastroPaciente cp = new CadastroPaciente();
                cp.setVisible(true);
            }
        });
        tabelaAcoes.add(btnAdicionar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para editar um paciente existente
            }
        });
        tabelaAcoes.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para excluir um paciente
            }
        });
        tabelaAcoes.add(btnExcluir);

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
            /* Criação do DAO */
            PacienteDAO dao = new PacienteDAO();
            List<Paciente> lista = dao.buscar(new Paciente());

            /* Captura o modelo da tabela */
            PacienteTableModel modelo = (PacienteTableModel) tabela.getModel();

            /* Limpa o modelo existente */
            modelo.limpar();

            /* Adiciona os pacientes ao modelo */
            modelo.adicionar(lista);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar um Cliente");
        }
    }
}