package br.edu.paulista.ifpe.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Home extends JFrame {

	private JPanel contentPane;
	private JPanel painel;
	private JToggleButton btnPaciente;
	private JToggleButton btnCadastro;
	private JToggleButton btnMedico;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		painel = new JPanel();
		painel.setBounds(0, 0, 91, 468);
		painel.setBackground(new Color(13, 73, 151));
		
		btnPaciente = new JToggleButton("");
		btnPaciente.setToolTipText("Exibir os pacientes cadastrados");
		btnPaciente.setForeground(new Color(13, 73, 151));
		btnPaciente.setBackground(new Color(13, 73, 151));
		btnPaciente.setBorderPainted(false);
		btnPaciente.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconePaciente.png")));
		
		btnCadastro = new JToggleButton("");
		btnCadastro.setToolTipText("Cadastrar um novo Paciente");
		btnCadastro.setBackground(new Color(255, 255, 255));
		btnCadastro.setForeground(new Color(255, 255, 255));
		btnCadastro.setBorderPainted(false);
		btnCadastro.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeCalendario.png")));
		
		btnMedico = new JToggleButton("");
		btnMedico.setForeground(new Color(255, 255, 255));
		btnMedico.setBackground(new Color(255, 255, 255));
		btnMedico.setBorderPainted(false);
		btnMedico.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeMedico.png")));
		GroupLayout gl_painel = new GroupLayout(painel);
		gl_painel.setHorizontalGroup(
			gl_painel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_painel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnCadastro, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_painel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnMedico, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(btnPaciente, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_painel.setVerticalGroup(
			gl_painel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_painel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPaciente, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnMedico, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCadastro)
					.addContainerGap(250, Short.MAX_VALUE))
		);
		painel.setLayout(gl_painel);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(91, 0, 731, 65);
		panel.setBackground(new Color(211, 211, 211));
		panel.setLayout(null);
		contentPane.add(panel);
		contentPane.add(painel);
	
	btnPaciente.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		TelaPaciente p = new TelaPaciente();
		Home.this.dispose();
		p.atualizar();
		p.setVisible(true);
	}
	});
	btnCadastro.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			EscolhaCadastro c = new EscolhaCadastro();
			Home.this.dispose();
			c.setVisible(true);
		}
		});
	btnMedico.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			TelaMedico m = new TelaMedico();
			Home.this.dispose();
			m.setVisible(true);
		}
	});
	}
}
