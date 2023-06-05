package Telas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Home extends JFrame {

	private JPanel contentPane;
	private JPanel painel;
	private JToggleButton btnPaciente;
	private JToggleButton btnCadastro;
	private JLabel lblNewLabel;
	private JToggleButton btnMedico;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

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
		setBounds(100, 100, 553, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		painel = new JPanel();
		painel.setBounds(0, 10, 539, 106);
		contentPane.add(painel);
		
		btnPaciente = new JToggleButton("");
		btnPaciente.setToolTipText("Exibir os pacientes cadastrados");
		btnPaciente.setForeground(new Color(255, 255, 255));
		btnPaciente.setBackground(new Color(255, 255, 255));
		btnPaciente.setIcon(new ImageIcon(Home.class.getResource("/Imagens/iconePaciente.png")));
		
		btnCadastro = new JToggleButton("");
		btnCadastro.setToolTipText("Cadastrar um novo Paciente");
		btnCadastro.setBackground(new Color(255, 255, 255));
		btnCadastro.setForeground(new Color(255, 255, 255));
		btnCadastro.setIcon(new ImageIcon(Home.class.getResource("/Imagens/iconeCalendario.png")));
		
		lblNewLabel = new JLabel("Pacientes");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		
		btnMedico = new JToggleButton("");
		btnMedico.setForeground(new Color(255, 255, 255));
		btnMedico.setBackground(new Color(255, 255, 255));
		btnMedico.setIcon(new ImageIcon(Home.class.getResource("/Imagens/iconeMedico.png")));
		
		lblNewLabel_1 = new JLabel("Médicos");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblNewLabel_2 = new JLabel("Cadastro");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		GroupLayout gl_painel = new GroupLayout(painel);
		gl_painel.setHorizontalGroup(
			gl_painel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painel.createSequentialGroup()
					.addGroup(gl_painel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_painel.createSequentialGroup()
							.addGap(18)
							.addComponent(lblNewLabel))
						.addGroup(gl_painel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnPaciente, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_painel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_painel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnMedico, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_painel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_painel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(btnCadastro, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_painel.setVerticalGroup(
			gl_painel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_painel.createSequentialGroup()
					.addGroup(gl_painel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_painel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCadastro, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
						.addGroup(gl_painel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnPaciente, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_painel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnMedico, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2))
					.addGap(26))
		);
		painel.setLayout(gl_painel);
	
	btnPaciente.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		TelaPaciente p = new TelaPaciente();
		Home.this.dispose();
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
