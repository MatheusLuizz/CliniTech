package br.edu.paulista.ifpe.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class EscolhaCadastro extends JFrame {

	private JPanel contentPane;
	private JButton btnPaciente;
	private JButton btnMedico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EscolhaCadastro frame = new EscolhaCadastro();
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
	public EscolhaCadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnPaciente = new JButton("Paciente");
		btnPaciente.setFont(new Font("Arial", Font.BOLD, 16));
		btnPaciente.setToolTipText("Cadastrar novo Paciente");
		btnPaciente.setBounds(24, 25, 108, 59);
		contentPane.add(btnPaciente);
		btnPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPaciente p = new CadastroPaciente();
				EscolhaCadastro.this.dispose();
				p.setVisible(true);
			}
		});
		
		btnMedico = new JButton("Medico");
		btnMedico.setFont(new Font("Arial", Font.BOLD, 16));
		btnMedico.setToolTipText("Cadastrar novo médico\r\n");
		btnMedico.setBounds(24, 107, 108, 59);
		contentPane.add(btnMedico);
		btnMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroMedico m = new CadastroMedico();
				EscolhaCadastro.this.dispose();
				m.setVisible(true);
			}
		});
	}

}
