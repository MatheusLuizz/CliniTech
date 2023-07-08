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

import br.edu.paulista.ifpe.data.MedicoDAO;
import br.edu.paulista.ifpe.model.tablemodel.MedicTableModel;
import br.edu.paulista.ifpe.model.user.Medico;

@SuppressWarnings("serial")
public class TelaMedico extends JPanel {

    private JScrollPane scrollPane;
    private JTable tabela;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaMedico frame = new TelaMedico();
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
    public TelaMedico() {
    	setBounds(100, 100, 800, 500);
        setLayout(new BorderLayout());

        JPanel tabelaAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para adicionar um novo paciente
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
        tabela.setBorder(new LineBorder(new Color(0, 0, 0)));
        tabela.setModel(new MedicTableModel());
        tabela.setFont(new Font("Arial", Font.PLAIN, 12));
        scrollPane.setViewportView(tabela);
    }

    public void atualizar() {
        try {
            /* Criação do DAO */
            MedicoDAO dao = new MedicoDAO();
            List<Medico> lista = dao.buscar(new Medico());

            /* Captura o modelo da tabela */
            MedicTableModel modelo = (MedicTableModel) tabela.getModel();

            /* Limpa o modelo existente */
            modelo.limpar();

            /* Adiciona os médicos ao modelo */
            modelo.adicionar(lista);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar um Médico");
        }
    }
}
