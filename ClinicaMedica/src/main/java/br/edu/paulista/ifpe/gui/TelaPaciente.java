package br.edu.paulista.ifpe.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import br.edu.paulista.ifpe.data.PacienteDAO;
import br.edu.paulista.ifpe.model.tablemodel.PacienteTableModel;
import br.edu.paulista.ifpe.model.user.Paciente;

@SuppressWarnings("serial")
public class TelaPaciente extends JFrame {

    private JPanel contentPane;
    private JButton btnHome;
    private JScrollPane scrollPane;
    private JTable tabela;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPaciente frame = new TelaPaciente();
                    frame.atualizar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TelaPaciente() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        btnHome = new JButton("New button");
        contentPane.add(btnHome, BorderLayout.WEST);
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home h = new Home();
                TelaPaciente.this.dispose();
                h.setVisible(true);
            }
        });

        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tabela = new JTable();
        tabela.setBorder(new LineBorder(new Color(0, 0, 0)));
        tabela.setModel(new PacienteTableModel());
        tabela.setFont(new Font("Arial", Font.PLAIN, 12));
        scrollPane.setViewportView(tabela);
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