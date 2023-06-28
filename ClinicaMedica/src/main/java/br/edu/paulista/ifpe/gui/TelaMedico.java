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

import br.edu.paulista.ifpe.data.MedicoDAO;
import br.edu.paulista.ifpe.model.tablemodel.MedicTableModel;
import br.edu.paulista.ifpe.model.user.Medico;

@SuppressWarnings("serial")
public class TelaMedico extends JFrame {

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
					TelaMedico frame = new TelaMedico();
					frame.atualizar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	public void setVisible(boolean visivel) {
        super.setVisible(visivel);
        if (visivel) {
            atualizar();
        }
    }

	/**
	 * Create the frame.
	 */
	public TelaMedico() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        btnHome = new JButton("Home");
        contentPane.add(btnHome, BorderLayout.WEST);
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home h = new Home();
                TelaMedico.this.dispose();
                h.setVisible(true);
            }
        });

        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

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

            /* Adiciona os pacientes ao modelo */
            modelo.adicionar(lista);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar um Cliente");
        }
    }
}
